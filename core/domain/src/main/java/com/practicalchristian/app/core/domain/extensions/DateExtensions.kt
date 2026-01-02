@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.practicalchristian.app.core.domain.extensions

import kotlin.time.Clock
import kotlin.time.Instant
import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.atStartOfDayIn

/**
 * We persist timestamps in UTC to be deterministic cross-device/server.
 * Convert to the user's local zone only for presentation.
 */
private val UTC: TimeZone = TimeZone.UTC

// Insatnt → calndar parts (UTC by defulat)

val Instant.date: LocalDate
    get() = toLocalDateTime(UTC).date

val Instant.datetime: LocalDateTime
    get() = toLocalDateTime(UTC)

val Instant.time: LocalTime
    get() = toLocalDateTime(UTC).time

val Instant.localDatetime: LocalDateTime
    get() = toLocalDateTime(TimeZone.currentSystemDefault())

// Calendr → Instant

/** Start-of-day Instant for this LocalDate in the given zone (UTC by default). */
fun LocalDate.getInstant(timeZone: TimeZone = UTC): Instant =
    atStartOfDayIn(timeZone)

/** LocalDateTime → Instant in the given zone (UTC by default). */
fun LocalDateTime.getInstant(timeZone: TimeZone = UTC): Instant =
    toInstant(timeZone)

/**
 * ✅ NEW: Treat this LocalDateTime as **UTC** and convert to an Instant.
 * Matches how your ScheduleDomain stores scheduled dates.
 */
val LocalDateTime.instant: Instant
    get() = toInstant(UTC)

/** ✅ NEW: Convenience for LocalDate at start of day in UTC. */
val LocalDate.instant: Instant
    get() = atStartOfDayIn(UTC)

// Zone conversions for stored (UTC) LocalDateTime

fun LocalDateTime.toLocalTimezone(): LocalDateTime =
    toInstant(UTC).toLocalDateTime(TimeZone.currentSystemDefault())

fun LocalDateTime.toUtc(): LocalDateTime =
    toInstant(TimeZone.currentSystemDefault()).toLocalDateTime(UTC)

// Epoch helpers

fun Long.toInstant(): Instant = Instant.fromEpochMilliseconds(this)
fun Long.toLocalDate(): LocalDate = Instant.fromEpochMilliseconds(this).toLocalDateTime(UTC).date

// Simple temporal perdiactes

val Instant.isPast: Boolean
    get() = this < Clock.System.now()

val Instant.isFuture: Boolean
    get() = this > Clock.System.now()

val Instant.isToday: Boolean
    get() {
        val tz = TimeZone.currentSystemDefault()
        val nowDate = Clock.System.now().toLocalDateTime(tz).date
        return toLocalDateTime(tz).date == nowDate
    }

// Optional alternative fixed UTC instance (not required)
@Suppress("unused")
private val FIXED_UTC = FixedOffsetTimeZone(UtcOffset.ZERO)
