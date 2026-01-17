package com.sacrament.demo.action

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
 * Action Components catalog overview screen.
 *
 * This is the top-level screen for Action Components in the design system catalog.
 * It displays a list of action component categories (Buttons, Icon Buttons, FABs),
 * each of which navigates to its own detailed subpage with specific examples.
 *
 * Navigation hierarchy:
 * - Home → Action Components (this screen) → Button → Button Variants/Sizes/Tones/Icons
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 * @param onNavigateToButtons Callback to navigate to the buttons catalog subpage
 * @param onNavigateToIconButtons Callback to navigate to the icon buttons catalog subpage
 * @param onNavigateToFABs Callback to navigate to the floating action buttons catalog subpage
 */
@Composable
fun ActionCatalogScreen(
    onNavigateBack: () -> Unit,
    onNavigateToButtons: () -> Unit,
    onNavigateToIconButtons: () -> Unit = {},
    onNavigateToFABs: () -> Unit = {}
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Action Components",
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
                ActionCatalogListItem(
                    title = "BUTTONS",
                    description = "Variants, Sizes, Tones, Icons",
                    onClick = onNavigateToButtons
                )

                ActionCatalogListItem(
                    title = "ICON BUTTONS",
                    description = "Variants, Sizes, Tones, States",
                    onClick = onNavigateToIconButtons
                )

                ActionCatalogListItem(
                    title = "FLOATING ACTION BUTTONS",
                    description = "Not yet implemented",
                    onClick = onNavigateToFABs
                )
            }
        }
    }
}

/**
 * List item component for displaying action component categories.
 *
 * Used exclusively in ActionCatalogScreen to present clickable items that
 * navigate to specific action component subpages (e.g., Buttons, Icon Buttons, FABs).
 *
 * @param title The category name (e.g., "BUTTONS")
 * @param description Brief summary of what's included in the category
 * @param onClick Callback invoked when the item is tapped
 */
@Composable
private fun ActionCatalogListItem(
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



