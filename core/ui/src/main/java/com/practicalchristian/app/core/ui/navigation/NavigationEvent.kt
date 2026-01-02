package com.practicalchristian.app.core.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow

/**
 * Sealed interface representing navigation events that ViewModels can emit.
 * The UI layer observes these events and delegates actual navigation to AppNavigator.
 */
sealed interface NavigationEvent {
    data class NavigateTo(val destination: AppDestination) : NavigationEvent
    data class ReplaceWith(val destination: AppDestination) : NavigationEvent
    data object NavigateBack : NavigationEvent
}

/**
 * Helper composable to handle navigation events from ViewModels.
 * Collects navigation events and delegates to the AppNavigator.
 */
@Composable
fun HandleNavigationEvents(
    navigationEvents: Flow<NavigationEvent>,
    navigator: AppNavigator
) {
    LaunchedEffect(Unit) {
        navigationEvents.collect { event ->
            when (event) {
                is NavigationEvent.ReplaceWith -> navigator.replaceWith(event.destination)
                is NavigationEvent.NavigateTo -> navigator.navigate(event.destination)
                NavigationEvent.NavigateBack -> navigator.back()
            }
        }
    }
}
