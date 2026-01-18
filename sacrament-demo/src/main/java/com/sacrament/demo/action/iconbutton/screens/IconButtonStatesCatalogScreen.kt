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
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Icon Button States detail screen.
 *
 * Displays examples of icon button states including default (enabled) and disabled states.
 * Shows how different variants appear in each state.
 *
 * Navigation path: Home → Action Components → Icon Buttons → Icon Button States (this screen)
 *
 * States demonstrated:
 * - **Default**: Normal interactive state
 * - **Disabled**: Non-interactive state with reduced opacity
 *
 * @param onNavigateBack Callback to navigate back to the Icon Buttons overview screen
 */
@Composable
fun IconButtonStatesCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Icon Button States",
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
            CatalogSection("Default (Enabled)") {
                CatalogRow("Filled, Outlined, Ghost") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconAdd,
                            contentDescription = "Add",
                            onClick = {},
                            enabled = true
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconEdit,
                            contentDescription = "Edit",
                            onClick = {},
                            variant = SacramentButtonVariant.Outlined,
                            enabled = true
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconFavorite,
                            contentDescription = "Favorite",
                            onClick = {},
                            variant = SacramentButtonVariant.Ghost,
                            enabled = true
                        )
                    }
                }
            }

            CatalogSection("Disabled") {
                CatalogRow("Filled") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconAdd,
                            contentDescription = "Add",
                            onClick = {},
                            enabled = false
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconEdit,
                            contentDescription = "Edit",
                            onClick = {},
                            enabled = false
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconClose,
                            contentDescription = "Remove",
                            onClick = {},
                            enabled = false
                        )
                    }
                }

                CatalogRow("Outlined") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconBookmark,
                            contentDescription = "Bookmark",
                            onClick = {},
                            variant = SacramentButtonVariant.Outlined,
                            enabled = false
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconSettings,
                            contentDescription = "Settings",
                            onClick = {},
                            variant = SacramentButtonVariant.Outlined,
                            enabled = false
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconSave,
                            contentDescription = "Save",
                            onClick = {},
                            variant = SacramentButtonVariant.Outlined,
                            enabled = false
                        )
                    }
                }

                CatalogRow("Ghost") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconClose,
                            contentDescription = "Close",
                            onClick = {},
                            variant = SacramentButtonVariant.Ghost,
                            enabled = false
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconStar,
                            contentDescription = "Star",
                            onClick = {},
                            variant = SacramentButtonVariant.Ghost,
                            enabled = false
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconMenu,
                            contentDescription = "Menu",
                            onClick = {},
                            variant = SacramentButtonVariant.Ghost,
                            enabled = false
                        )
                    }
                }
            }
        }
    }
}

/**
 * Row component for displaying icon button state examples.
 *
 * @param label Descriptive label for the example
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
