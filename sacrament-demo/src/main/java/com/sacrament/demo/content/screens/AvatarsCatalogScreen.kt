package com.sacrament.demo.content.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.content.SacramentAvatar
import com.sacrament.ui.components.content.SacramentAvatarSize
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Avatars detail screen.
 *
 * Displays examples of SacramentAvatar in various sizes, showing both placeholder
 * and initials variants.
 *
 * Navigation path: Home → Content Components → Avatars (this screen)
 *
 * Sizes demonstrated:
 * - **Small**: Compact size for lists or small UI elements
 * - **Medium**: Default size for most avatar use cases
 * - **Large**: Prominent size for profiles or hero sections
 *
 * Each size shows:
 * - Placeholder avatar (without initials)
 * - Avatar with initials (e.g., "AB")
 *
 * @param onNavigateBack Callback to navigate back to Content Components overview
 */
@Composable
fun AvatarsCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Avatars",
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
            CatalogSection("Avatar Sizes") {
                CatalogRow("Small") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
                    ) {
                        SacramentAvatar(size = SacramentAvatarSize.Small)
                        SacramentAvatar(size = SacramentAvatarSize.Small, initials = "AB")
                    }
                }
                CatalogRow("Medium") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
                    ) {
                        SacramentAvatar(size = SacramentAvatarSize.Medium)
                        SacramentAvatar(size = SacramentAvatarSize.Medium, initials = "CD")
                    }
                }
                CatalogRow("Large") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
                    ) {
                        SacramentAvatar(size = SacramentAvatarSize.Large)
                        SacramentAvatar(size = SacramentAvatarSize.Large, initials = "EF")
                    }
                }
            }
        }
    }
}

/**
 * Row component for displaying avatar examples.
 *
 * Used exclusively in AvatarsCatalogScreen to present labeled examples
 * of different avatar sizes.
 *
 * @param label Descriptive label for the example (e.g., "Small", "Medium", "Large")
 * @param content The composable avatar examples to display below the label
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
