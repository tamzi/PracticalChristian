@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.practicalchristian.app.core.domain.models

import com.practicalchristian.app.core.domain.extensions.datetime
import com.practicalchristian.app.core.domain.extensions.sentence
import com.practicalchristian.app.core.domain.extensions.toLocalTimezone
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number
import kotlin.time.Clock

data class NoteDomain(
    val id: String,
    /**
     * UTC timestamps for persistence; convert to local zone for UI.
     */
    val createdAt: LocalDateTime = Clock.System.now().datetime,
    val updatedAt: LocalDateTime = Clock.System.now().datetime,
    val pinnedAt: LocalDateTime? = null,
    val title: String,
    val content: String,
    val tags: List<TagDomain>,
    val type: NoteType,
    val start: NoteBook,
    val end: NoteBook
) {
    constructor(
        title: String,
        content: String,
        tags: List<TagDomain>,
        type: NoteType,
        start: NoteBook,
        end: NoteBook
    ) : this(
        id = java.util.UUID.randomUUID().toString(),
        title = title,
        content = content,
        tags = tags,
        type = type,
        start = start,
        end = end
    )

    val displayBook: String
        get() = buildString {
            append(start.book.name.sentence)
            append(" ")
            append(start.chapter)
            append(":")
            append(start.verse)
            // If later you want cross-range like "Gen 1:1–2:3", handle here when end differs
        }

    /**
     * UI-friendly date in user's local zone.
     * Uses kotlinx-datetime 0.7.x field names: LocalDate.day & Month.number.
     */
    val displayDate: String
        get() {
            val local = updatedAt.toLocalTimezone()
            return buildString {
                append(local.date.day) // day-of-month
                append("/")
                append(local.date.month.number) // numeric month
                append("/")
                append(local.year)
            }
        }
}
