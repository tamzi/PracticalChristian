package com.sacrament.demo.action.button.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentButtonTone
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Button Tones detail screen.
 *
 * Displays examples of all six button tones available in the SacramentButton component.
 * Tones provide semantic meaning through color, helping users understand the button's purpose.
 *
 * Navigation path: Home → Action Components → Buttons → Button Tones (this screen)
 *
 * Tones demonstrated:
 * - **Brand**: Primary brand color for main actions
 * - **Neutral**: Subtle color for less prominent actions
 * - **Success**: Green tone for positive/completion actions
 * - **Warning**: Orange/yellow tone for caution actions
 * - **Error**: Red tone for destructive/critical actions
 * - **Info**: Blue tone for informational actions
 *
 * @param onNavigateBack Callback to navigate back to the Buttons overview screen
 */
@Composable
fun ButtonTonesCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Button Tones",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
        ) {
            CatalogSection("Button Tones") {
                CatalogRow("Brand") {
                    SacramentButton(
                        text = "Brand",
                        onClick = {},
                        tone = SacramentButtonTone.Brand
                    )
                }
                CatalogRow("Neutral") {
                    SacramentButton(
                        text = "Neutral",
                        onClick = {},
                        tone = SacramentButtonTone.Neutral
                    )
                }
                CatalogRow("Success") {
                    SacramentButton(
                        text = "Success",
                        onClick = {},
                        tone = SacramentButtonTone.Success
                    )
                }
                CatalogRow("Warning") {
                    SacramentButton(
                        text = "Warning",
                        onClick = {},
                        tone = SacramentButtonTone.Warning
                    )
                }
                CatalogRow("Error") {
                    SacramentButton(
                        text = "Error",
                        onClick = {},
                        tone = SacramentButtonTone.Error
                    )
                }
                CatalogRow("Info") {
                    SacramentButton(
                        text = "Info",
                        onClick = {},
                        tone = SacramentButtonTone.Info
                    )
                }
            }
        }
    }
}

/**
 * Row component for displaying button tone examples.
 *
 * Used exclusively in ButtonTonesCatalogScreen to present labeled examples
 * of different button tones (semantic colors).
 *
 * @param label Descriptive label for the example (e.g., "Brand", "Success", "Error")
 * @param content The composable button example to display below the label
 */
@Composable
private fun CatalogRow(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
    ) {
        SacramentText(
            text = label,
            style = SacramentTheme.typography.bodyMedium,
            color = SacramentTheme.colors.text.muted
        )
        content()
    }
}
