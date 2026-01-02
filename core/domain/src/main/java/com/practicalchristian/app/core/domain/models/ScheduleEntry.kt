package com.practicalchristian.app.core.domain.models

import kotlinx.datetime.LocalDateTime

/**
 * Represents a completed schedule entry.
 *
 * @property id Unique identifier for the entry
 * @property scheduleId Reference to the associated schedule
 * @property date Completion timestamp. **Stored in UTC** for consistency across devices and server synchronization.
 *                Use [toLocalTimezone()] for UI display in the user's local timezone.
 */
data class ScheduleEntry(
    val id: String,
    val scheduleId: Int,
    val date: LocalDateTime
)
