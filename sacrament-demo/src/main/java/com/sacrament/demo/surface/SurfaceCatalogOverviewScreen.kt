package com.sacrament.demo.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSectionTitle
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Surface Components catalog overview screen.
 *
 * Organizes surface component examples into subcategories. Surface components
 * provide elevated containers and overlays for content presentation.
 *
 * Navigation hierarchy:
 * - Home → Surface Components (this screen) → Cards/Dialogs/Sheets
 *
 * Subcategories:
 * - **Cards**: Container for related content and actions
 * - **Dialogs**: Modal overlays for critical decisions
 * - **Sheets**: Elevated surfaces and bottom sheets
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 * @param onNavigateToCards Callback to navigate to Cards screen
 * @param onNavigateToDialogs Callback to navigate to Dialogs screen
 * @param onNavigateToSheets Callback to navigate to Sheets screen
 */
@Composable
fun SurfaceCatalogOverviewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToCards: () -> Unit,
    onNavigateToDialogs: () -> Unit = {},
    onNavigateToSheets: () -> Unit = {}
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Surface Components",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(SacramentTheme.colors.surfaces.background)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
                verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.lg)
            ) {
                SurfaceCatalogListItem(
                    title = "CARDS",
                    description = "Container for grouped content",
                    onClick = onNavigateToCards
                )
                
                SurfaceCatalogListItem(
                    title = "DIALOGS",
                    description = "Modal overlays for critical decisions",
                    onClick = onNavigateToDialogs
                )
                
                SurfaceCatalogListItem(
                    title = "SHEETS",
                    description = "Elevated surfaces and bottom sheets",
                    onClick = onNavigateToSheets
                )
            }
        }
    }
}

/**
 * List item component for displaying surface component categories.
 */
@Composable
private fun SurfaceCatalogListItem(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = SacramentTheme.spacing.md)
    ) {
        CatalogSectionTitle(text = title)
        Spacer(modifier = Modifier.height(SacramentTheme.spacing.xs))
        SacramentText(
            text = description,
            style = SacramentTheme.typography.bodyMedium,
            color = SacramentTheme.colors.text.muted
        )
    }
}
