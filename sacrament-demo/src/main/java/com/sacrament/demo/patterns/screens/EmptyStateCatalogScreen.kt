package com.sacrament.demo.patterns.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

@Composable
fun EmptyStateCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = { CatalogTopAppBar(title = "Empty State", onNavigateBack = onNavigateBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
        ) {
            CatalogSection("Empty State Pattern") {
                CatalogRow("Example") {
                    SacramentEmptyState(
                        modifier = Modifier.fillMaxWidth(),
                        icon = SacramentIcons.SacramentIconInfo,
                        title = "No items found",
                        contentDescription = "Empty state example",
                        description = "This is an example empty state pattern used when no content is available."
                    )
                }
            }
        }
    }
}

@Composable
private fun CatalogRow(label: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)) {
        SacramentText(text = label, style = SacramentTheme.typography.bodyMedium, color = SacramentTheme.colors.text.muted)
        content()
    }
}
