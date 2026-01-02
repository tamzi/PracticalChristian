package com.practicalchristian.app.core.data.mappers

import com.practicalchristian.app.core.domain.models.ScheduleEntry
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryCache

/**
 * Converts a ScheduleEntryCache to domain model.
 *
 * **Timezone handling:**
 * - Cache layer stores completedAt as UTC LocalDateTime
 * - Passes through to domain layer maintaining UTC
 */
fun ScheduleEntryCache.toDomain() = ScheduleEntry(
    id = id,
    scheduleId = scheduleId,
    date = completedAt
)

/**
 * Converts a ScheduleEntry domain model to cache.
 *
 * **Timezone handling:**
 * - Domain layer date is in UTC
 * - Stores in cache as UTC LocalDateTime
 */
fun ScheduleEntry.toCache() = ScheduleEntryCache(
    id = id,
    scheduleId = scheduleId,
    completedAt = date
)
