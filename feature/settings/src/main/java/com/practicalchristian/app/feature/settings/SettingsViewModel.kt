package com.practicalchristian.app.feature.settings

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.repository.PreferencesRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI state for the Settings screen.
 */
data class SettingsScreenUiState(
    val isDarkThemeEnabled: Boolean = true,
)

/**
 * ViewModel for the Settings screen. Manages theme preferences and other settings.
 */
@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : StatefulViewModel<SettingsScreenUiState>(SettingsScreenUiState()) {

    init {
        observeDarkModeTheme()
    }

    // Observe the dark mode preference and update UI state
    private fun observeDarkModeTheme() {
        viewModelScope.launch {
            preferencesRepository.isDarkModeEnabled.collectLatest { isEnabled ->
                update { copy(isDarkThemeEnabled = isEnabled) }
            }
        }
    }

    // Toggle the dark mode theme preference
    fun toggleDarkModeTheme() {
        viewModelScope.launch {
            preferencesRepository.toggleDarkModeTheme()
        }
    }
}
