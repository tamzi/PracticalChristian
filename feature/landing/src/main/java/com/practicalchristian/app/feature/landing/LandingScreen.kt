package com.practicalchristian.app.feature.landing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentSurface
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Landing screen - Entry point of the app.

 */
@Composable
fun LandingScreen(
    navigator: AppNavigator,
    viewModel: LandingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LandingScreenContent(
        state = state,
        onNavigateToDestination = { destination ->
            when (destination) {
                Destination.ONBOARDING -> navigator.replaceWith(AppDestination.Onboarding)
                Destination.AUTHENTICATION -> navigator.replaceWith(AppDestination.Authentication)
                Destination.SETUP -> navigator.replaceWith(AppDestination.Setup)
                Destination.HOME -> navigator.replaceWith(AppDestination.Home)
            }
        }
    )
}

@Composable
fun LandingScreenContent(
    state: LandingScreenUiState,
    onNavigateToDestination: (Destination) -> Unit
) {
    LaunchedEffect(state.destination) {
        state.destination?.let { onNavigateToDestination(it) }
    }

    SacramentSurface(
        modifier = Modifier.fillMaxSize(),
        color = SacramentTheme.colors.surfaces.background,
        shape = RectangleShape,
    ) {
        SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
            Column {
                SacramentProgressIndicator()
            }
        }
    }
}

@Preview(name = "Loading State")
@Composable
fun LandingScreenLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        LandingScreenContent(
            state = LandingScreenUiState(destination = null),
            onNavigateToDestination = {}
        )
    }
}

@Preview(name = "Authentication State")
@Composable
fun LandingScreenAuthenticationPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        LandingScreenContent(
            state = LandingScreenUiState(destination = Destination.AUTHENTICATION),
            onNavigateToDestination = {}
        )
    }
}

@Preview(name = "Onboarding State")
@Composable
fun LandingScreenOnboardingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        LandingScreenContent(
            state = LandingScreenUiState(destination = Destination.ONBOARDING),
            onNavigateToDestination = {}
        )
    }
}

@Preview(name = "Setup State")
@Composable
fun LandingScreenSetupPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        LandingScreenContent(
            state = LandingScreenUiState(destination = Destination.SETUP),
            onNavigateToDestination = {}
        )
    }
}

@Preview(showBackground = true, name = "LandingScreen - Home Destination")
@Composable
fun LandingScreenHomePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        LandingScreenContent(
            state = LandingScreenUiState(destination = Destination.HOME),
            onNavigateToDestination = {}
        )
    }
}

@Preview(showBackground = true, name = "LandingScreen - Idle State")
@Composable
private fun LandingScreenIdlePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSurface(
            modifier = Modifier.fillMaxSize(),
            color = SacramentTheme.colors.surfaces.background,
            shape = RectangleShape,
        ) {
            SacramentEmptyState(
                icon = Icons.AutoMirrored.Rounded.List,
                title = "Welcome",
                contentDescription = "setting up",
                description = "Please wait while we're setting things up"
            )
        }
    }
}

@Preview(showBackground = true, name = "LandingScreen - Error State")
@Composable
private fun LandingScreenErrorPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val spacing = SacramentTheme.spacing
        SacramentSurface(
            modifier = Modifier.fillMaxSize(),
            color = SacramentTheme.colors.surfaces.background,
            shape = RectangleShape,
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
            ) {
                SacramentIcon(
                    imageVector = Icons.Rounded.Warning,
                    contentDescription = "error",
                    tint = SacramentTheme.colors.semantic.error,
                    modifier = Modifier
                        .padding(bottom = spacing.padding12),
                    size = 48.dp,
                )
                SacramentText(
                    text = "Error",
                    color = SacramentTheme.colors.semantic.error,
                    style = SacramentTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
                )
                SacramentText(
                    text = "Failed to initialize app. Please restart the application.",
                    color = SacramentTheme.colors.semantic.error,
                    style = SacramentTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                    modifier = Modifier.padding(
                        top = spacing.padding8,
                        start = spacing.padding32,
                        end = spacing.padding32
                    )
                )
            }
        }
    }
}
