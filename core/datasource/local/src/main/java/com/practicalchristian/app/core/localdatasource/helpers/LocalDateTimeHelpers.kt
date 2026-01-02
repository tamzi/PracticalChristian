@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.core.localdatasource.helpers

import kotlinx.datetime.FixedOffsetTimeZone
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import java.util.Date
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

/**
 * UTC timezone used for local database storage.
 *
 * **Why UTC in local storage?**
 * - Maintains consistency with server timestamps
 * - Prevents timezone conversion errors during sync
 * - Allows proper sorting and comparison of timestamps
 * - Follows the principle: store UTC, display in local time
 *
 * **Important:** All database timestamps are stored as UTC milliseconds.
 * These extensions handle conversion between Long (millis) and LocalDateTime (UTC).
 */
private val TIMEZONE = FixedOffsetTimeZone(UtcOffset.ZERO)

fun Date.toLocalDateTime(): LocalDateTime =
    Instant.fromEpochMilliseconds(this.time).toLocalDateTime(FixedOffsetTimeZone(UtcOffset.ZERO))

// INSTANT EXTENSIONS

/**
 * Converts an Instant to LocalDate in UTC timezone.
 * Used for database storage and domain layer.
 */
val Instant.date: LocalDate
    get() = toLocalDateTime(FixedOffsetTimeZone(UtcOffset.ZERO)).date

/**
 * Converts an Instant to LocalDateTime in UTC timezone.
 * Used when reading timestamps from database (Long millis -> LocalDateTime UTC).
 */
val Instant.datetime: LocalDateTime
    get() = toLocalDateTime(TIMEZONE)

/**
 * Converts an Instant to LocalTime in UTC timezone.
 * Used for time-based queries and filtering.
 */
val Instant.time: LocalTime
    get() = toLocalDateTime(FixedOffsetTimeZone(UtcOffset.ZERO)).time

fun LocalDate.getInstant(timeZone: TimeZone = TimeZone.UTC): Instant =
    LocalDateTime(year, month, day, 0, 0, 0, 0)
        .toInstant(timeZone)

// LOCAL DATETIME EXTENSIONS

fun LocalDateTime.getInstant(timeZone: TimeZone = TimeZone.UTC): Instant =
    LocalDateTime(year, month, day, 0, 0, 0, 0)
        .toInstant(timeZone)

/**
 * Converts LocalDateTime to Instant assuming UTC timezone.
 * Used when writing timestamps to database (LocalDateTime UTC -> Long millis).
 *
 * **Important:** This assumes the LocalDateTime is in UTC, which is correct
 * for all domain models as they store UTC timestamps.
 */
val LocalDateTime.instant: Instant
    get() = LocalDateTime(year, month, day, hour, minute, second, nanosecond)
        .toInstant(TIMEZONE)

// LONG CONVERTERS
fun Long.toInstant(): Instant = Instant.fromEpochMilliseconds(this)

fun Long.toLocalDate(): LocalDate = toInstant().date
