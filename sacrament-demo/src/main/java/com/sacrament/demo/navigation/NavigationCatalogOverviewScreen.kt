package com.sacrament.demo.navigation

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
import androidx.compose.ui.semantics.Role
import com.sacrament.demo.CatalogSectionTitle
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Navigation Components catalog overview screen.
 *
 * Organizes navigation component examples into subcategories. Navigation components
 * help users move through the app and understand their current location.
 *
 * Navigation hierarchy:
 * - Home → Navigation Components (this screen) → TopAppBar/BottomBar/TabRow/NavigationRail
 *
 * Subcategories:
 * - **Top App Bar**: Screen header with title and actions
 * - **Bottom Bar**: Primary navigation for mobile
 * - **Tab Row**: Section navigation with tabs
 * - **Navigation Rail**: Side navigation for larger screens
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 * @param onNavigateToTopAppBar Callback to navigate to Top App Bar screen
 * @param onNavigateToBottomBar Callback to navigate to Bottom Bar screen
 * @param onNavigateToTabRow Callback to navigate to Tab Row screen
 * @param onNavigateToNavigationRail Callback to navigate to Navigation Rail screen
 */
@Composable
fun NavigationCatalogOverviewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToTopAppBar: () -> Unit,
    onNavigateToBottomBar: () -> Unit = {},
    onNavigateToTabRow: () -> Unit = {},
    onNavigateToNavigationRail: () -> Unit = {}
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Navigation Components",
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
                NavigationCatalogListItem(
                    title = "TOP APP BAR",
                    description = "Screen header with title and navigation",
                    onClick = onNavigateToTopAppBar
                )
                
                NavigationCatalogListItem(
                    title = "BOTTOM BAR",
                    description = "Primary navigation for mobile",
                    onClick = onNavigateToBottomBar
                )
                
                NavigationCatalogListItem(
                    title = "TAB ROW",
                    description = "Section navigation with tabs",
                    onClick = onNavigateToTabRow
                )
                
                NavigationCatalogListItem(
                    title = "NAVIGATION RAIL",
                    description = "Side navigation for larger screens",
                    onClick = onNavigateToNavigationRail
                )
            }
        }
    }
}

/**
 * List item component for displaying navigation component categories.
 */
@Composable
private fun NavigationCatalogListItem(
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                role = Role.Button,
                onClick = onClick
            )
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
