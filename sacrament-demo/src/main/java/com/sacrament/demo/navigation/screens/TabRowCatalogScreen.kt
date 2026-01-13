package com.sacrament.demo.navigation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.navigation.SacramentTabItem
import com.sacrament.ui.components.navigation.SacramentTabRow
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Catalog screen demonstrating tab rows.
 *
 * Showcases SacramentTabRow with multiple tabs,
 * selected states, icons, and interactive behavior.
 *
 * Tab rows provide navigation between related content sections
 * within a single screen or feature area.
 *
 * @param onNavigateBack Callback to navigate back to the Navigation catalog screen
 */
@Composable
fun TabRowCatalogScreen(
    onNavigateBack: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Tab Row",
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
                // Basic tab row with icons
                CatalogSection("Tab Row with Icons") {
                    var selectedIndex by remember { mutableStateOf(0) }
                    
                    SacramentText(
                        text = "Tabs with icons and labels. Tap to switch between tabs.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )
                    
                    SacramentTabRow(
                        tabs = listOf(
                            SacramentTabItem("Home", SacramentIcons.Home),
                            SacramentTabItem("Notes", SacramentIcons.Edit),
                            SacramentTabItem("Profile", SacramentIcons.Book)
                        ),
                        selectedIndex = selectedIndex,
                        onTabSelected = { selectedIndex = it }
                    )
                }

                // Text-only tab row
                CatalogSection("Text-Only Tab Row") {
                    var selectedIndex by remember { mutableStateOf(0) }
                    
                    SacramentText(
                        text = "Tabs with labels only, no icons.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )
                    
                    SacramentTabRow(
                        tabs = listOf(
                            SacramentTabItem("Overview"),
                            SacramentTabItem("Details"),
                            SacramentTabItem("Settings")
                        ),
                        selectedIndex = selectedIndex,
                        onTabSelected = { selectedIndex = it }
                    )
                }

                // Usage guidelines
                CatalogSection("Usage") {
                    SacramentText(
                        text = "Tab rows organize content into related categories within a screen or feature area.",
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
                            text = "• Use 2-5 tabs per row",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Keep tab labels short and clear",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Group related content together",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Use icons for better recognition (optional)",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                    }
                }
            }
        }
    }
}
