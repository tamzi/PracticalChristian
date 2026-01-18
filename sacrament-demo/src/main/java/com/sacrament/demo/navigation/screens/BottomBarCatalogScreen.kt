package com.sacrament.demo.navigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.navigation.SacramentBottomBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Catalog screen demonstrating bottom navigation bars.
 *
 * Showcases SacramentBottomBar with multiple navigation items,
 * selected states, and interactive behavior.
 *
 * Bottom bars are used for primary navigation between top-level
 * destinations in an app (typically 3-5 items).
 *
 * @param onNavigateBack Callback to navigate back to the Navigation catalog screen
 */
@Composable
fun BottomBarCatalogScreen(
    onNavigateBack: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Bottom Bar",
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
                // Basic bottom bar example
                CatalogSection("Basic Bottom Bar") {
                    var selectedIndex by remember { mutableIntStateOf(0) }
                    
                    SacramentText(
                        text = "A bottom bar with three navigation items. Tap items to see selection states.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )
                    
                    SacramentBottomBar {
                        BottomBarItem(
                            label = "Home",
                            icon = SacramentIcons.SacramentIconHome,
                            selected = selectedIndex == 0,
                            onClick = { selectedIndex = 0 }
                        )
                        BottomBarItem(
                            label = "Notes",
                            icon = SacramentIcons.SacramentIconEdit,
                            selected = selectedIndex == 1,
                            onClick = { selectedIndex = 1 }
                        )
                        BottomBarItem(
                            label = "Profile",
                            icon = SacramentIcons.SacramentIconBook,
                            selected = selectedIndex == 2,
                            onClick = { selectedIndex = 2 }
                        )
                    }
                }

                // Description section
                CatalogSection("Usage") {
                    SacramentText(
                        text = "Bottom bars are used for primary navigation between top-level destinations in an app.",
                        style = SacramentTheme.typography.bodyMedium
                    )
                    
                    SacramentText(
                        text = "Best practices:",
                        style = SacramentTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = SacramentTheme.spacing.md)
                    )
                    
                    Column(
                        modifier = Modifier.padding(start = SacramentTheme.spacing.md),
                        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xs)
                    ) {
                        SacramentText(
                            text = "• Use 3-5 top-level destinations",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Include icons with labels for clarity",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Keep labels short and descriptive",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Maintain visibility across all screens",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                    }
                }
            }
        }
    }
}

/**
 * Individual bottom bar navigation item.
 *
 * Displays an icon and label, with visual feedback for selected state.
 */
@Composable
fun RowScope.BottomBarItem(
    label: String,
    icon: ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = SacramentTheme.spacing
    val colors = SacramentTheme.colors
    val contentColor = if (selected) colors.brand.primary else colors.text.muted
    
    Column(
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .clickable(role = Role.Tab, onClick = onClick)
            .padding(vertical = spacing.xs),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(spacing.xs, Alignment.CenterVertically),
    ) {
        SacramentIcon(
            imageVector = icon,
            contentDescription = label,
            tint = contentColor,
            size = SacramentTheme.iconSizes.md,
        )
        SacramentText(
            text = label,
            style = SacramentTheme.typography.labelSmall,
            color = contentColor,
        )
    }
}
