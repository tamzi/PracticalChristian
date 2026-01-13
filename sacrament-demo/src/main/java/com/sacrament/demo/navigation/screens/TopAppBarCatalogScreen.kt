package com.sacrament.demo.navigation.screens

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
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Top App Bar detail screen.
 *
 * Displays information and examples of the SacramentTopAppBar component.
 *
 * Navigation path: Home → Navigation Components → Top App Bar (this screen)
 *
 * The Top App Bar is demonstrated at the top of this screen, showing:
 * - Title display
 * - Back navigation icon
 * - Consistent styling and spacing
 *
 * @param onNavigateBack Callback to navigate back to Navigation Components overview
 */
@Composable
fun TopAppBarCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Top App Bar",
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
            CatalogSection("Top App Bar") {
                CatalogRow("Live Example") {
                    SacramentText(
                        text = "The top app bar is shown at the top of this screen, demonstrating the navigation icon and title.",
                        style = SacramentTheme.typography.bodyMedium
                    )
                }
                
                CatalogRow("Usage") {
                    SacramentText(
                        text = "Top app bars provide context and actions for the current screen. They typically include:\n\n• Screen title\n• Navigation icon (back or menu)\n• Optional action icons\n• Consistent elevation and styling",
                        style = SacramentTheme.typography.bodyMedium
                    )
                }
                
                CatalogRow("Best Practices") {
                    SacramentText(
                        text = "• Keep titles concise and descriptive\n• Use back navigation when there's a clear parent screen\n• Limit action icons to 2-3 most important actions\n• Maintain consistent styling across the app",
                        style = SacramentTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}

/**
 * Row component for displaying top app bar information.
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
