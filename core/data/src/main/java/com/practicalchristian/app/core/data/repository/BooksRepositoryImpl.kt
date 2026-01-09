package com.practicalchristian.app.core.data.repository

import com.practicalchristian.app.core.data.mappers.toDomain
import com.practicalchristian.app.core.data.mappers.toDomainError
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.repository.BooksRepository
import com.practicalchristian.app.core.localdatasource.entity.BookCache
import com.practicalchristian.app.core.localdatasource.helpers.LocalResult
import com.practicalchristian.app.core.localdatasource.sources.book.BookSources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val sources: BookSources
) : BooksRepository {

    override val books: Flow<List<Book>>
        get() = sources.books.mapLatest { list -> list.map(BookCache::toDomain) }

    override suspend fun getBookById(id: Int): Outcome<Flow<Book?>> {
        return when (val result = sources.getBookById(id = id)) {
            is LocalResult.Error -> Outcome.Failure(error = result.message.toDomainError())
            is LocalResult.Success -> Outcome.Success(value = result.data.map { it?.toDomain() })
        }
    }
}
