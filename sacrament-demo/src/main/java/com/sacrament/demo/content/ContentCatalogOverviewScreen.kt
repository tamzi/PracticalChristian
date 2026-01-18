package com.sacrament.demo.content

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
 * Content Components catalog overview screen.
 *
 * This intermediate screen organizes content component examples into subcategories.
 * Each subcategory navigates to its own dedicated screen with detailed examples.
 *
 * Navigation hierarchy:
 * - Home → Content Components (this screen) → Avatars/Badges/Chips/Tags
 *
 * Subcategories:
 * - **Avatars**: Small, Medium, Large sizes with placeholder and initials
 * - **Badges**: Brand, Neutral, Success, Warning, Error tones
 * - **Chips**: Unselected, Selected, With icons states
 * - **Tags**: Neutral, Brand, Success, Warning, Error, Info tones
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 * @param onNavigateToAvatars Callback to navigate to Avatars screen
 * @param onNavigateToBadges Callback to navigate to Badges screen
 * @param onNavigateToChips Callback to navigate to Chips screen
 * @param onNavigateToTags Callback to navigate to Tags screen
 */
@Composable
fun ContentCatalogOverviewScreen(
    onNavigateBack: () -> Unit,
    onNavigateToAvatars: () -> Unit,
    onNavigateToBadges: () -> Unit,
    onNavigateToChips: () -> Unit,
    onNavigateToTags: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Content Components",
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
                    headline = "AVATARS",
                    supporting = "Small, Medium, Large sizes",
                    onClick = onNavigateToAvatars
                )

                SacramentListItem(
                    headline = "BADGES",
                    supporting = "Brand, Neutral, Success, Warning, Error tones",
                    onClick = onNavigateToBadges
                )

                SacramentListItem(
                    headline = "CHIPS",
                    supporting = "Selected, Unselected, With icons",
                    onClick = onNavigateToChips
                )

                SacramentListItem(
                    headline = "TAGS",
                    supporting = "Neutral, Brand, Success, Warning, Error, Info tones",
                    onClick = onNavigateToTags
                )
            }
        }
    }
}
