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
import com.sacrament.ui.components.action.SacramentButtonSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Button Sizes detail screen.
 *
 * Displays examples of the three button sizes available in the SacramentButton component.
 * Size affects padding, text size, and overall button dimensions.
 *
 * Navigation path: Home → Action Components → Buttons → Button Sizes (this screen)
 *
 * Sizes demonstrated:
 * - **Small**: Compact size for dense layouts or less prominent actions
 * - **Medium**: Default size, balanced for most use cases
 * - **Large**: Prominent size for primary actions or touch-focused interfaces
 *
 * @param onNavigateBack Callback to navigate back to the Buttons overview screen
 */
@Composable
fun ButtonSizesCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Button Sizes",
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
            CatalogSection("Button Sizes") {
                CatalogRow("Small") {
                    SacramentButton(
                        text = "Small Button",
                        onClick = {},
                        size = SacramentButtonSize.Small
                    )
                }
                CatalogRow("Medium") {
                    SacramentButton(
                        text = "Medium Button",
                        onClick = {},
                        size = SacramentButtonSize.Medium
                    )
                }
                CatalogRow("Large") {
                    SacramentButton(
                        text = "Large Button",
                        onClick = {},
                        size = SacramentButtonSize.Large
                    )
                }
            }
        }
    }
}

/**
 * Row component for displaying button size examples.
 *
 * Used exclusively in ButtonSizesCatalogScreen to present labeled examples
 * of different button sizes.
 *
 * @param label Descriptive label for the example (e.g., "Small", "Medium", "Large")
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
