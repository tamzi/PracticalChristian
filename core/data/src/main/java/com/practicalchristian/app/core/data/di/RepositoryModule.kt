package com.practicalchristian.app.core.data.di

import com.practicalchristian.app.core.data.repositories.AuthenticationRepositoryImpl
import com.practicalchristian.app.core.data.repositories.BooksRepositoryImpl
import com.practicalchristian.app.core.data.repositories.NoteRepositoryImpl
import com.practicalchristian.app.core.data.repositories.PreferencesRepositoryImpl
import com.practicalchristian.app.core.data.repositories.ScheduleRepositoryImpl
import com.practicalchristian.app.core.data.repositories.TagsRepositoryImpl
import com.practicalchristian.app.core.domain.repositories.AuthenticationRepository
import com.practicalchristian.app.core.domain.repositories.BooksRepository
import com.practicalchristian.app.core.domain.repositories.NotesRepository
import com.practicalchristian.app.core.domain.repositories.PreferencesRepository
import com.practicalchristian.app.core.domain.repositories.ScheduleRepository
import com.practicalchristian.app.core.domain.repositories.TagsRepository
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
