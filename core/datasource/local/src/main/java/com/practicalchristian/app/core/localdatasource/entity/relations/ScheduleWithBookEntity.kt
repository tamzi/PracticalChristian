@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.core.localdatasource.entity.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.practicalchristian.app.core.localdatasource.entity.BookCache
import com.practicalchristian.app.core.localdatasource.entity.BookEntity
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntity
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryCache
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryEntity
import com.practicalchristian.app.core.localdatasource.helpers.datetime
import com.practicalchristian.app.core.localdatasource.helpers.toInstant
import kotlinx.datetime.LocalDateTime
import kotlin.time.ExperimentalTime

/**
 * Room relation combining schedule with its associated books and optional completion entry.
 *
 * **Timezone handling:**
 * - Converts schedule date from Long (UTC millis) to LocalDateTime (UTC)
 * - Entry completedAt is already converted by ScheduleEntryEntity.toCache()
 */
data class ScheduleWithBookEntity(
    @Embedded val scheduleEntity: ScheduleEntity,
    @Relation(
        parentColumn = "startBook",
        entityColumn = "id"
    )
    val startBook: BookEntity,
    @Relation(
        parentColumn = "endBook",
        entityColumn = "id"
    )
    val endBook: BookEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "scheduleId"
    )
    val entry: ScheduleEntryEntity?
) {

    fun toCache(): ScheduleWithBookCache = ScheduleWithBookCache(
        id = scheduleEntity.id,
        date = scheduleEntity.date.toInstant().datetime, // Convert Long (UTC millis) → LocalDateTime (UTC)
        startBook = startBook.toCache(),
        startChapter = scheduleEntity.startChapter,
        endBook = endBook.toCache(),
        endChapter = scheduleEntity.endChapter,
        entry = entry?.toCache() // Already UTC LocalDateTime
    )
}

/**
 * Cache representation with UTC LocalDateTime for the date.
 */
data class ScheduleWithBookCache(
    val id: Int,
    val date: LocalDateTime, // UTC LocalDateTime
    val startBook: BookCache,
    val startChapter: Int,
    val endBook: BookCache,
    val endChapter: Int,
    val entry: ScheduleEntryCache?
)
