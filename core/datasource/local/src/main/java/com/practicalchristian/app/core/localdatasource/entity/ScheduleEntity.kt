package com.practicalchristian.app.core.localdatasource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practicalchristian.app.core.localdatasource.helpers.BaseCache
import com.practicalchristian.app.core.localdatasource.helpers.BaseEntity

/**
 * Database entity for reading schedules.
 *
 * **Timezone handling:**
 * - `date` is stored as Long (milliseconds since epoch in UTC)
 * - Conversion to/from LocalDateTime UTC happens in the cache layer
 */
@Entity(tableName = "schedules")
data class ScheduleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val startBook: Int,
    val startChapter: Int,
    val endBook: Int,
    val endChapter: Int,
    val date: Long, // UTC milliseconds
) : BaseEntity<ScheduleCache> {

    override fun toCache() = ScheduleCache(
        id = id,
        date = date,
        start = ScheduleCacheItem(book = startBook, chapter = startChapter),
        end = ScheduleCacheItem(book = endBook, chapter = endChapter),
    )
}

data class ScheduleCacheItem(val book: Int, val chapter: Int)

data class ScheduleCache(
    val id: Int = 0,
    val date: Long, // UTC milliseconds
    val start: ScheduleCacheItem,
    val end: ScheduleCacheItem
) : BaseCache<ScheduleEntity> {

    override fun toEntity() = ScheduleEntity(
        id = id,
        startBook = start.book,
        startChapter = start.chapter,
        endBook = end.book,
        endChapter = end.chapter,
        date = date
    )
}
