package com.sacrament.demo.input.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSectionTitle
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

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
                InputCatalogListItem(
                    title = "TEXT FIELDS",
                    description = "Sizes, Label, Error state, Disabled",
                    onClick = onNavigateToTextFields
                )

                InputCatalogListItem(
                    title = "CHECKBOXES",
                    description = "Small, Medium sizes",
                    onClick = onNavigateToCheckboxes
                )

                InputCatalogListItem(
                    title = "RADIO BUTTONS",
                    description = "Small, Medium sizes",
                    onClick = onNavigateToRadios
                )

                InputCatalogListItem(
                    title = "SWITCHES",
                    description = "Small, Medium sizes",
                    onClick = onNavigateToSwitches
                )
            }
        }
    }
}

/**
 * List item component for displaying input component categories.
 */
@Composable
private fun InputCatalogListItem(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = SacramentTheme.spacing.md)
    ) {
        CatalogSectionTitle(text = title)
        
        Spacer(modifier = Modifier.height(SacramentTheme.spacing.xs))
        
        SacramentText(
            text = description,
            style = SacramentTheme.typography.bodyMedium,
            color = SacramentTheme.colors.text.muted
        )
    }
}
