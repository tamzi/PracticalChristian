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
import com.sacrament.ui.components.content.SacramentChip
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Chips detail screen.
 *
 * Displays examples of SacramentChip in various states, including selected,
 * unselected, and with icons.
 *
 * Navigation path: Home → Content Components → Chips (this screen)
 *
 * States demonstrated:
 * - **Unselected**: Default state, available for selection
 * - **Selected**: Active state indicating user selection
 * - **With Icon**: Selected chip with a leading icon for visual reinforcement
 *
 * @param onNavigateBack Callback to navigate back to Content Components overview
 */
@Composable
fun ChipsCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Chips",
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
            CatalogSection("Chip States") {
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
                        leadingIcon = SacramentIcons.SacramentIconStar,
                        onClick = {}
                    )
                }
            }
        }
    }
}

/**
 * Row component for displaying chip examples.
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
