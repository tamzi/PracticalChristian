package com.sacrament.demo.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Navigation Components catalog screen.
 *
 * Displays information about navigation components that help users move through
 * the app, including top app bars, bottom bars, tabs, and navigation rails.
 *
 * Navigation path: Home → Navigation Components (this screen)
 *
 * Components referenced:
 * - **Top App Bar**: Demonstrated at the top of this screen
 * - **Bottom Bar**: Bottom navigation for primary destinations (not shown)
 * - **Tab Row**: Tab-based navigation within a section (not shown)
 * - **Navigation Rail**: Side navigation for tablet layouts (not shown)
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 */
@Composable
fun NavigationCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Navigation Components",
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
            CatalogSection("Top App Bar") {
                SacramentText(
                    text = "The top app bar is shown at the top of this screen.",
                    style = SacramentTheme.typography.bodyMedium
                )
            }
            
            CatalogSection("Bottom Bar") {
                SacramentText(
                    text = "Bottom navigation bar component (not shown in catalog).",
                    style = SacramentTheme.typography.bodyMedium
                )
            }
            
            CatalogSection("Tab Row") {
                SacramentText(
                    text = "Tab navigation component (not shown in catalog).",
                    style = SacramentTheme.typography.bodyMedium
                )
            }
            
            CatalogSection("Navigation Rail") {
                SacramentText(
                    text = "Navigation rail for tablet layouts (not shown in catalog).",
                    style = SacramentTheme.typography.bodyMedium
                )
            }
        }
    }
}
