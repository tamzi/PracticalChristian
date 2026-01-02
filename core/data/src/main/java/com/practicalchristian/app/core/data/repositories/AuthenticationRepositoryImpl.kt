package com.practicalchristian.app.core.data.repositories

import com.practicalchristian.app.core.domain.repositories.AuthenticationRepository
import com.practicalchristian.app.core.localdatasource.preferences.auth.AuthenticationPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val authenticationPreferences: AuthenticationPreferences
) : AuthenticationRepository {

    override val userHasSignedIn: Flow<Boolean>
        get() = authenticationPreferences.userHasSignedIn

    override val userHasSetup: Flow<Boolean>
        get() = authenticationPreferences.userHasSetup

    override suspend fun setUserHasSignedIn(value: Boolean) {
        authenticationPreferences.setUserHasSignedIn(value)
    }

    override suspend fun setUserHasSetup(value: Boolean) {
        authenticationPreferences.setUserHasSetup(value)
    }
}
