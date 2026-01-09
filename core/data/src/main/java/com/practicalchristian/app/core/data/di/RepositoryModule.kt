package com.practicalchristian.app.core.data.di

import com.practicalchristian.app.core.data.repository.AuthenticationRepositoryImpl
import com.practicalchristian.app.core.data.repository.BooksRepositoryImpl
import com.practicalchristian.app.core.data.repository.NoteRepositoryImpl
import com.practicalchristian.app.core.data.repository.PreferencesRepositoryImpl
import com.practicalchristian.app.core.data.repository.ScheduleRepositoryImpl
import com.practicalchristian.app.core.data.repository.TagsRepositoryImpl
import com.practicalchristian.app.core.domain.repository.AuthenticationRepository
import com.practicalchristian.app.core.domain.repository.BooksRepository
import com.practicalchristian.app.core.domain.repository.NotesRepository
import com.practicalchristian.app.core.domain.repository.PreferencesRepository
import com.practicalchristian.app.core.domain.repository.ScheduleRepository
import com.practicalchristian.app.core.domain.repository.TagsRepository
import com.practicalchristian.app.core.localdatasource.preferences.auth.AuthenticationPreferences
import com.practicalchristian.app.core.localdatasource.preferences.user.UserPreferences
import com.practicalchristian.app.core.localdatasource.sources.book.BookSources
import com.practicalchristian.app.core.localdatasource.sources.notes.NotesSource
import com.practicalchristian.app.core.localdatasource.sources.schedule.ScheduleSource
import com.practicalchristian.app.core.localdatasource.sources.schedule_entry.ScheduleEntrySource
import com.practicalchristian.app.core.localdatasource.sources.tags.TagsSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providesBooksRepository(sources: BookSources): BooksRepository =
        BooksRepositoryImpl(sources = sources)

    @Provides
    fun providesPreferencesRepository(
        preferences: UserPreferences
    ): PreferencesRepository =
        PreferencesRepositoryImpl(userPreferences = preferences)

    @Provides
    fun providesAuthenticationRepository(
        authenticationPreferences: AuthenticationPreferences
    ): AuthenticationRepository = AuthenticationRepositoryImpl(
        authenticationPreferences = authenticationPreferences
    )

    @Provides
    fun providesScheduleRepository(
        scheduleSource: ScheduleSource,
        scheduleEntrySource: ScheduleEntrySource
    ): ScheduleRepository = ScheduleRepositoryImpl(
        scheduleSource = scheduleSource,
        scheduleEntrySource = scheduleEntrySource
    )

    @Provides
    fun providesTagsRepository(
        tagsSource: TagsSource
    ): TagsRepository = TagsRepositoryImpl(tagsSource = tagsSource)

    @Provides
    fun providesNotesRepository(
        notesSource: NotesSource,
        tagsSource: TagsSource,
        bookSources: BookSources,
    ): NotesRepository = NoteRepositoryImpl(
        notesSource = notesSource,
        tagsSource = tagsSource,
        bookSources = bookSources
    )
}
