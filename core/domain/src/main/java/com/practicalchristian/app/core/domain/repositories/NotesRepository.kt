package com.practicalchristian.app.core.domain.repositories

import com.practicalchristian.app.core.domain.models.NoteDomain
import com.practicalchristian.app.core.domain.models.NoteType
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.TagDomain
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    val notes: Flow<List<NoteDomain>>

    suspend fun searchNotes(query: String): Outcome<Flow<List<NoteDomain>>>

    suspend fun insert(
        title: String,
        content: String,
        type: NoteType,
        tags: List<TagDomain>,
        startBook: Int,
        startChapter: Int,
        startVerse: Int,
        endBook: Int,
        endChapter: Int,
        endVerse: Int,
    ): Outcome<Boolean>

    suspend fun update(note: NoteDomain): Outcome<Boolean>

    suspend fun delete(note: NoteDomain): Outcome<Boolean>

    suspend fun getNoteById(id: String): Outcome<Flow<NoteDomain>>
}
