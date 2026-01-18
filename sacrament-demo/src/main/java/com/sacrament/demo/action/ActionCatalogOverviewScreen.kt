package com.sacrament.demo.action

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
                SacramentListItem(
                    headline = "BUTTONS",
                    supporting = "Variants, Sizes, Tones, Icons",
                    onClick = onNavigateToButtons
                )

                SacramentListItem(
                    headline = "ICON BUTTONS",
                    supporting = "Variants, Sizes, Tones, States",
                    onClick = onNavigateToIconButtons
                )

                SacramentListItem(
                    headline = "FLOATING ACTION BUTTONS",
                    supporting = "Not yet implemented",
                    onClick = onNavigateToFABs
                )
            }
        }
    }
}

