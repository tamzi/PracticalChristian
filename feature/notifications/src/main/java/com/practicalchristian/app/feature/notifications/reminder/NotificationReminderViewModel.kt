package com.practicalchristian.app.feature.notifications.reminder

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.repository.PreferencesRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class NotificationReminderScreenUiState(
    val shouldNavigateToHome: Boolean = false
)

@HiltViewModel
class NotificationReminderViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : StatefulViewModel<NotificationReminderScreenUiState>(NotificationReminderScreenUiState()) {

    fun onPermissionResult(isGranted: Boolean) {
        viewModelScope.launch {
            // Save notification permission preference
            preferencesRepository.setNotificationPermissionRequested(true)
            preferencesRepository.setNotificationPermissionGranted(isGranted)

            // Navigate to home after permission is handled
            update { copy(shouldNavigateToHome = true) }
        }
    }

    fun onSkip() {
        viewModelScope.launch {
            // Mark that user skipped the permission request
            preferencesRepository.setNotificationPermissionRequested(true)
            preferencesRepository.setNotificationPermissionGranted(false)

            // Navigate to home
            update { copy(shouldNavigateToHome = true) }
        }
    }

    fun onNavigationComplete() {
        update { copy(shouldNavigateToHome = false) }
    }
}
