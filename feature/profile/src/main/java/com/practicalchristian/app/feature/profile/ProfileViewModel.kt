package com.practicalchristian.app.feature.profile

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.repositories.PreferencesRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ProfileScreenUiState(
    val isDarkThemeEnabled: Boolean = true,
    val profilePictureUri: String? = null,
    val isUploadingProfilePicture: Boolean = false,
    val uploadError: String? = null,
    val uploadSuccess: Boolean = false
)

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : StatefulViewModel<ProfileScreenUiState>(ProfileScreenUiState()) {

    init {
        observeDarkModeTheme()
        observeProfilePicture()
    }

    private fun observeDarkModeTheme() {
        viewModelScope.launch {
            preferencesRepository.isDarkModeEnabled.collectLatest { isEnabled ->
                update { copy(isDarkThemeEnabled = isEnabled) }
            }
        }
    }

    private fun observeProfilePicture() {
        viewModelScope.launch {
            preferencesRepository.profilePictureUri.collectLatest { uri ->
                update { copy(profilePictureUri = uri) }
            }
        }
    }

    fun toggleDarkModeTheme() {
        viewModelScope.launch {
            preferencesRepository.toggleDarkModeTheme()
        }
    }

    fun setProfilePictureUri(uri: String?) {
        viewModelScope.launch {
            preferencesRepository.setProfilePictureUri(uri)
        }
    }
}
