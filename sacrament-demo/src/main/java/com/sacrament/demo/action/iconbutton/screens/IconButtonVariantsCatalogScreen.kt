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
 * Icon Button Variants detail screen.
 *
 * Displays comprehensive examples of the three icon button variants available in the
 * SacramentIconButton component. Each variant is shown with multiple icon examples.
 *
 * Navigation path: Home → Action Components → Icon Buttons → Icon Button Variants (this screen)
 *
 * Variants demonstrated:
 * - **Filled**: Solid background with high emphasis (primary actions)
 * - **Outlined**: Border with transparent background (secondary actions)
 * - **Ghost**: Icon-only with no border (tertiary actions)
 *
 * @param onNavigateBack Callback to navigate back to the Icon Buttons overview screen
 */
@Composable
fun IconButtonVariantsCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Icon Button Variants",
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
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconAdd,
                            contentDescription = "Add",
                            onClick = {},
                            variant = SacramentButtonVariant.Filled
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconEdit,
                            contentDescription = "Edit",
                            onClick = {},
                            variant = SacramentButtonVariant.Filled
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconFavorite,
                            contentDescription = "Favorite",
                            onClick = {},
                            variant = SacramentButtonVariant.Filled
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconClose,
                            contentDescription = "Remove",
                            onClick = {},
                            variant = SacramentButtonVariant.Filled
                        )
                    }
                }
                CatalogRow("Disabled") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconAdd,
                            contentDescription = "Add",
                            onClick = {},
                            variant = SacramentButtonVariant.Filled,
                            enabled = false
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconEdit,
                            contentDescription = "Edit",
                            onClick = {},
                            variant = SacramentButtonVariant.Filled,
                            enabled = false
                        )
                    }
                }
            }

            CatalogSection("Outlined") {
                CatalogRow("Default") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconBookmark,
                            contentDescription = "Bookmark",
                            onClick = {},
                            variant = SacramentButtonVariant.Outlined
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconSettings,
                            contentDescription = "Settings",
                            onClick = {},
                            variant = SacramentButtonVariant.Outlined
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconSave,
                            contentDescription = "Save",
                            onClick = {},
                            variant = SacramentButtonVariant.Outlined
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconNotifications,
                            contentDescription = "Notifications",
                            onClick = {},
                            variant = SacramentButtonVariant.Outlined
                        )
                    }
                }
                CatalogRow("Disabled") {
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
                    }
                }
            }

            CatalogSection("Ghost") {
                CatalogRow("Default") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconClose,
                            contentDescription = "Close",
                            onClick = {},
                            variant = SacramentButtonVariant.Ghost
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconStar,
                            contentDescription = "Star",
                            onClick = {},
                            variant = SacramentButtonVariant.Ghost
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconFavorite,
                            contentDescription = "Favorite",
                            onClick = {},
                            variant = SacramentButtonVariant.Ghost
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconMenu,
                            contentDescription = "Menu",
                            onClick = {},
                            variant = SacramentButtonVariant.Ghost
                        )
                    }
                }
                CatalogRow("Disabled") {
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
                    }
                }
            }
        }
    }
}

/**
 * Row component for displaying icon button variant examples.
 *
 * @param label Descriptive label for the example (e.g., "Default", "Disabled")
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
