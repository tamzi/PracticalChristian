package com.sacrament.demo.surface

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
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Surface Components catalog screen.
 *
 * Displays examples of surface-related components including cards, dialogs, and bottom sheets.
 * Surface components provide elevated containers and overlays for content.
 *
 * Navigation path: Home → Surface Components (this screen)
 *
 * Components demonstrated:
 * - **Cards**: Basic card with content
 * - **Dialogs**: Interactive demo not shown (requires state management)
 * - **Sheets**: Bottom sheet demo not shown (requires state management)
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 */
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
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

/**
 * Row component for displaying surface component examples.
 *
 * Used exclusively in SurfaceCatalogScreen to present labeled examples of
 * surface components (cards, dialogs, sheets).
 *
 * @param label Descriptive label for the example (e.g., "Basic Card")
 * @param content The composable content to display below the label
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

