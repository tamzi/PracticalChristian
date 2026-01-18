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
import com.sacrament.ui.components.action.SacramentIconButtonSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Icon Button Sizes detail screen.
 *
 * Displays examples of the three icon button sizes available in the SacramentIconButton component.
 * Size affects the overall button dimensions and touch target.
 *
 * Navigation path: Home → Action Components → Icon Buttons → Icon Button Sizes (this screen)
 *
 * Sizes demonstrated:
 * - **Small (32dp)**: Compact size for dense layouts or toolbars
 * - **Medium (40dp)**: Default size, balanced for most use cases
 * - **Large (48dp)**: Prominent size for primary actions or touch-focused interfaces
 *
 * @param onNavigateBack Callback to navigate back to the Icon Buttons overview screen
 */
@Composable
fun IconButtonSizesCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Icon Button Sizes",
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
            CatalogSection("Icon Button Sizes") {
                CatalogRow("Small (32dp)") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconAdd,
                            contentDescription = "Add",
                            onClick = {},
                            size = SacramentIconButtonSize.Small
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconEdit,
                            contentDescription = "Edit",
                            onClick = {},
                            size = SacramentIconButtonSize.Small,
                            variant = SacramentButtonVariant.Outlined
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconFavorite,
                            contentDescription = "Favorite",
                            onClick = {},
                            size = SacramentIconButtonSize.Small,
                            variant = SacramentButtonVariant.Ghost
                        )
                    }
                }

                CatalogRow("Medium (40dp)") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconAdd,
                            contentDescription = "Add",
                            onClick = {},
                            size = SacramentIconButtonSize.Medium
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconEdit,
                            contentDescription = "Edit",
                            onClick = {},
                            size = SacramentIconButtonSize.Medium,
                            variant = SacramentButtonVariant.Outlined
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconFavorite,
                            contentDescription = "Favorite",
                            onClick = {},
                            size = SacramentIconButtonSize.Medium,
                            variant = SacramentButtonVariant.Ghost
                        )
                    }
                }

                CatalogRow("Large (48dp)") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                    ) {
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconAdd,
                            contentDescription = "Add",
                            onClick = {},
                            size = SacramentIconButtonSize.Large
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconEdit,
                            contentDescription = "Edit",
                            onClick = {},
                            size = SacramentIconButtonSize.Large,
                            variant = SacramentButtonVariant.Outlined
                        )
                        SacramentIconButton(
                            imageVector = SacramentIcons.SacramentIconFavorite,
                            contentDescription = "Favorite",
                            onClick = {},
                            size = SacramentIconButtonSize.Large,
                            variant = SacramentButtonVariant.Ghost
                        )
                    }
                }
            }
        }
    }
}

/**
 * Row component for displaying icon button size examples.
 *
 * @param label Descriptive label for the example (e.g., "Small (32dp)", "Medium (40dp)")
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
