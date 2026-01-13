package com.sacrament.demo.content.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.content.SacramentBadge
import com.sacrament.ui.components.content.SacramentBadgeTone
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Badges detail screen.
 *
 * Displays examples of SacramentBadge with various tones for semantic meaning.
 *
 * Navigation path: Home → Content Components → Badges (this screen)
 *
 * Tones demonstrated:
 * - **Brand**: Primary brand color for featured items
 * - **Neutral**: Subtle color for general indicators
 * - **Success**: Green tone for active/completed states
 * - **Warning**: Orange tone for pending/caution states
 * - **Error**: Red tone for error/critical states
 *
 * @param onNavigateBack Callback to navigate back to Content Components overview
 */
@Composable
fun BadgesCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Badges",
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
            CatalogSection("Badge Tones") {
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
        }
    }
}

/**
 * Row component for displaying badge examples.
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
