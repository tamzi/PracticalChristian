package com.practicalchristian.app.core.localdatasource.preferences.auth

import androidx.datastore.preferences.core.booleanPreferencesKey
import com.practicalchristian.app.core.localdatasource.preferences.source.PreferenceSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationPreferencesImpl @Inject constructor(
    private val source: PreferenceSource
) : AuthenticationPreferences {

    private object Keys {
        val USER_HAS_SIGNED_IN = booleanPreferencesKey("USER_HAS_SIGNED_IN")
        val USER_HAS_SETUP = booleanPreferencesKey("USER_HAS_SETUP")
    }

    override val userHasSignedIn: Flow<Boolean>
        get() = source.get(key = Keys.USER_HAS_SIGNED_IN, default = false)

    override val userHasSetup: Flow<Boolean>
        get() = source.get(key = Keys.USER_HAS_SETUP, default = false)

    override suspend fun setUserHasSignedIn(value: Boolean) {
        source.update(key = Keys.USER_HAS_SIGNED_IN, value = value)
    }

    override suspend fun setUserHasSetup(value: Boolean) {
        source.update(key = Keys.USER_HAS_SETUP, value = value)
    }
}
