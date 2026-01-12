package com.practicalchristian.app.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Settings screen.

 */
@Composable
fun SettingsScreen(
    navigator: AppNavigator
) {
    SettingsScreenContent(
        onNavigateBackClicked = { navigator.back() }
    )
}

@Composable
fun SettingsScreenContent(onNavigateBackClicked: () -> Unit) {
    SacramentScreenScaffold(topBar = {
        SacramentTopAppBar(
            title = { SacramentText(text = "Settings", style = SacramentTheme.typography.titleLarge) },
            navigationIcon = {
                SacramentIconButton(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "navigate back",
                    onClick = onNavigateBackClicked
                )
            }
        )
    }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                SacramentText(text = "Settings", style = SacramentTheme.typography.bodyLarge)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsScreenPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SettingsScreenContent(
            onNavigateBackClicked = {}
        )
    }
}
