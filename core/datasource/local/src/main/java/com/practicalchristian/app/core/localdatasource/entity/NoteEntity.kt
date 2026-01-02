@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.core.localdatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practicalchristian.app.core.localdatasource.helpers.BaseCache
import com.practicalchristian.app.core.localdatasource.helpers.BaseEntity
import java.util.UUID
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * Database entity for notes.
 *
 * **Timezone handling:**
 * - `createdAt`, `updatedAt`, and `pinnedAt` are stored as Long (milliseconds since epoch in UTC)
 * - Default values use `Clock.System.now().toEpochMilliseconds()` which produces UTC timestamps
 * - Conversion to LocalDateTime UTC happens in the mapper layer via `toInstant().datetime`
 */
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(), // UTC milliseconds
    val updatedAt: Long = Clock.System.now().toEpochMilliseconds(), // UTC milliseconds
    val pinnedAt: Long? = null, // UTC milliseconds
    val title: String,
    val content: String,
    val tags: List<String> = listOf(),
    val type: String,
    val startBook: Int,
    val startChapter: Int,
    val startVerse: Int,
    val endBook: Int,
    val endChapter: Int,
    val endVerse: Int,
) : BaseEntity<NoteCache> {

    override fun toCache() = NoteCache(
        id,
        createdAt,
        updatedAt,
        pinnedAt,
        title,
        content,
        tags,
        type,
        startBook,
        startChapter,
        startVerse,
        endBook,
        endChapter,
        endVerse
    )
}

/**
 * Cache representation of a note.
 *
 * **Timezone handling:**
 * - Timestamps are stored as Long (UTC milliseconds) to match database representation
 * - Conversion to domain LocalDateTime UTC happens in NoteMapper
 */
data class NoteCache(
    val id: String = UUID.randomUUID().toString(),
    val createdAt: Long = Clock.System.now().toEpochMilliseconds(), // UTC milliseconds
    val updatedAt: Long = Clock.System.now().toEpochMilliseconds(), // UTC milliseconds
    val pinnedAt: Long? = null, // UTC milliseconds
    val title: String,
    val content: String,
    val tags: List<String> = listOf(),
    val type: String,
    val startBook: Int,
    val startChapter: Int,
    val startVerse: Int,
    val endBook: Int,
    val endChapter: Int,
    val endVerse: Int,
) : BaseCache<NoteEntity> {

    override fun toEntity() = NoteEntity(
        id,
        createdAt,
        updatedAt,
        pinnedAt,
        title,
        content,
        tags,
        type,
        startBook,
        startChapter,
        startVerse,
        endBook,
        endChapter,
        endVerse
    )
}
