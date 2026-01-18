package com.sacrament.demo.navigation

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
import com.sacrament.ui.components.content.SacramentListItem
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold

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
                SacramentListItem(
                    headline = "TOP APP BAR",
                    supporting = "Screen header with title and navigation",
                    onClick = onNavigateToTopAppBar
                )
                
                SacramentListItem(
                    headline = "BOTTOM BAR",
                    supporting = "Primary navigation for mobile",
                    onClick = onNavigateToBottomBar
                )
                
                SacramentListItem(
                    headline = "TAB ROW",
                    supporting = "Section navigation with tabs",
                    onClick = onNavigateToTabRow
                )
                
                SacramentListItem(
                    headline = "NAVIGATION RAIL",
                    supporting = "Side navigation for larger screens",
                    onClick = onNavigateToNavigationRail
                )
            }
        }
    }
}
