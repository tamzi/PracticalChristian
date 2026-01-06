package com.sacrament.demo.catalog.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.SacramentTheme

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
                .padding(SacramentTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xl)
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

@Composable
private fun CatalogSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
    ) {
        SacramentText(
            text = title,
            style = SacramentTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = SacramentTheme.spacing.xs)
        )
        content()
    }
}

