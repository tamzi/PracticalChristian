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
import com.sacrament.demo.catalog.CatalogSection
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.patterns.SacramentErrorState
import com.sacrament.ui.patterns.SacramentLoadingState
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

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
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
        ) {
            CatalogSection("Screen Scaffold") {
                SacramentText(
                    text = "The screen scaffold is used throughout this catalog app. It provides consistent structure with top bar, bottom bar, and FAB support.",
                    style = SacramentTheme.typography.bodyMedium
                )
            }
            
            CatalogSection("Empty State") {
                SacramentEmptyState(
                    modifier = Modifier.fillMaxWidth(),
                    icon = SacramentIcons.Info,
                    title = "No items found",
                    contentDescription = "Example empty state",
                    description = "This is an example empty state pattern",
                )
            }

            CatalogSection("Error State") {
                SacramentErrorState(
                    modifier = Modifier.fillMaxWidth(),
                    title = "Something went wrong",
                    contentDescription = "Example error state",
                    message = "This is an example error state pattern",
                    action = {},
                )
            }

            CatalogSection("Loading State") {
                SacramentLoadingState(
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}
