package com.practicalchristian.app.core.localdatasource.preferences.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class PreferenceSourceImpl(
    private val dataStore: DataStore<Preferences>
) : PreferenceSource {
    override fun <T> getNullable(key: Preferences.Key<T>): Flow<T?> {
        return dataStore.data.map { it[key] }
    }

    override fun <T> get(key: Preferences.Key<T>, default: T): Flow<T> {
        return dataStore.data.map { it[key] ?: default }
    }

    override suspend fun <T> save(key: Preferences.Key<T>, value: T) {
        withContext(Dispatchers.IO) {
            dataStore.edit { it[key] = value }
        }
    }

    override suspend fun <T> update(key: Preferences.Key<T>, value: T) {
        withContext(Dispatchers.IO) {
            dataStore.edit { it[key] = value }
        }
    }

    override suspend fun <T> delete(key: Preferences.Key<T>) {
        withContext(Dispatchers.IO) {
            dataStore.edit { it.remove(key) }
        }
    }

    override suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataStore.edit { it.clear() }
        }
    }
}
