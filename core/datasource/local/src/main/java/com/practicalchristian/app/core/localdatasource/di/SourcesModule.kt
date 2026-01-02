package com.practicalchristian.app.core.localdatasource.di

import com.practicalchristian.app.core.localdatasource.sources.book.BookDao
import com.practicalchristian.app.core.localdatasource.sources.book.BookSources
import com.practicalchristian.app.core.localdatasource.sources.book.BookSourcesImpl
import com.practicalchristian.app.core.localdatasource.sources.notes.NotesDao
import com.practicalchristian.app.core.localdatasource.sources.notes.NotesSource
import com.practicalchristian.app.core.localdatasource.sources.notes.NotesSourceImpl
import com.practicalchristian.app.core.localdatasource.sources.schedule.ScheduleDao
import com.practicalchristian.app.core.localdatasource.sources.schedule.ScheduleSource
import com.practicalchristian.app.core.localdatasource.sources.schedule.ScheduleSourceImpl
import com.practicalchristian.app.core.localdatasource.sources.schedule_entry.ScheduleEntryDao
import com.practicalchristian.app.core.localdatasource.sources.schedule_entry.ScheduleEntrySource
import com.practicalchristian.app.core.localdatasource.sources.schedule_entry.ScheduleEntrySourceImpl
import com.practicalchristian.app.core.localdatasource.sources.tags.TagsDao
import com.practicalchristian.app.core.localdatasource.sources.tags.TagsSource
import com.practicalchristian.app.core.localdatasource.sources.tags.TagsSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Provides
    fun providesBooksSources(dao: BookDao): BookSources = BookSourcesImpl(dao = dao)

    @Provides
    fun providesScheduleSource(dao: ScheduleDao): ScheduleSource = ScheduleSourceImpl(dao = dao)

    @Provides
    fun providesScheduleEntrySource(dao: ScheduleEntryDao): ScheduleEntrySource =
        ScheduleEntrySourceImpl(dao = dao)

    @Provides
    fun providesTagsDao(dao: TagsDao): TagsSource = TagsSourceImpl(dao = dao)

    @Provides
    fun providesNotesSource(dao: NotesDao): NotesSource = NotesSourceImpl(dao = dao)
}
