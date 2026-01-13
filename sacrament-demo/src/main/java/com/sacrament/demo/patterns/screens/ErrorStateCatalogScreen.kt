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
import com.sacrament.ui.patterns.SacramentErrorState
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

@Composable
fun ErrorStateCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = { CatalogTopAppBar(title = "Error State", onNavigateBack = onNavigateBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
        ) {
            CatalogSection("Error State Pattern") {
                CatalogRow("Example") {
                    SacramentErrorState(
                        modifier = Modifier.fillMaxWidth(),
                        title = "Something went wrong",
                        contentDescription = "Error state example",
                        message = "This is an example error state pattern used when an operation fails.",
                        action = {}
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
