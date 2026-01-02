package com.practicalchristian.app.core.data.mappers

import com.practicalchristian.app.core.domain.models.ScheduleDomain
import com.practicalchristian.app.core.domain.models.ScheduleItem
import com.practicalchristian.app.core.localdatasource.entity.relations.ScheduleWithBookCache

/**
 * Converts a ScheduleWithBookCache to domain model.
 *
 * **Timezone handling:**
 * - Cache layer stores date as UTC LocalDateTime (converted from Long millis in entity)
 * - Passes through to domain layer maintaining UTC
 * - Entry completion date is also in UTC
 */
fun ScheduleWithBookCache.toDomain() =
    ScheduleDomain(
        id = id,
        date = date,
        start = ScheduleItem(book = startBook.toDomain(), chapter = startChapter),
        end = ScheduleItem(book = endBook.toDomain(), chapter = endChapter),
        entry = entry?.toDomain()
    )

// fun ScheduleCache.toDomain() =
//    Schedule(
//        id = id,
//        date = date.toInstant().date,
//        start = ScheduleItem(book = start.book, chapter = start.chapter),
//        end = ScheduleItem(book = end.book, chapter = end.chapter),
//    )
//
// fun Schedule.toCache() =
//    ScheduleCache(
//        id = id,
//        date = date.getInstant().toEpochMilliseconds(),
//        start = ScheduleCacheItem(book = start.book, chapter = start.chapter),
//        end = ScheduleCacheItem(book = end.book, chapter = end.chapter),
//    )
