package com.sacrament.demo.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.content.list.SacramentListItem
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold

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
                SacramentListItem(
                    headline = "CARDS",
                    supporting = "Container for grouped content",
                    onClick = onNavigateToCards
                )
                
                SacramentListItem(
                    headline = "DIALOGS",
                    supporting = "Modal overlays for critical decisions",
                    onClick = onNavigateToDialogs
                )
                
                SacramentListItem(
                    headline = "SHEETS",
                    supporting = "Elevated surfaces and bottom sheets",
                    onClick = onNavigateToSheets
                )
            }
        }
    }
}
