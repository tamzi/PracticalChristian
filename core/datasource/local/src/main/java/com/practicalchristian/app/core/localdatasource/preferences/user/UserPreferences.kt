package com.practicalchristian.app.core.localdatasource.preferences.user

import kotlinx.coroutines.flow.Flow

interface UserPreferences {

    val darkModeTheme: Flow<Boolean>
    val profilePictureUri: Flow<String?>
    val userName: Flow<String?>
    val notificationPermissionRequested: Flow<Boolean>
    val notificationPermissionGranted: Flow<Boolean>

    suspend fun toggleDarkModeTheme()
    suspend fun setProfilePictureUri(uri: String?)
    suspend fun setUserName(name: String?)
    
    suspend fun setNotificationPermissionRequested(requested: Boolean)
    suspend fun setNotificationPermissionGranted(granted: Boolean)
}
