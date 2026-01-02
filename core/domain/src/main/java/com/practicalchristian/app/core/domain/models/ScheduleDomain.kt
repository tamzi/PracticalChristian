@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.practicalchristian.app.core.domain.models

import com.practicalchristian.app.core.domain.extensions.instant
import com.practicalchristian.app.core.domain.extensions.isFuture
import com.practicalchristian.app.core.domain.extensions.isPast
import com.practicalchristian.app.core.domain.extensions.isToday
import kotlinx.datetime.LocalDateTime

enum class ScheduleStatus {
    DONE_ON_DATE, DONE_OFF_DATE, PENDING_ON_DATE, PENDING_OFF_DATE, TODO;

    val label: String
        get() = when (this) {
            DONE_ON_DATE -> "Done"
            DONE_OFF_DATE -> "Done"
            PENDING_ON_DATE -> "Pending"
            PENDING_OFF_DATE -> "Pending"
            TODO -> "To-Do"
        }
}

data class ScheduleItem(val book: Book, val chapter: Int)

data class ScheduleDomain(
    val id: Int,
    /**
     * The scheduled date for this reading plan entry.
     * **Stored in UTC** for consistency across devices and server synchronization.
     * Use [toLocalTimezone()] for UI display in the user's local timezone.
     */
    val date: LocalDateTime,
    val start: ScheduleItem,
    val end: ScheduleItem,
    val entry: ScheduleEntry?,
) {

    private val isCorrectEntryDate: Boolean
        get() = date.date == entry?.date?.date

    val status: ScheduleStatus
        get() = when (date.instant.isToday) {
            true -> {
                if (isCorrectEntryDate)
                    ScheduleStatus.DONE_ON_DATE
                else
                    ScheduleStatus.PENDING_ON_DATE
            }

            false -> {
                when {
                    date.instant.isPast -> {
                        if (entry == null)
                            ScheduleStatus.PENDING_OFF_DATE
                        else
                            if (isCorrectEntryDate)
                                ScheduleStatus.DONE_ON_DATE
                            else
                                ScheduleStatus.DONE_OFF_DATE
                    }

                    date.instant.isFuture -> ScheduleStatus.TODO
                    else -> ScheduleStatus.TODO
                }
            }
        }

    val isComplete: Boolean
        get() = entry != null

    val isItemInTheFuture: Boolean
        get() = date.instant.isFuture

    val isSameBook: Boolean
        get() = start.book == end.book

    val isMoreThanOneBook: Boolean
        get() = (start.book - end.book) > 1
}
