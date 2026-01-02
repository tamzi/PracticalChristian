package com.practicalchristian.app.feature.landing

import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.repositories.AuthenticationRepository
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class Destination {
    ONBOARDING, AUTHENTICATION, SETUP, HOME
}

data class LandingScreenUiState(val destination: Destination? = null)

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val repository: AuthenticationRepository
) : StatefulViewModel<LandingScreenUiState>(LandingScreenUiState()) {

    init {
        checkNavigationDestination()
    }

    private fun checkNavigationDestination() {
        viewModelScope.launch {
            val userHasSignedIn = repository.userHasSignedIn.first()
            if (userHasSignedIn.not()) {
                update { copy(destination = Destination.ONBOARDING) }
                return@launch
            }

            val userHasSetup = repository.userHasSetup.first()
            if (userHasSetup.not()) {
                update { copy(destination = Destination.SETUP) }
                return@launch
            }

            update { copy(destination = Destination.HOME) }
        }
    }
}
