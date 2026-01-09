package com.practicalchristian.app.feature.setup

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.repository.AuthenticationRepository
import com.practicalchristian.app.core.domain.repository.PreferencesRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class SetupScreenUiState(
    val name: String = "",
    val isLoading: Boolean = false,
    val shouldNavigateToHome: Boolean = false
)

@HiltViewModel
class SetupViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val preferencesRepository: PreferencesRepository
) : StatefulViewModel<SetupScreenUiState>(SetupScreenUiState()) {

    fun onNameChanged(value: String) {
        update { copy(name = value) }
    }

    /**
     * Completes the setup flow by persisting the setup state before navigation.
     * This ensures the user won't be redirected back to setup on app restart.
     */
    fun onSetupComplete() {
        viewModelScope.launch {
            update { copy(isLoading = true) }
            
            val name = state.value.name.trim()
            preferencesRepository.setUserName(name.ifBlank { null })
            // Persist setup completion state
            authenticationRepository.setUserHasSetup(true)
            
            update { 
                copy(
                    isLoading = false,
                    shouldNavigateToHome = true
                )
            }
        }
    }
    
    /**
     * Resets navigation flag after navigation is complete.
     */
    fun onNavigationComplete() {
        update { copy(shouldNavigateToHome = false) }
    }
}
