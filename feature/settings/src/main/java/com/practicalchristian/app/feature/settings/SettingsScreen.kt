package com.practicalchristian.app.feature.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(onNavigateBackClicked: () -> Unit) {
    Scaffold(topBar = {
        LargeTopAppBar(title = {
            Text(text = "Settings")
        }, navigationIcon = {
            IconButton(onClick = onNavigateBackClicked) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "navigate back"
                )
            }
        }, actions = {
        })
    },
        containerColor = SacramentTheme.colors.surfaces.background,
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                Text(text = "Settings")
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
