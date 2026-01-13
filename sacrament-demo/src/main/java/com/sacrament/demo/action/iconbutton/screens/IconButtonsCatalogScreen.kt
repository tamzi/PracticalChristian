package com.sacrament.demo.action.iconbutton.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.action.SacramentIconButtonSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Catalog screen demonstrating icon buttons.
 *
 * Showcases icon button variants, sizes, tones, and states including:
 * - Variants: Filled, Outlined, Ghost
 * - Sizes: Small, Medium, Large
 * - Tones: Brand, Neutral, Success, Warning, Danger
 * - States: Default, Disabled
 *
 * @param onNavigateBack Callback to navigate back to the Action catalog screen
 */
@Composable
fun IconButtonsCatalogScreen(
    onNavigateBack: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Icon Buttons",
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
                    .padding(
                        horizontal = SacramentTheme.spacing.xl,
                        vertical = SacramentTheme.spacing.xxl
                    ),
                verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
            ) {
                // Variants section
                CatalogSection("Variants") {
                    Column(verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)) {
                        SacramentText(
                            text = "Filled",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Add,
                                contentDescription = "Add",
                                onClick = {},
                                variant = SacramentButtonVariant.Filled
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Edit,
                                contentDescription = "Edit",
                                onClick = {},
                                variant = SacramentButtonVariant.Filled
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Favorite,
                                contentDescription = "Favorite",
                                onClick = {},
                                variant = SacramentButtonVariant.Filled
                            )
                        }

                        SacramentText(
                            text = "Outlined",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Bookmark,
                                contentDescription = "Bookmark",
                                onClick = {},
                                variant = SacramentButtonVariant.Outlined
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Settings,
                                contentDescription = "Settings",
                                onClick = {},
                                variant = SacramentButtonVariant.Outlined
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Save,
                                contentDescription = "Save",
                                onClick = {},
                                variant = SacramentButtonVariant.Outlined
                            )
                        }

                        SacramentText(
                            text = "Ghost",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Close,
                                contentDescription = "Close",
                                onClick = {},
                                variant = SacramentButtonVariant.Ghost
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Star,
                                contentDescription = "Star",
                                onClick = {},
                                variant = SacramentButtonVariant.Ghost
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Favorite,
                                contentDescription = "Favorite",
                                onClick = {},
                                variant = SacramentButtonVariant.Ghost
                            )
                        }
                    }
                }

                // Sizes section
                CatalogSection("Sizes") {
                    Column(verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)) {
                        SacramentText(
                            text = "Small (32dp)",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Add,
                                contentDescription = "Add",
                                onClick = {},
                                size = SacramentIconButtonSize.Small
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Edit,
                                contentDescription = "Edit",
                                onClick = {},
                                size = SacramentIconButtonSize.Small,
                                variant = SacramentButtonVariant.Outlined
                            )
                        }

                        SacramentText(
                            text = "Medium (40dp)",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Add,
                                contentDescription = "Add",
                                onClick = {},
                                size = SacramentIconButtonSize.Medium
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Edit,
                                contentDescription = "Edit",
                                onClick = {},
                                size = SacramentIconButtonSize.Medium,
                                variant = SacramentButtonVariant.Outlined
                            )
                        }

                        SacramentText(
                            text = "Large (48dp)",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Add,
                                contentDescription = "Add",
                                onClick = {},
                                size = SacramentIconButtonSize.Large
                            )
                            SacramentIconButton(
                                imageVector = SacramentIcons.Edit,
                                contentDescription = "Edit",
                                onClick = {},
                                size = SacramentIconButtonSize.Large,
                                variant = SacramentButtonVariant.Outlined
                            )
                        }
                    }
                }

                // Tones section
                CatalogSection("Tones") {
                    Column(verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)) {
                        SacramentText(
                            text = "Brand",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Star,
                                contentDescription = "Brand",
                                onClick = {},
                                tone = SacramentButtonTone.Brand
                            )
                        }

                        SacramentText(
                            text = "Neutral",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Settings,
                                contentDescription = "Neutral",
                                onClick = {},
                                tone = SacramentButtonTone.Neutral
                            )
                        }

                        SacramentText(
                            text = "Success",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Favorite,
                                contentDescription = "Success",
                                onClick = {},
                                tone = SacramentButtonTone.Success
                            )
                        }

                        SacramentText(
                            text = "Warning",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Settings,
                                contentDescription = "Warning",
                                onClick = {},
                                tone = SacramentButtonTone.Warning
                            )
                        }

                        SacramentText(
                            text = "Error",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Close,
                                contentDescription = "Error",
                                onClick = {},
                                tone = SacramentButtonTone.Error
                            )
                        }
                    }
                }

                // States section
                CatalogSection("States") {
                    Column(verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)) {
                        SacramentText(
                            text = "Default",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Add,
                                contentDescription = "Enabled",
                                onClick = {},
                                enabled = true
                            )
                        }

                        SacramentText(
                            text = "Disabled",
                            style = SacramentTheme.typography.labelMedium
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentIconButton(
                                imageVector = SacramentIcons.Add,
                                contentDescription = "Disabled",
                                onClick = {},
                                enabled = false
                            )
                        }
                    }
                }
            }
        }
    }
}
