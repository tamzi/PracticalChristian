package com.practicalchristian.app.core.domain.repositories

import kotlinx.coroutines.flow.Flow

interface AuthenticationRepository {

    val userHasSignedIn: Flow<Boolean>

    val userHasSetup: Flow<Boolean>

    suspend fun setUserHasSignedIn(value: Boolean)

    suspend fun setUserHasSetup(value: Boolean)
}
