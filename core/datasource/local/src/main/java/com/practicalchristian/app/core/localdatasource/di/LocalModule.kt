package com.practicalchristian.app.core.localdatasource.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.practicalchristian.app.core.localdatasource.database.PracticalChristianDatabase
import com.practicalchristian.app.core.localdatasource.sources.book.BookDao
import com.practicalchristian.app.core.localdatasource.sources.notes.NotesDao
import com.practicalchristian.app.core.localdatasource.sources.schedule.ScheduleDao
import com.practicalchristian.app.core.localdatasource.sources.schedule_entry.ScheduleEntryDao
import com.practicalchristian.app.core.localdatasource.sources.tags.TagsDao
import com.practicalchristian.app.core.localdatasource.workers.startPopulateBibleBooksWork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun providesRoomDatabaseCallback(
        @ApplicationContext context: Context
    ): RoomDatabase.Callback = object : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            context.startPopulateBibleBooksWork()
        }
    }

    @Provides
    @Singleton
    fun providesDatabase(
        @ApplicationContext context: Context,
        callback: RoomDatabase.Callback,
    ): PracticalChristianDatabase =
        Room.databaseBuilder(
            context = context,
            klass = PracticalChristianDatabase::class.java,
            name = PracticalChristianDatabase.DATABASE_NAME
        )
            .addCallback(callback)
            .build()

    @Provides
    @Singleton
    fun providesBookBao(database: PracticalChristianDatabase): BookDao = database.getBooksDao()

    @Provides
    fun providesScheduleDao(database: PracticalChristianDatabase): ScheduleDao = database.getScheduleDao()

    @Provides
    fun providesScheduleEntryDao(database: PracticalChristianDatabase): ScheduleEntryDao =
        database.getScheduleEntryDao()

    @Provides
    fun providesTagsDao(database: PracticalChristianDatabase): TagsDao =
        database.getTagsDao()

    @Provides
    fun providesNotesDao(database: PracticalChristianDatabase): NotesDao =
        database.getNotesDao()
}
