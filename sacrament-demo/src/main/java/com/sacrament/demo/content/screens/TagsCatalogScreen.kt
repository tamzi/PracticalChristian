package com.sacrament.demo.content.screens

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
import com.sacrament.ui.components.content.SacramentTag
import com.sacrament.ui.components.content.SacramentTagTone
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Tags detail screen.
 *
 * Displays examples of SacramentTag with various tones for semantic meaning.
 *
 * Navigation path: Home → Content Components → Tags (this screen)
 *
 * Tones demonstrated:
 * - **Neutral**: Subtle color for general tags
 * - **Brand**: Primary brand color for featured tags
 * - **Success**: Green tone for positive/verified tags
 * - **Warning**: Orange tone for caution tags
 * - **Error**: Red tone for error/critical tags
 * - **Info**: Blue tone for informational tags
 *
 * @param onNavigateBack Callback to navigate back to Content Components overview
 */
@Composable
fun TagsCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Tags",
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
            CatalogSection("Tag Tones") {
                CatalogRow("Neutral") {
                    SacramentTag(text = "Tag", tone = SacramentTagTone.Neutral)
                }
                CatalogRow("Brand") {
                    SacramentTag(text = "Featured", tone = SacramentTagTone.Brand)
                }
                CatalogRow("Success") {
                    SacramentTag(text = "Verified", tone = SacramentTagTone.Success)
                }
                CatalogRow("Warning") {
                    SacramentTag(text = "Warning", tone = SacramentTagTone.Warning)
                }
                CatalogRow("Error") {
                    SacramentTag(text = "Error", tone = SacramentTagTone.Error)
                }
                CatalogRow("Info") {
                    SacramentTag(text = "Info", tone = SacramentTagTone.Info)
                }
            }
        }
    }
}

/**
 * Row component for displaying tag examples.
 */
@Composable
private fun CatalogRow(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
    ) {
        SacramentText(
            text = label,
            style = SacramentTheme.typography.bodyMedium,
            color = SacramentTheme.colors.text.muted
        )
        content()
    }
}
