package com.sacrament.demo.patterns

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
                PatternsCatalogListItem(
                    title = "SCREEN SCAFFOLD",
                    description = "Consistent screen structure with top bar and FAB",
                    onClick = onNavigateToScaffold
                )
                
                PatternsCatalogListItem(
                    title = "EMPTY STATE",
                    description = "Display when no content is available",
                    onClick = onNavigateToEmptyState
                )
                
                PatternsCatalogListItem(
                    title = "ERROR STATE",
                    description = "Display when an operation fails",
                    onClick = onNavigateToErrorState
                )
                
                PatternsCatalogListItem(
                    title = "LOADING STATE",
                    description = "Indicate content is being loaded",
                    onClick = onNavigateToLoadingState
                )
            }
        }
    }
}

/**
 * List item component for displaying pattern categories.
 */
@Composable
private fun PatternsCatalogListItem(
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
