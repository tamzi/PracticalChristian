package com.practicalchristian.app.feature.auth

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.repositories.AuthenticationRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class AuthenticationScreenUiState(
    val isLoading: Boolean = false,
    val shouldNavigateToSetup: Boolean = false,
    val shouldNavigateToHome: Boolean = false
)

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : StatefulViewModel<AuthenticationScreenUiState>(AuthenticationScreenUiState()) {

    /**
     * Handles sign-in flow by persisting the sign-in state and determining the next destination.
     * Navigates to Setup if the user hasn't completed setup, otherwise navigates to Home.
     * This ensures the user remains logged in on app restart.
     */
    fun onSignIn() {
        viewModelScope.launch {
            update { copy(isLoading = true) }
            
            // Persist sign-in state - this suspend function ensures the write completes
            authenticationRepository.setUserHasSignedIn(true)
            
            // Check if user has completed setup to determine navigation destination
            val userHasSetup = authenticationRepository.userHasSetup.first()
            
            update { 
                copy(
                    isLoading = false,
                    shouldNavigateToSetup = !userHasSetup,
                    shouldNavigateToHome = userHasSetup
                )
            }
        }
    }
    
    /**
     * Resets navigation flags after navigation is complete.
     */
    fun onNavigationComplete() {
        update { copy(shouldNavigateToSetup = false, shouldNavigateToHome = false) }
    }
}
