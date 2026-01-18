package com.practicalchristian.app.feature.notifications.reminder

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentSurface
import com.sacrament.ui.primitives.SacramentText

/**
 * Screen for requesting notification permissions.
 * Displays an illustration and allows users to enable notifications or skip.
 */
@Composable
fun NotificationReminderScreen(
    navigator: AppNavigator,
    viewModel: NotificationReminderViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        viewModel.onPermissionResult(isGranted)
    }

    LaunchedEffect(state.shouldNavigateToHome) {
        if (state.shouldNavigateToHome) {
            navigator.replaceWith(AppDestination.Home)
            viewModel.onNavigationComplete()
        }
    }

    NotificationReminderScreenContent(
        onEnableNotifications = {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        },
        onSkip = {
            viewModel.onSkip()
        },
    )
}

@Composable
private fun NotificationReminderScreenContent(
    onEnableNotifications: () -> Unit,
    onSkip: () -> Unit
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing

    SacramentScreenScaffold { paddingValues ->
        SacramentCenteredColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = spacing.padding24)
        ) {
            // Phone illustration with notification
            PhoneNotificationIllustration(
                modifier = Modifier.size(200.dp),
            )

            Spacer(modifier = Modifier.height(spacing.padding32))

            // Title
            SacramentText(
                text = "Stay connected\nwith reminders",
                style = SacramentTheme.typography.headlineMedium.copy(textAlign = TextAlign.Center),
                color = colors.text.strong,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(spacing.padding16))

            // Description
            SacramentText(
                text = "Get reminders for prayer times, daily readings, and devotional content " +
                    "to help you stay consistent in your spiritual journey.",
                style = SacramentTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = colors.text.muted,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(spacing.padding32))

            // Primary button
            SacramentButton(
                text = "Turn on notifications",
                onClick = onEnableNotifications,
                variant = SacramentButtonVariant.Filled,
                modifier = Modifier.fillMaxWidth(),
            )

            Spacer(modifier = Modifier.height(spacing.padding16))

            // Skip button
            SacramentButton(
                text = "Skip",
                onClick = onSkip,
                variant = SacramentButtonVariant.Ghost,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun PhoneNotificationIllustration(
    modifier: Modifier = Modifier
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    val radii = SacramentTheme.radii
    val phoneColor = colors.surfaces.lavenderSoft
    val haloColor = colors.surfaces.surfaceVariant

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        // Halo circle
        SacramentSurface(
            modifier = Modifier.size(280.dp),
            color = haloColor,
            shape = CircleShape,
        ) {}

        // Phone
        SacramentSurface(
            modifier = Modifier.size(180.dp),
            color = phoneColor,
            shape = RoundedCornerShape(radii.xl),
        ) {
            // Phone screen
            SacramentSurface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(spacing.xs),
                color = colors.surfaces.surface,
                shape = RoundedCornerShape(radii.lg),
            ) {
                // Notification banner
                SacramentSurface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .padding(horizontal = spacing.sm, vertical = spacing.xs),
                    color = colors.surfaces.surface,
                    shape = RoundedCornerShape(radii.sm),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = spacing.sm, vertical = spacing.xs),
                        verticalArrangement = Arrangement.spacedBy(spacing.xs),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(spacing.xs),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            SacramentIcon(
                                imageVector = SacramentIcons.SacramentIconAccessTime,
                                contentDescription = null,
                                tint = colors.text.strong,
                                size = SacramentTheme.iconSizes.sm,
                            )
                            // Placeholder lines for notification text
                            SacramentSurface(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(spacing.sm),
                                color = colors.text.muted.copy(alpha = 0.3f),
                                shape = RoundedCornerShape(radii.xs),
                            ) {}
                        }
                        SacramentSurface(
                            modifier = Modifier
                                .fillMaxWidth(0.7f)
                                .height(spacing.padding6),
                            color = colors.text.muted.copy(alpha = 0.2f),
                            shape = RoundedCornerShape(radii.xs),
                        ) {}
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun NotificationReminderScreenPreview() {
    SacramentTheme(navigationBar = Bar.BACKGROUND, statusBar = Bar.BACKGROUND) {
        NotificationReminderScreenContent(
            onEnableNotifications = {},
            onSkip = {},
        )
    }
}
