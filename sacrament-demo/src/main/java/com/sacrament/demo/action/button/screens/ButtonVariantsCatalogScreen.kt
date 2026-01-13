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
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Button Variants detail screen.
 *
 * Displays comprehensive examples of the three button variants available in the
 * SacramentButton component. Each variant is shown in both default and disabled states.
 *
 * Navigation path: Home → Action Components → Buttons → Button Variants (this screen)
 *
 * Variants demonstrated:
 * - **Filled**: Solid background with high emphasis (primary actions)
 * - **Outlined**: Border with transparent background (secondary actions)
 * - **Ghost**: Text-only with no border (tertiary actions)
 *
 * @param onNavigateBack Callback to navigate back to the Buttons overview screen
 */
@Composable
fun ButtonVariantsCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Button Variants",
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
            CatalogSection("Filled") {
                CatalogRow("Default") {
                    SacramentButton(
                        text = "Filled Button",
                        onClick = {},
                        variant = SacramentButtonVariant.Filled
                    )
                }
                CatalogRow("Disabled") {
                    SacramentButton(
                        text = "Disabled",
                        onClick = {},
                        variant = SacramentButtonVariant.Filled,
                        enabled = false
                    )
                }
            }
            
            CatalogSection("Outlined") {
                CatalogRow("Default") {
                    SacramentButton(
                        text = "Outlined Button",
                        onClick = {},
                        variant = SacramentButtonVariant.Outlined
                    )
                }
                CatalogRow("Disabled") {
                    SacramentButton(
                        text = "Disabled",
                        onClick = {},
                        variant = SacramentButtonVariant.Outlined,
                        enabled = false
                    )
                }
            }
            
            CatalogSection("Ghost") {
                CatalogRow("Default") {
                    SacramentButton(
                        text = "Ghost Button",
                        onClick = {},
                        variant = SacramentButtonVariant.Ghost
                    )
                }
                CatalogRow("Disabled") {
                    SacramentButton(
                        text = "Disabled",
                        onClick = {},
                        variant = SacramentButtonVariant.Ghost,
                        enabled = false
                    )
                }
            }
        }
    }
}

/**
 * Row component for displaying button variant examples.
 *
 * Used exclusively in ButtonVariantsCatalogScreen to present labeled examples
 * of different button variant styles.
 *
 * @param label Descriptive label for the example (e.g., "Default", "Disabled")
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
