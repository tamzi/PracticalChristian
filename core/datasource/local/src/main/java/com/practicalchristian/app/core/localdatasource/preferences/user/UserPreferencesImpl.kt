package com.practicalchristian.app.core.localdatasource.preferences.user

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.practicalchristian.app.core.localdatasource.preferences.source.PreferenceSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UserPreferencesImpl @Inject constructor(
    private val source: PreferenceSource
) : UserPreferences {

    object Keys {
        val darkModeTheme = booleanPreferencesKey("DARK_MODE")
        val profilePictureUri = stringPreferencesKey("PROFILE_PICTURE_URI")
        val userName = stringPreferencesKey("USER_NAME")
        val notificationPermissionRequested = booleanPreferencesKey("NOTIFICATION_PERMISSION_REQUESTED")
        val notificationPermissionGranted = booleanPreferencesKey("NOTIFICATION_PERMISSION_GRANTED")
    }

    override val darkModeTheme: Flow<Boolean>
        get() = source.get(key = Keys.darkModeTheme, true)

    override val profilePictureUri: Flow<String?>
        get() = source.getNullable(key = Keys.profilePictureUri)

    override val userName: Flow<String?>
        get() = source.getNullable(key = Keys.userName)

    override val notificationPermissionRequested: Flow<Boolean>
        get() = source.get(key = Keys.notificationPermissionRequested, default = false)

    override val notificationPermissionGranted: Flow<Boolean>
        get() = source.get(key = Keys.notificationPermissionGranted, default = false)

    override suspend fun toggleDarkModeTheme() {
        val current = source.get(key = Keys.darkModeTheme, default = true).first()
        source.update(key = Keys.darkModeTheme, value = current.not())
    }

    override suspend fun setProfilePictureUri(uri: String?) {
        if (uri != null) {
            source.update(key = Keys.profilePictureUri, value = uri)
        } else {
            source.delete(key = Keys.profilePictureUri)
        }
    }

    override suspend fun setUserName(name: String?) {
        val trimmed = name?.trim().orEmpty()
        if (trimmed.isNotBlank()) {
            source.update(key = Keys.userName, value = trimmed)
        } else {
            source.delete(key = Keys.userName)
        }
    }

    override suspend fun setNotificationPermissionRequested(requested: Boolean) {
        source.update(key = Keys.notificationPermissionRequested, value = requested)
    }

    override suspend fun setNotificationPermissionGranted(granted: Boolean) {
        source.update(key = Keys.notificationPermissionGranted, value = granted)
    }
}
