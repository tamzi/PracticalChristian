package com.sacrament.demo.catalog.patterns

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.patterns.SacramentErrorState
import com.sacrament.ui.patterns.SacramentLoadingState
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.SacramentTheme

@Composable
fun PatternsCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Patterns",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(SacramentTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xl)
        ) {
            CatalogSection("Screen Scaffold") {
                SacramentText(
                    text = "The screen scaffold is used throughout this catalog app. It provides consistent structure with top bar, bottom bar, and FAB support.",
                    style = SacramentTheme.typography.bodyMedium
                )
            }
            
            CatalogSection("Empty State") {
                SacramentEmptyState(
                    title = "No items found",
                    message = "This is an example empty state pattern",
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            CatalogSection("Error State") {
                SacramentErrorState(
                    title = "Something went wrong",
                    message = "This is an example error state pattern",
                    onRetry = {},
                    modifier = Modifier.fillMaxWidth()
                )
            }
            
            CatalogSection("Loading State") {
                SacramentLoadingState(
                    message = "Loading content...",
                    modifier = Modifier.fillMaxWidth()
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

