package com.practicalchristian.app.core.data.repositories

import com.practicalchristian.app.core.data.mappers.toCache
import com.practicalchristian.app.core.data.mappers.toDomain
import com.practicalchristian.app.core.data.mappers.toOutcome
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.NoteDomain
import com.practicalchristian.app.core.domain.models.NoteType
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.domain.repositories.NotesRepository
import com.practicalchristian.app.core.localdatasource.entity.NoteCache
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.sources.book.BookSources
import com.practicalchristian.app.core.localdatasource.sources.notes.NotesSource
import com.practicalchristian.app.core.localdatasource.sources.tags.TagsSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val notesSource: NotesSource,
    private val tagsSource: TagsSource,
    private val bookSources: BookSources
) : NotesRepository {

    override val notes: Flow<List<NoteDomain>>
        get() = notesSource.notes.mapLatest { list ->
            list.map { cache ->
                val tags = mutableListOf<TagDomain>()
                cache.tags.forEach {
                    when (val result = tagsSource.search(query = it)) {
                        is LocalResult.Error -> {}
                        is LocalResult.Success -> {
                            val domains = result.data.first().map { it.toDomain() }
                            tags.addAll(domains)
                        }
                    }
                }
                val result = bookSources.getBookById(id = cache.startBook)
                val book = when (result) {
                    is LocalResult.Error -> null
                    is LocalResult.Success -> result.data.first()
                }?.toDomain() ?: Book(id = cache.startBook, name = "", chapters = 1)
                val domain = cache.toDomain()
                domain.copy(
                    tags = tags,
                    start = domain.start.copy(book = book),
                    end = domain.start.copy(book = book),
                )
            }
        }

    override suspend fun searchNotes(query: String): Outcome<Flow<List<NoteDomain>>> {
        val result = notesSource.search(query = query)
        return result.toOutcome { data ->
            data.mapLatest { list -> list.map { it.toDomain() } }
        }
    }

    override suspend fun insert(
        title: String,
        content: String,
        type: NoteType,
        tags: List<TagDomain>,
        startBook: Int,
        startChapter: Int,
        startVerse: Int,
        endBook: Int,
        endChapter: Int,
        endVerse: Int
    ): Outcome<Boolean> {
        val item = NoteCache(
            title = title,
            content = content,
            type = type.name,
            tags = tags.map { it.name },
            startBook = startBook,
            startChapter = startChapter,
            startVerse = startVerse,
            endBook = endBook,
            endChapter = endChapter,
            endVerse = endVerse,
        )
        val result = notesSource.insert(item = item)
        return result.toOutcome {
            true
        }
    }

    override suspend fun update(note: NoteDomain): Outcome<Boolean> {
        val result = notesSource.update(item = note.toCache())
        return result.toOutcome()
    }

    override suspend fun delete(note: NoteDomain): Outcome<Boolean> {
        val result = notesSource.delete(item = note.toCache())
        return result.toOutcome()
    }

    override suspend fun getNoteById(id: String): Outcome<Flow<NoteDomain>> {
        return notesSource.getFlow(id = id).toOutcome { it.mapLatest { it.toDomain() } }
    }
}
