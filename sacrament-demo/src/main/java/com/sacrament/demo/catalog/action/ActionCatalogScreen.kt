package com.sacrament.demo.catalog.action

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.catalog.CatalogRow
import com.sacrament.demo.catalog.CatalogSection
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentButtonSize
import com.sacrament.ui.components.action.SacramentButtonTone
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.components.action.SacramentFab
import com.sacrament.ui.components.action.SacramentFabSize
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.action.SacramentIconButtonSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold

@Composable
fun ActionCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Action Components",
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
            // Buttons - Variants
            CatalogSection("Button Variants") {
                CatalogRow("Filled") {
                    SacramentButton(
                        text = "Filled Button",
                        onClick = {},
                        variant = SacramentButtonVariant.Filled
                    )
                }
                CatalogRow("Outlined") {
                    SacramentButton(
                        text = "Outlined Button",
                        onClick = {},
                        variant = SacramentButtonVariant.Outlined
                    )
                }
                CatalogRow("Ghost") {
                    SacramentButton(
                        text = "Ghost Button",
                        onClick = {},
                        variant = SacramentButtonVariant.Ghost
                    )
                }
            }
            
            // Buttons - Sizes
            CatalogSection("Button Sizes") {
                CatalogRow("Small") {
                    SacramentButton(
                        text = "Small",
                        onClick = {},
                        size = SacramentButtonSize.Small
                    )
                }
                CatalogRow("Medium") {
                    SacramentButton(
                        text = "Medium",
                        onClick = {},
                        size = SacramentButtonSize.Medium
                    )
                }
                CatalogRow("Large") {
                    SacramentButton(
                        text = "Large",
                        onClick = {},
                        size = SacramentButtonSize.Large
                    )
                }
            }
            
            // Buttons - Tones
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
            
            // Buttons - With Icons
            CatalogSection("Buttons with Icons") {
                CatalogRow("Leading Icon") {
                    SacramentButton(
                        text = "Add Item",
                        onClick = {},
                        leadingIcon = SacramentIcons.Add
                    )
                }
                CatalogRow("Trailing Icon") {
                    SacramentButton(
                        text = "Continue",
                        onClick = {},
                        trailingIcon = SacramentIcons.ArrowForward
                    )
                }
                CatalogRow("Disabled") {
                    SacramentButton(
                        text = "Disabled",
                        onClick = {},
                        enabled = false
                    )
                }
            }
            
            // Icon Buttons
            CatalogSection("Icon Buttons") {
                CatalogRow("Small") {
                    SacramentIconButton(
                        imageVector = SacramentIcons.Add,
                        contentDescription = "Add",
                        onClick = {},
                        size = SacramentIconButtonSize.Small
                    )
                }
                CatalogRow("Medium") {
                    SacramentIconButton(
                        imageVector = SacramentIcons.Add,
                        contentDescription = "Add",
                        onClick = {},
                        size = SacramentIconButtonSize.Medium
                    )
                }
                CatalogRow("Large") {
                    SacramentIconButton(
                        imageVector = SacramentIcons.Add,
                        contentDescription = "Add",
                        onClick = {},
                        size = SacramentIconButtonSize.Large
                    )
                }
            }
            
            // FABs
            CatalogSection("Floating Action Buttons") {
                CatalogRow("Small") {
                    SacramentFab(
                        imageVector = SacramentIcons.Add,
                        contentDescription = "Add",
                        onClick = {},
                        size = SacramentFabSize.Small
                    )
                }
                CatalogRow("Medium") {
                    SacramentFab(
                        imageVector = SacramentIcons.Add,
                        contentDescription = "Add",
                        onClick = {},
                        size = SacramentFabSize.Medium
                    )
                }
                CatalogRow("Large") {
                    SacramentFab(
                        imageVector = SacramentIcons.Add,
                        contentDescription = "Add",
                        onClick = {},
                        size = SacramentFabSize.Large
                    )
                }
            }
        }
    }
}



