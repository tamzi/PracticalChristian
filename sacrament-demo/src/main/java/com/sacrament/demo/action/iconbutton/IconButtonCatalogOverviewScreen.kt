package com.sacrament.demo.action.iconbutton

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.sacrament.demo.CatalogSectionTitle
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

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
                IconButtonCatalogListItem(
                    title = "ICON BUTTON VARIANTS",
                    description = "Filled, Outlined, Ghost",
                    onClick = onNavigateToVariants
                )

                IconButtonCatalogListItem(
                    title = "ICON BUTTON SIZES",
                    description = "Small (32dp), Medium (40dp), Large (48dp)",
                    onClick = onNavigateToSizes
                )

                IconButtonCatalogListItem(
                    title = "ICON BUTTON TONES",
                    description = "Brand, Neutral, Success, Warning, Error, Info",
                    onClick = onNavigateToTones
                )

                IconButtonCatalogListItem(
                    title = "ICON BUTTON STATES",
                    description = "Default, Disabled",
                    onClick = onNavigateToStates
                )
            }
        }
    }
}

/**
 * List item component for displaying icon button subcategories.
 *
 * Used exclusively in IconButtonCatalogScreen to present clickable items that
 * navigate to specific icon button detail screens.
 *
 * @param title The subcategory name (e.g., "ICON BUTTON VARIANTS")
 * @param description Brief summary of what's included in the subcategory
 * @param onClick Callback invoked when the item is tapped
 */
@Composable
private fun IconButtonCatalogListItem(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
            .padding(vertical = SacramentTheme.spacing.md)
    ) {
        CatalogSectionTitle(text = title)
        
        Spacer(modifier = Modifier.height(SacramentTheme.spacing.xs))
        
        SacramentText(
            text = description,
            style = SacramentTheme.typography.bodyMedium,
            color = SacramentTheme.colors.text.muted
        )
    }
}
