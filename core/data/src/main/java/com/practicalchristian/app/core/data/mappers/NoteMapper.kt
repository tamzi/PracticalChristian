@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.core.data.mappers

import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.NoteBook
import com.practicalchristian.app.core.domain.models.NoteDomain
import com.practicalchristian.app.core.domain.models.NoteType
import com.practicalchristian.app.core.localdatasource.entity.NoteCache
import com.practicalchristian.app.core.localdatasource.helpers.datetime
import com.practicalchristian.app.core.localdatasource.helpers.toInstant
import com.practicalchristian.app.core.localdatasource.helpers.instant
import kotlin.time.ExperimentalTime

/**
 * Converts a NoteCache entity from the database to a NoteDomain model.
 *
 * **Timezone handling:**
 * - Database stores timestamps as Long (milliseconds since epoch in UTC)
 * - Converts to LocalDateTime in UTC for domain layer
 * - Domain layer maintains UTC timestamps for server sync consistency
 */
fun NoteCache.toDomain(): NoteDomain = NoteDomain(
    id = id,
    createdAt = createdAt.toInstant().datetime,
    updatedAt = updatedAt.toInstant().datetime,
    pinnedAt = pinnedAt?.toInstant()?.datetime,
    title = title,
    content = content,
    tags = listOf(),
    type = NoteType.valueOf(type),
    start = NoteBook(
        book = Book(id = startBook, name = "", chapters = 1),
        chapter = startChapter,
        verse = startVerse
    ),
    end = NoteBook(
        book = Book(id = endBook, name = "", chapters = 1),
        chapter = endChapter,
        verse = endVerse
    )
)

/**
 * Converts a NoteDomain model to a NoteCache entity for database storage.
 *
 * **Timezone handling:**
 * - Domain layer stores timestamps as LocalDateTime in UTC
 * - Converts to Long (milliseconds since epoch) for database storage
 * - Maintains UTC throughout: no timezone conversion occurs
 * - Ready for server synchronization without timezone offset issues
 */
fun NoteDomain.toCache(): NoteCache = NoteCache(
    id = id,
    createdAt = createdAt.instant.toEpochMilliseconds(),
    updatedAt = updatedAt.instant.toEpochMilliseconds(),
    pinnedAt = pinnedAt?.instant?.toEpochMilliseconds(),
    title = title,
    content = content,
    tags = listOf(),
    type = type.name,
    startBook = start.book.id,
    startChapter = start.chapter,
    startVerse = start.verse,
    endBook = end.book.id,
    endChapter = end.chapter,
    endVerse = end.verse,
)
