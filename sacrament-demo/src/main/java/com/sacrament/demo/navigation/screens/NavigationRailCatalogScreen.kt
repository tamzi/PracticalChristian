package com.sacrament.demo.navigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.navigation.SacramentNavigationRail
import com.sacrament.ui.components.navigation.SacramentNavigationRailItem
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Catalog screen demonstrating navigation rails.
 *
 * Showcases SacramentNavigationRail with multiple items,
 * selected states, and interactive behavior.
 *
 * Navigation rails provide side navigation for larger screen layouts
 * like tablets and desktops, offering an alternative to bottom bars.
 *
 * @param onNavigateBack Callback to navigate back to the Navigation catalog screen
 */
@Composable
fun NavigationRailCatalogScreen(
    onNavigateBack: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Navigation Rail",
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
                // Basic navigation rail example
                CatalogSection("Navigation Rail") {
                    var selectedIndex by remember { mutableIntStateOf(0) }
                    
                    SacramentText(
                        text = "Side navigation for larger screen layouts. Tap items to see selection states.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )
                    
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SacramentTheme.colors.surfaces.surface),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        SacramentNavigationRail(
                            items = listOf(
                                SacramentNavigationRailItem("Home", SacramentIcons.SacramentIconHome),
                                SacramentNavigationRailItem("Notes", SacramentIcons.SacramentIconEdit),
                                SacramentNavigationRailItem("Profile", SacramentIcons.SacramentIconBook),
                                SacramentNavigationRailItem("Settings", SacramentIcons.SacramentIconSettings)
                            ),
                            selectedIndex = selectedIndex,
                            onItemSelected = { selectedIndex = it },
                            modifier = Modifier.fillMaxHeight()
                        )
                        
                        // Placeholder content area
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .padding(SacramentTheme.spacing.xl),
                            contentAlignment = Alignment.Center
                        ) {
                            SacramentText(
                                text = "Content area for selected tab",
                                style = SacramentTheme.typography.bodyLarge,
                                color = SacramentTheme.colors.text.muted
                            )
                        }
                    }
                }

                // Usage guidelines
                CatalogSection("Usage") {
                    SacramentText(
                        text = "Navigation rails provide side navigation for larger layouts, typically on tablets and desktops.",
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
                            text = "• Use on tablets and larger screens",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Include icons with text labels",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Place on the left side of the screen",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Combine with top app bar for headers",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                    }
                }
            }
        }
    }
}
