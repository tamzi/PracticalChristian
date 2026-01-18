package com.sacrament.demo.action.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.content.SacramentListItem
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold

/**
 * Button catalog overview screen.
 *
 * This intermediate screen organizes button examples into subcategories, allowing users
 * to explore specific aspects of the SacramentButton component. Each subcategory
 * navigates to its own dedicated screen with detailed examples.
 *
 * Navigation hierarchy:
 * - Home → Action Components → Buttons (this screen) → Button Variants/Sizes/Tones/Icons
 *
 * Subcategories:
 * - **Variants**: Filled, Outlined, Ghost styles
 * - **Sizes**: Small, Medium, Large dimensions
 * - **Tones**: Brand, Neutral, Success, Warning, Error, Info color schemes
 * - **Icons**: Leading icons, trailing icons, disabled states
 *
 * @param onNavigateBack Callback to navigate back to Action Components screen
 * @param onNavigateToVariants Callback to navigate to Button Variants screen
 * @param onNavigateToSizes Callback to navigate to Button Sizes screen
 * @param onNavigateToTones Callback to navigate to Button Tones screen
 * @param onNavigateToIcons Callback to navigate to Buttons with Icons screen
 */
@Composable
fun ButtonCatalogScreen(
    onNavigateBack: () -> Unit,
    onNavigateToVariants: () -> Unit,
    onNavigateToSizes: () -> Unit,
    onNavigateToTones: () -> Unit,
    onNavigateToIcons: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Buttons",
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
                SacramentListItem(
                    headline = "BUTTON VARIANTS",
                    supporting = "Filled, Outlined, Ghost",
                    onClick = onNavigateToVariants
                )

                SacramentListItem(
                    headline = "BUTTON SIZES",
                    supporting = "Small, Medium, Large",
                    onClick = onNavigateToSizes
                )

                SacramentListItem(
                    headline = "BUTTON TONES",
                    supporting = "Brand, Neutral, Success, Warning, Error, Info",
                    onClick = onNavigateToTones
                )

                SacramentListItem(
                    headline = "BUTTONS WITH ICONS",
                    supporting = "Leading icons, Trailing icons, Disabled states",
                    onClick = onNavigateToIcons
                )
            }
        }
    }
}


