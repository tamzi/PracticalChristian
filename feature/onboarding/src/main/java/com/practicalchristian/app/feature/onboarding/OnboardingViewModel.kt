package com.practicalchristian.app.feature.onboarding

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.repositories.AuthenticationRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

data class OnboardingScreenUiState(
    val isLoading: Boolean = false,
    val shouldNavigateToSetup: Boolean = false,
    val shouldNavigateToHome: Boolean = false
)

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : StatefulViewModel<OnboardingScreenUiState>(OnboardingScreenUiState()) {

    fun onContinueAsGuest() {
        viewModelScope.launch {
            update { copy(isLoading = true) }

            authenticationRepository.setUserHasSignedIn(true)
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

    fun onNavigationComplete() {
        update { copy(shouldNavigateToSetup = false, shouldNavigateToHome = false) }
    }
}
