package com.practicalchristian.app.feature.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentButtonDefaults
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.typography.SacramentLogoStyle

/**
 * Authentication screen.
 *
 * After successful sign-in, navigates to Setup if user hasn't completed setup,
 * otherwise navigates to Home.
 */
@Composable
fun AuthenticationScreen(
    navigator: AppNavigator,
    viewModel: AuthenticationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.shouldNavigateToSetup, state.shouldNavigateToHome) {
        when {
            state.shouldNavigateToSetup -> {
                navigator.replaceWith(AppDestination.Setup)
                viewModel.onNavigationComplete()
            }
            state.shouldNavigateToHome -> {
                navigator.replaceWith(AppDestination.Home)
                viewModel.onNavigationComplete()
            }
        }
    }

    AuthenticationScreenContent(
        state = state,
        onSignUpWithGoogle = { viewModel.onSignIn() },
        onSignUpWithFacebook = { viewModel.onSignIn() },
        onSignUpWithTikTok = { viewModel.onSignIn() },
        onSignUpWithX = { viewModel.onSignIn() }
    )
}

@Composable
fun AuthenticationScreenContent(
    state: AuthenticationScreenUiState,
    onSignUpWithGoogle: () -> Unit,
    onSignUpWithFacebook: () -> Unit,
    onSignUpWithTikTok: () -> Unit,
    onSignUpWithX: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = SacramentTheme.colors.surfaces.background
    ) {
        AuthenticationSignUp(
            state = state,
            onSignUpWithGoogle = onSignUpWithGoogle,
            onSignUpWithFacebook = onSignUpWithFacebook,
            onSignUpWithTikTok = onSignUpWithTikTok,
            onSignUpWithX = onSignUpWithX
        )
    }
}

@Composable
private fun AuthenticationSignUp(
    state: AuthenticationScreenUiState,
    onSignUpWithGoogle: () -> Unit,
    onSignUpWithFacebook: () -> Unit,
    onSignUpWithTikTok: () -> Unit,
    onSignUpWithX: () -> Unit
) {
    val spacing = SacramentTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = spacing.padding24, vertical = spacing.padding40),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "The Practical Christian",
                style = SacramentLogoStyle,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(spacing.padding10))
            Text(
                text = "Create account or log in",
                style = SacramentTheme.typography.bodyMedium,
                color = SacramentTheme.colors.text.strong.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(spacing.padding200))
            AuthProviderButton(
                iconRes = R.drawable.feature_auth_google,
                label = "Continue with Google",
                onClick = onSignUpWithGoogle,
                enabled = !state.isLoading
            )
            Spacer(modifier = Modifier.height(spacing.padding12))
            AuthProviderButton(
                iconRes = R.drawable.feature_auth_facebook,
                label = "Continue with Facebook",
                onClick = onSignUpWithFacebook,
                enabled = !state.isLoading
            )
            Spacer(modifier = Modifier.height(spacing.padding12))
            AuthProviderButton(
                iconRes = R.drawable.feature_auth_tiktok,
                label = "Continue with TikTok",
                onClick = onSignUpWithTikTok,
                enabled = !state.isLoading
            )
            Spacer(modifier = Modifier.height(spacing.padding12))
            AuthProviderButton(
                iconRes = R.drawable.feature_auth_x,
                label = "Continue with X",
                onClick = onSignUpWithX,
                enabled = !state.isLoading
            )
        }
        Text(
            text = "By continuing, you agree to Practical Christian's Terms of Service and confirm that you have read and understand our Privacy Policy.",
            style = SacramentTheme.typography.bodySmall,
            color = SacramentTheme.colors.text.strong.copy(alpha = 0.5f),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun AuthProviderButton(
    iconRes: Int,
    label: String,
    onClick: () -> Unit,
    enabled: Boolean
) {
    val spacing = SacramentTheme.spacing
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val defaultElevation = SacramentButtonDefaults.elevation(enabled = enabled, pressed = false)
    val pressedElevation = SacramentButtonDefaults.elevation(enabled = enabled, pressed = true)
    val disabledElevation = SacramentButtonDefaults.elevation(enabled = false, pressed = false)
    
    Button(
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource,
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp,
                color = SacramentTheme.colors.text.strong.copy(alpha = 0.1f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(SacramentTheme.radii.lg)
            ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(SacramentTheme.radii.lg),
        colors = ButtonDefaults.buttonColors(
            containerColor = SacramentTheme.colors.utilities.authProviderSurface,
            contentColor = Color.Black
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = defaultElevation,
            pressedElevation = pressedElevation,
            disabledElevation = disabledElevation
        )
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(spacing.padding12))
        Text(
            text = label,
            style = SacramentTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthenticationScreenPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        AuthenticationScreenContent(
            state = AuthenticationScreenUiState(isLoading = false),
            onSignUpWithGoogle = {},
            onSignUpWithFacebook = {},
            onSignUpWithTikTok = {},
            onSignUpWithX = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AuthenticationScreenLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        AuthenticationScreenContent(
            state = AuthenticationScreenUiState(isLoading = true),
            onSignUpWithGoogle = {},
            onSignUpWithFacebook = {},
            onSignUpWithTikTok = {},
            onSignUpWithX = {}
        )
    }
}
