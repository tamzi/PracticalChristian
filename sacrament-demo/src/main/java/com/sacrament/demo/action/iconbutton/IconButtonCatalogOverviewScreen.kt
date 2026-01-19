package com.sacrament.demo.action.iconbutton

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
import com.sacrament.ui.components.content.list.SacramentListItem
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold

/**
 * Icon Button catalog overview screen.
 *
 * Organizes icon button examples into subcategories, allowing users to explore
 * specific aspects of the SacramentIconButton component. Each subcategory navigates
 * to its own dedicated detail screen.
 *
 * Navigation hierarchy:
 * - Home → Action Components → Icon Buttons (this screen) → Variants/Sizes/Tones/States
 *
 * Subcategories:
 * - **Variants**: Filled, Outlined, Ghost styles
 * - **Sizes**: Small, Medium, Large dimensions
 * - **Tones**: Brand, Neutral, Success, Warning, Error, Info color schemes
 * - **States**: Default, Disabled states
 *
 * @param onNavigateBack Callback to navigate back to Action Components screen
 * @param onNavigateToVariants Callback to navigate to Icon Button Variants screen
 * @param onNavigateToSizes Callback to navigate to Icon Button Sizes screen
 * @param onNavigateToTones Callback to navigate to Icon Button Tones screen
 * @param onNavigateToStates Callback to navigate to Icon Button States screen
 */
@Composable
fun IconButtonCatalogScreen(
    onNavigateBack: () -> Unit,
    onNavigateToVariants: () -> Unit,
    onNavigateToSizes: () -> Unit,
    onNavigateToTones: () -> Unit,
    onNavigateToStates: () -> Unit
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
                    .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
                verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.lg)
            ) {
                SacramentListItem(
                    headline = "ICON BUTTON VARIANTS",
                    supporting = "Filled, Outlined, Ghost",
                    onClick = onNavigateToVariants
                )

                SacramentListItem(
                    headline = "ICON BUTTON SIZES",
                    supporting = "Small (32dp), Medium (40dp), Large (48dp)",
                    onClick = onNavigateToSizes
                )

                SacramentListItem(
                    headline = "ICON BUTTON TONES",
                    supporting = "Brand, Neutral, Success, Warning, Error, Info",
                    onClick = onNavigateToTones
                )

                SacramentListItem(
                    headline = "ICON BUTTON STATES",
                    supporting = "Default, Disabled",
                    onClick = onNavigateToStates
                )
            }
        }
    }
}
