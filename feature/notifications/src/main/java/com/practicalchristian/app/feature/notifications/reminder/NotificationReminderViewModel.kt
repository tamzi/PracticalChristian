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
            handlePermissionResult(isGranted)
        }
    }

    fun onSkip() {
        viewModelScope.launch {
            handlePermissionResult(false)
        }
    }

    private suspend fun handlePermissionResult(isGranted: Boolean) {
        // Save notification permission preference
        preferencesRepository.setNotificationPermissionRequested(true)
        preferencesRepository.setNotificationPermissionGranted(isGranted)

        // Navigate to home after permission is handled
        update { copy(shouldNavigateToHome = true) }
    }

    fun onNavigationComplete() {
        update { copy(shouldNavigateToHome = false) }
    }
}
