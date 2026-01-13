package com.sacrament.demo.content

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
import com.sacrament.ui.components.content.SacramentBadge
import com.sacrament.ui.components.content.SacramentBadgeTone
import com.sacrament.ui.components.content.SacramentChip
import com.sacrament.ui.components.content.SacramentTag
import com.sacrament.ui.components.content.SacramentTagTone
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Content Components catalog screen.
 *
 * Displays examples of content-related components including avatars, badges, chips, and tags.
 * Each component is shown with various sizes, states, or tones to demonstrate flexibility.
 *
 * Navigation path: Home → Content Components (this screen)
 *
 * Components demonstrated:
 * - **Avatars**: Small, Medium, Large sizes with placeholder and initial states
 * - **Badges**: Brand, Neutral, Success, Warning, Error tones
 * - **Chips**: Unselected, Selected, With icons
 * - **Tags**: Neutral, Brand, Success, Warning, Error, Info tones
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 */
@Composable
fun ContentCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Content Components",
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
            // Avatars
            CatalogSection("Avatars - Sizes") {
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
                        SacramentAvatar(size = SacramentAvatarSize.Medium, initials = "AB")
                    }
                }
                CatalogRow("Large") {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
                    ) {
                        SacramentAvatar(size = SacramentAvatarSize.Large)
                        SacramentAvatar(size = SacramentAvatarSize.Large, initials = "AB")
                    }
                }
            }
            
            // Badges
            CatalogSection("Badges - Tones") {
                CatalogRow("Brand") {
                    SacramentBadge(text = "New", tone = SacramentBadgeTone.Brand)
                }
                CatalogRow("Neutral") {
                    SacramentBadge(text = "Default", tone = SacramentBadgeTone.Neutral)
                }
                CatalogRow("Success") {
                    SacramentBadge(text = "Active", tone = SacramentBadgeTone.Success)
                }
                CatalogRow("Warning") {
                    SacramentBadge(text = "Pending", tone = SacramentBadgeTone.Warning)
                }
                CatalogRow("Error") {
                    SacramentBadge(text = "Error", tone = SacramentBadgeTone.Error)
                }
            }
            
            // Chips
            CatalogSection("Chips") {
                CatalogRow("Unselected") {
                    SacramentChip(label = "Morning", selected = false, onClick = {})
                }
                CatalogRow("Selected") {
                    SacramentChip(label = "Evening", selected = true, onClick = {})
                }
                CatalogRow("With Icon") {
                    SacramentChip(
                        label = "Favorite",
                        selected = true,
                        leadingIcon = SacramentIcons.Star,
                        onClick = {}
                    )
                }
            }
            
            // Tags
            CatalogSection("Tags - Tones") {
                CatalogRow("Neutral") {
                    SacramentTag(text = "Tag", tone = SacramentTagTone.Neutral)
                }
                CatalogRow("Brand") {
                    SacramentTag(text = "Featured", tone = SacramentTagTone.Brand)
                }
                CatalogRow("Success") {
                    SacramentTag(text = "Verified", tone = SacramentTagTone.Success)
                }
                CatalogRow("Warning") {
                    SacramentTag(text = "Warning", tone = SacramentTagTone.Warning)
                }
                CatalogRow("Error") {
                    SacramentTag(text = "Error", tone = SacramentTagTone.Error)
                }
                CatalogRow("Info") {
                    SacramentTag(text = "Info", tone = SacramentTagTone.Info)
                }
            }
        }
    }
}

/**
 * Row component for displaying content component examples.
 *
 * Used exclusively in ContentCatalogScreen to present labeled examples of
 * content components (avatars, badges, chips, tags).
 *
 * @param label Descriptive label for the example (e.g., "Small", "Brand")
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

