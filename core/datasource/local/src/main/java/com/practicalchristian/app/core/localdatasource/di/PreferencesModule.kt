package com.practicalchristian.app.core.localdatasource.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.practicalchristian.app.core.localdatasource.preferences.auth.AuthenticationPreferences
import com.practicalchristian.app.core.localdatasource.preferences.auth.AuthenticationPreferencesImpl
import com.practicalchristian.app.core.localdatasource.preferences.source.PreferenceSource
import com.practicalchristian.app.core.localdatasource.preferences.source.PreferenceSourceImpl
import com.practicalchristian.app.core.localdatasource.preferences.user.UserPreferences
import com.practicalchristian.app.core.localdatasource.preferences.user.UserPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferences")

    @Provides
    fun providesDataStore(@ApplicationContext context: Context): DataStore<Preferences> =
        context.dataStore

    @Provides
    fun providesPreferenceSource(dataStore: DataStore<Preferences>): PreferenceSource =
        PreferenceSourceImpl(dataStore = dataStore)

    @Provides
    fun providesUserPreferences(preferenceSource: PreferenceSource): UserPreferences =
        UserPreferencesImpl(source = preferenceSource)

    @Provides
    fun providesAuthenticationPreferences(preferenceSource: PreferenceSource): AuthenticationPreferences =
        AuthenticationPreferencesImpl(source = preferenceSource)
}
