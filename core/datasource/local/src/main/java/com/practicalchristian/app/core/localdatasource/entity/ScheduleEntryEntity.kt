@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.core.localdatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practicalchristian.app.core.localdatasource.helpers.BaseCache
import com.practicalchristian.app.core.localdatasource.helpers.BaseEntity
import com.practicalchristian.app.core.localdatasource.helpers.datetime
import com.practicalchristian.app.core.localdatasource.helpers.instant
import com.practicalchristian.app.core.localdatasource.helpers.toInstant
import kotlinx.datetime.LocalDateTime
import java.util.UUID
import kotlin.time.ExperimentalTime

/**
 * Database entity for schedule completion entries.
 *
 * **Timezone handling:**
 * - `completedAt` is stored as Long (milliseconds since epoch in UTC)
 * - Conversion to LocalDateTime UTC happens via `toInstant().datetime`
 * - Conversion from LocalDateTime UTC happens via `instant.toEpochMilliseconds()`
 */
@Entity(tableName = "schedule_entries")
data class ScheduleEntryEntity(
    @PrimaryKey
    val entryId: String = UUID.randomUUID().toString(),
    val scheduleId: Int,
    val completedAt: Long // UTC milliseconds
) : BaseEntity<ScheduleEntryCache> {

    override fun toCache() = ScheduleEntryCache(
        id = entryId,
        scheduleId = scheduleId,
        completedAt = completedAt.toInstant().datetime // Convert to UTC LocalDateTime
    )
}

/**
 * Cache representation of a schedule entry with UTC LocalDateTime.
 */
data class ScheduleEntryCache(
    val id: String = "",
    val scheduleId: Int,
    val completedAt: LocalDateTime // UTC LocalDateTime
) : BaseCache<ScheduleEntryEntity> {

    override fun toEntity() = ScheduleEntryEntity(
        scheduleId = scheduleId,
        completedAt = completedAt.instant.toEpochMilliseconds() // Convert to UTC millis
    )
}
