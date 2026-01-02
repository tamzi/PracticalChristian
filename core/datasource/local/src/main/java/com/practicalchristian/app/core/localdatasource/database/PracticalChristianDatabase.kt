package com.practicalchristian.app.core.localdatasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.practicalchristian.app.core.localdatasource.converters.ListConverters
import com.practicalchristian.app.core.localdatasource.entity.BookEntity
import com.practicalchristian.app.core.localdatasource.entity.NoteEntity
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntity
import com.practicalchristian.app.core.localdatasource.entity.ScheduleEntryEntity
import com.practicalchristian.app.core.localdatasource.entity.TagEntity
import com.practicalchristian.app.core.localdatasource.sources.book.BookDao
import com.practicalchristian.app.core.localdatasource.sources.notes.NotesDao
import com.practicalchristian.app.core.localdatasource.sources.schedule.ScheduleDao
import com.practicalchristian.app.core.localdatasource.sources.schedule_entry.ScheduleEntryDao
import com.practicalchristian.app.core.localdatasource.sources.tags.TagsDao

@Database(
    entities = [
        BookEntity::class, ScheduleEntity::class, ScheduleEntryEntity::class,
        TagEntity::class, NoteEntity::class
    ],
    exportSchema = false,
    version = 1
)
@TypeConverters(
    ListConverters::class
)
abstract class PracticalChristianDatabase : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "practical_christian_database"
    }

    abstract fun getBooksDao(): BookDao

    abstract fun getScheduleDao(): ScheduleDao

    abstract fun getScheduleEntryDao(): ScheduleEntryDao

    abstract fun getTagsDao(): TagsDao

    abstract fun getNotesDao(): NotesDao
}
