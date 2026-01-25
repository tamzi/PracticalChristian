package com.practicalchristian.app.feature.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.input.SacramentSwitch
import com.sacrament.ui.components.input.SacramentSwitchSize
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Settings screen with theme toggle and other app settings.
 */
@Composable
fun SettingsScreen(
    navigator: AppNavigator,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()
    
    SettingsScreenContent(
        onNavigateBackClicked = { navigator.back() },
        isDarkThemeEnabled = uiState.isDarkThemeEnabled,
        onToggleDarkMode = viewModel::toggleDarkModeTheme
    )
}

@Composable
fun SettingsScreenContent(
    onNavigateBackClicked: () -> Unit,
    isDarkThemeEnabled: Boolean = true,
    onToggleDarkMode: () -> Unit = {}
) {
    SacramentScreenScaffold(topBar = {
        SacramentTopAppBar(
            title = { SacramentText(text = "Settings", style = SacramentTheme.typography.titleLarge) },
            navigationIcon = {
                SacramentIconButton(
                    imageVector = SacramentIcons.SacramentIconArrowBack,
                    contentDescription = "Navigate back",
                    onClick = onNavigateBackClicked
                )
            }
        )
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(SacramentTheme.spacing.md)
        ) {
            // Theme switcher row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = SacramentTheme.spacing.sm)
                    .semantics(mergeDescendants = true) {
                        stateDescription = if (isDarkThemeEnabled) "Dark mode enabled" else "Light mode enabled"
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
                ) {
                    // Light mode icon
                    SacramentIcon(
                        imageVector = SacramentIcons.SacramentIconLightMode,
                        contentDescription = null,
                        tint = SacramentTheme.colors.text.strong
                    )
                    
                    SacramentText(
                        text = "Dark Mode",
                        style = SacramentTheme.typography.bodyLarge,
                        color = SacramentTheme.colors.text.strong
                    )
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
                ) {
                    // Theme switcher
                    SacramentSwitch(
                        checked = isDarkThemeEnabled,
                        onCheckedChange = { onToggleDarkMode() },
                        size = SacramentSwitchSize.Medium
                    )
                    
                    // Dark mode icon
                    SacramentIcon(
                        imageVector = SacramentIcons.SacramentIconDarkMode,
                        contentDescription = null,
                        tint = SacramentTheme.colors.text.strong
                    )
                }
            }
        }
    }
}

@Preview(name = "Settings - Light Mode", showBackground = true)
@Composable
fun SettingsScreenLightPreview() {
    SacramentTheme(
        darkTheme = false,
        navigationBar = Bar.SURFACE, 
        statusBar = Bar.BACKGROUND
    ) {
        SettingsScreenContent(
            onNavigateBackClicked = {},
            isDarkThemeEnabled = false
        )
    }
}

@Preview(name = "Settings - Dark Mode", showBackground = true)
@Composable
fun SettingsScreenDarkPreview() {
    SacramentTheme(
        darkTheme = true,
        navigationBar = Bar.SURFACE, 
        statusBar = Bar.BACKGROUND
    ) {
        SettingsScreenContent(
            onNavigateBackClicked = {},
            isDarkThemeEnabled = true
        )
    }
}
