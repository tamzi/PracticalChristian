package com.sacrament.demo.action.fab.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Catalog screen for Floating Action Buttons (FABs).
 *
 * Note: The SacramentFloatingActionButton component has not been implemented yet.
 * This screen serves as a placeholder in the design system catalog.
 *
 * When implemented, FABs would showcase:
 * - Sizes: Small, Medium, Large
 * - Variants: Filled, Outlined
 * - Extended FABs with text labels
 * - States: Default, Disabled, Pressed
 *
 * @param onNavigateBack Callback to navigate back to the Action catalog screen
 */
@Composable
fun FloatingActionButtonsCatalogScreen(
    onNavigateBack: () -> Unit
) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Floating Action Buttons",
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
                    .padding(
                        horizontal = SacramentTheme.spacing.xl,
                        vertical = SacramentTheme.spacing.xxl
                    ),
                verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SacramentText(
                    text = "Floating Action Buttons",
                    style = SacramentTheme.typography.headlineLarge
                )
                
                SacramentText(
                    text = "The SacramentFloatingActionButton component has not been implemented yet. This screen is a placeholder for future FAB examples.",
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.text.muted
                )
            }
        }
    }
}
