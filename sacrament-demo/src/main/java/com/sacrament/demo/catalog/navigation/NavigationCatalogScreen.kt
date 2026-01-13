package com.sacrament.demo.catalog.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.catalog.CatalogSection
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

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
