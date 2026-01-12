package com.practicalchristian.app.feature.setup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.input.SacramentTextField
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Setup screen - Onboarding flow.
 * 
 * Persists setup completion state before navigating to Home to prevent
 * users from being redirected back to setup on app restart.
 */
@Composable
fun SetupScreen(
    navigator: AppNavigator,
    viewModel: SetupViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    
    // Handle navigation after setup completion
    androidx.compose.runtime.LaunchedEffect(state.shouldNavigateToHome) {
        if (state.shouldNavigateToHome) {
            navigator.replaceWith(AppDestination.Home)
            viewModel.onNavigationComplete()
        }
    }
    
    SetupScreenContent(
        state = state,
        onNameChanged = viewModel::onNameChanged,
        onSetupComplete = { viewModel.onSetupComplete() }
    )
}

@Composable
fun SetupScreenContent(
    state: SetupScreenUiState,
    onNameChanged: (String) -> Unit,
    onSetupComplete: () -> Unit
) {
    val spacing = SacramentTheme.spacing
    SacramentScreenScaffold { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
        ) {
            SacramentCenteredColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = spacing.padding24)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.Start
                ) {
                    SacramentText(
                        text = "Let's get to know you",
                        style = SacramentTheme.typography.headlineSmall
                    )
                    Spacer(modifier = Modifier.height(spacing.padding8))
                    SacramentText(
                        text = "Tell us what to call you. You can skip this for now.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.strong.copy(alpha = 0.7f)
                    )
                    Spacer(modifier = Modifier.height(spacing.padding24))
                    SacramentTextField(
                        value = state.name,
                        onValueChange = onNameChanged,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = "Name (optional)",
                        singleLine = true,
                        enabled = !state.isLoading
                    )
                    Spacer(modifier = Modifier.height(spacing.padding24))
                    SacramentButton(
                        text = if (state.isLoading) "Saving..." else "Continue",
                        onClick = onSetupComplete,
                        enabled = !state.isLoading,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SetupScreenPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SetupScreenContent(
            state = SetupScreenUiState(),
            onNameChanged = {},
            onSetupComplete = {}
        )
    }
}
