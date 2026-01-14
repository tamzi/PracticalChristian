package com.sacrament.demo.action.iconbutton.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.action.SacramentButtonTone
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Icon Button Tones detail screen.
 *
 * Displays examples of all icon button tones available in the SacramentIconButton component.
 * Tones provide semantic meaning through color, helping users understand the button's purpose.
 *
 * Navigation path: Home → Action Components → Icon Buttons → Icon Button Tones (this screen)
 *
 * Tones demonstrated:
 * - **Brand**: Primary brand color for main actions
 * - **Neutral**: Subtle color for less prominent actions
 * - **Success**: Green tone for positive/completion actions
 * - **Warning**: Orange/yellow tone for caution actions
 * - **Error**: Red tone for destructive/critical actions
 * - **Info**: Blue tone for informational actions
 *
 * @param onNavigateBack Callback to navigate back to the Icon Buttons overview screen
 */
@Composable
fun IconButtonTonesCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Icon Button Tones",
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
            CatalogSection("Icon Button Tones") {
                CatalogRow("Brand") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.Star,
                            contentDescription = "Brand",
                            onClick = {},
                            tone = SacramentButtonTone.Brand
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.Favorite,
                            contentDescription = "Brand",
                            onClick = {},
                            tone = SacramentButtonTone.Brand
                        )
                    }
                }

                CatalogRow("Neutral") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.Settings,
                            contentDescription = "Neutral",
                            onClick = {},
                            tone = SacramentButtonTone.Neutral
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.Menu,
                            contentDescription = "Neutral",
                            onClick = {},
                            tone = SacramentButtonTone.Neutral
                        )
                    }
                }

                CatalogRow("Success") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.Verified,
                            contentDescription = "Success",
                            onClick = {},
                            tone = SacramentButtonTone.Success
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.Save,
                            contentDescription = "Success",
                            onClick = {},
                            tone = SacramentButtonTone.Success
                        )
                    }
                }

                CatalogRow("Warning") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.Warning,
                            contentDescription = "Warning",
                            onClick = {},
                            tone = SacramentButtonTone.Warning
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.Info,
                            contentDescription = "Warning",
                            onClick = {},
                            tone = SacramentButtonTone.Warning
                        )
                    }
                }

                CatalogRow("Error") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.Close,
                            contentDescription = "Error",
                            onClick = {},
                            tone = SacramentButtonTone.Error
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.Warning,
                            contentDescription = "Error",
                            onClick = {},
                            tone = SacramentButtonTone.Error
                        )
                    }
                }

                CatalogRow("Info") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.Info,
                            contentDescription = "Info",
                            onClick = {},
                            tone = SacramentButtonTone.Info
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.Book,
                            contentDescription = "Info",
                            onClick = {},
                            tone = SacramentButtonTone.Info
                        )
                    }
                }
            }
        }
    }
}

/**
 * Row component for displaying icon button tone examples.
 *
 * @param label Descriptive label for the example (e.g., "Brand", "Success", "Error")
 * @param content The composable icon button examples to display below the label
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
