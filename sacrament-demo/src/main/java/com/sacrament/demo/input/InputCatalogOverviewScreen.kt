package com.sacrament.demo.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.content.list.SacramentListItem
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold

/**
 * Input Components catalog overview screen.
 *
 * This intermediate screen organizes input component examples into subcategories.
 * Each subcategory navigates to its own dedicated screen with detailed, interactive examples.
 *
 * Navigation hierarchy:
 * - Home → Input Components (this screen) → TextFields/Checkboxes/Radios/Switches
 *
 * Subcategories:
 * - **Text Fields**: Sizes, states (label, error, disabled)
 * - **Checkboxes**: Small, Medium sizes
 * - **Radio Buttons**: Small, Medium sizes
 * - **Switches**: Small, Medium sizes
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 * @param onNavigateToTextFields Callback to navigate to Text Fields screen
 * @param onNavigateToCheckboxes Callback to navigate to Checkboxes screen
 * @param onNavigateToRadios Callback to navigate to Radio Buttons screen
 * @param onNavigateToSwitches Callback to navigate to Switches screen
 */
@Composable
fun InputCatalogOverviewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToTextFields: () -> Unit,
    onNavigateToCheckboxes: () -> Unit,
    onNavigateToRadios: () -> Unit,
    onNavigateToSwitches: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Input Components",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(SacramentTheme.colors.surfaces.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
                verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.lg)
            ) {
                SacramentListItem(
                    headline = "TEXT FIELDS",
                    supporting = "Sizes, Label, Error state, Disabled",
                    onClick = onNavigateToTextFields
                )

                SacramentListItem(
                    headline = "CHECKBOXES",
                    supporting = "Small, Medium sizes",
                    onClick = onNavigateToCheckboxes
                )

                SacramentListItem(
                    headline = "RADIO BUTTONS",
                    supporting = "Small, Medium sizes",
                    onClick = onNavigateToRadios
                )

                SacramentListItem(
                    headline = "SWITCHES",
                    supporting = "Small, Medium sizes",
                    onClick = onNavigateToSwitches
                )
            }
        }
    }
}
