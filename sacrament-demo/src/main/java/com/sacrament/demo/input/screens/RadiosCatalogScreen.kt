package com.sacrament.demo.input.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.sacrament.ui.components.input.SacramentRadio
import com.sacrament.ui.components.input.SacramentRadioSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

@Composable
fun RadiosCatalogScreen(onNavigateBack: () -> Unit) {
    var radioSelectedSmall by remember { mutableStateOf(false) }
    var radioSelectedMedium by remember { mutableStateOf(false) }
    
    SacramentScreenScaffold(
        topBar = { CatalogTopAppBar(title = "Radio Buttons", onNavigateBack = onNavigateBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
        ) {
            CatalogSection("Radio Button Sizes") {
                CatalogRow("Small") {
                    SacramentRadio(
                        selected = radioSelectedSmall,
                        onClick = { radioSelectedSmall = !radioSelectedSmall },
                        size = SacramentRadioSize.Small
                    )
                }
                CatalogRow("Medium") {
                    SacramentRadio(
                        selected = radioSelectedMedium,
                        onClick = { radioSelectedMedium = !radioSelectedMedium },
                        size = SacramentRadioSize.Medium
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
