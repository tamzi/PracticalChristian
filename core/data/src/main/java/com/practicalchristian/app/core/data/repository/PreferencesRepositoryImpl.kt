package com.practicalchristian.app.core.data.repository

import com.practicalchristian.app.core.domain.repository.PreferencesRepository
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

    override suspend fun setNotificationPermissionRequested(requested: Boolean) {
        userPreferences.setNotificationPermissionRequested(requested)
    }

    override suspend fun setNotificationPermissionGranted(granted: Boolean) {
        userPreferences.setNotificationPermissionGranted(granted)
    }
}
