package com.practicalchristian.app.core.localdatasource.preferences.source

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface PreferenceSource {
    fun <T> getNullable(key: Preferences.Key<T>): Flow<T?>

    fun <T> get(key: Preferences.Key<T>, default: T): Flow<T>

    suspend fun <T> save(key: Preferences.Key<T>, value: T)

    suspend fun <T> update(key: Preferences.Key<T>, value: T)

    suspend fun <T> delete(key: Preferences.Key<T>)

    suspend fun clear()
}
