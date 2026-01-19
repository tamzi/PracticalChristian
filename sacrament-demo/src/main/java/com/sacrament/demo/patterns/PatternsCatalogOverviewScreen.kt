package com.sacrament.demo.patterns

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
 * Patterns catalog overview screen.
 *
 * Organizes UI pattern examples into subcategories. Patterns combine multiple
 * components to solve recurring design problems consistently.
 *
 * Navigation hierarchy:
 * - Home → Patterns (this screen) → Individual patterns
 *
 * Subcategories:
 * - **Screen Scaffold**: Consistent screen structure
 * - **Empty State**: No content available
 * - **Error State**: Operation failed
 * - **Loading State**: Content loading
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 * @param onNavigateToScaffold Callback to navigate to Screen Scaffold screen
 * @param onNavigateToEmptyState Callback to navigate to Empty State screen
 * @param onNavigateToErrorState Callback to navigate to Error State screen
 * @param onNavigateToLoadingState Callback to navigate to Loading State screen
 */
@Composable
fun PatternsCatalogOverviewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToScaffold: () -> Unit,
    onNavigateToEmptyState: () -> Unit,
    onNavigateToErrorState: () -> Unit,
    onNavigateToLoadingState: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Patterns",
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
                    headline = "SCREEN SCAFFOLD",
                    supporting = "Consistent screen structure with top bar and FAB",
                    onClick = onNavigateToScaffold
                )
                
                SacramentListItem(
                    headline = "EMPTY STATE",
                    supporting = "Display when no content is available",
                    onClick = onNavigateToEmptyState
                )
                
                SacramentListItem(
                    headline = "ERROR STATE",
                    supporting = "Display when an operation fails",
                    onClick = onNavigateToErrorState
                )
                
                SacramentListItem(
                    headline = "LOADING STATE",
                    supporting = "Indicate content is being loaded",
                    onClick = onNavigateToLoadingState
                )
            }
        }
    }
}
