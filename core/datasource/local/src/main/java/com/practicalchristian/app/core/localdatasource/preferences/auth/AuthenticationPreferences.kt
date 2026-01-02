package com.practicalchristian.app.core.localdatasource.preferences.auth

import kotlinx.coroutines.flow.Flow

interface AuthenticationPreferences {

    val userHasSignedIn: Flow<Boolean>
    val userHasSetup: Flow<Boolean>

    suspend fun setUserHasSignedIn(value: Boolean)
    suspend fun setUserHasSetup(value: Boolean)
}
