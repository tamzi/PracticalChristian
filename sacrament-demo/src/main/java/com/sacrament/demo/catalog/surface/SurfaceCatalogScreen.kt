package com.sacrament.demo.catalog.surface

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.SacramentTheme

@Composable
fun SurfaceCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Surface Components",
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
            CatalogSection("Cards") {
                CatalogRow("Basic Card") {
                    SacramentCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SacramentText(
                            text = "Card content",
                            modifier = Modifier.padding(SacramentTheme.spacing.md)
                        )
                    }
                }
            }
            
            CatalogSection("Dialogs") {
                SacramentText(
                    text = "Dialog component (interactive demo not shown).",
                    style = SacramentTheme.typography.bodyMedium
                )
            }
            
            CatalogSection("Sheets") {
                SacramentText(
                    text = "Bottom sheet component (interactive demo not shown).",
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

@Composable
private fun CatalogRow(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xs)
    ) {
        SacramentText(
            text = label,
            style = SacramentTheme.typography.labelMedium,
            modifier = Modifier.padding(start = SacramentTheme.spacing.xs)
        )
        content()
    }
}

