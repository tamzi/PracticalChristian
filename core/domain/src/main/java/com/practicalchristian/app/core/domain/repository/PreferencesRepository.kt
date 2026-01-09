package com.practicalchristian.app.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {

    val isDarkModeEnabled: Flow<Boolean>
    val profilePictureUri: Flow<String?>
    val userName: Flow<String?>

    suspend fun toggleDarkModeTheme()
    suspend fun setProfilePictureUri(uri: String?)
    suspend fun setUserName(name: String?)
    
    suspend fun setNotificationPermissionRequested(requested: Boolean)
    suspend fun setNotificationPermissionGranted(granted: Boolean)
}
