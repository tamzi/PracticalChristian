package com.practicalchristian.app.core.data.repositories

import com.practicalchristian.app.core.domain.repositories.PreferencesRepository
import com.practicalchristian.app.core.localdatasource.preferences.user.UserPreferences
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferencesRepositoryImpl @Inject constructor(
    private val userPreferences: UserPreferences
) : PreferencesRepository {

    override val isDarkModeEnabled: Flow<Boolean>
        get() = userPreferences.darkModeTheme

    override val profilePictureUri: Flow<String?>
        get() = userPreferences.profilePictureUri

    override val userName: Flow<String?>
        get() = userPreferences.userName

    override suspend fun toggleDarkModeTheme() {
        userPreferences.toggleDarkModeTheme()
    }

    override suspend fun setProfilePictureUri(uri: String?) {
        userPreferences.setProfilePictureUri(uri)
    }

    override suspend fun setUserName(name: String?) {
        userPreferences.setUserName(name)
    }
}
