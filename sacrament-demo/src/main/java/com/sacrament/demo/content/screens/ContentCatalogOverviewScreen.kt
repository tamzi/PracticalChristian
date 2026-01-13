package com.sacrament.demo.content.screens

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
                ContentCatalogListItem(
                    title = "AVATARS",
                    description = "Small, Medium, Large sizes",
                    onClick = onNavigateToAvatars
                )

                ContentCatalogListItem(
                    title = "BADGES",
                    description = "Brand, Neutral, Success, Warning, Error tones",
                    onClick = onNavigateToBadges
                )

                ContentCatalogListItem(
                    title = "CHIPS",
                    description = "Selected, Unselected, With icons",
                    onClick = onNavigateToChips
                )

                ContentCatalogListItem(
                    title = "TAGS",
                    description = "Neutral, Brand, Success, Warning, Error, Info tones",
                    onClick = onNavigateToTags
                )
            }
        }
    }
}

/**
 * List item component for displaying content component categories.
 *
 * Used exclusively in ContentCatalogOverviewScreen to present clickable items that
 * navigate to specific content component detail screens.
 *
 * @param title The category name (e.g., "AVATARS")
 * @param description Brief summary of what's included in the category
 * @param onClick Callback invoked when the item is tapped
 */
@Composable
private fun ContentCatalogListItem(
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
