package com.sacrament.demo.surface.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sacrament.demo.CatalogSection
import com.sacrament.demo.CatalogTopAppBar
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.surface.SacramentModalBottomSheet
import com.sacrament.ui.components.surface.SacramentSheet
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Catalog screen demonstrating sheets.
 *
 * Showcases SacramentSheet and SacramentModalBottomSheet with different configurations:
 * - Basic sheets as container surfaces
 * - Modal bottom sheets for contextual content
 * - Various use cases and patterns
 *
 * Sheets provide elevated surfaces for displaying content, especially
 * bottom sheets that slide up from the bottom of the screen.
 *
 * @param onNavigateBack Callback to navigate back to the Surface catalog screen
 */
@Composable
fun SheetsCatalogScreen(
    onNavigateBack: () -> Unit
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Sheets",
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
                verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
            ) {
                // Basic sheet
                CatalogSection("Basic Sheet") {
                    SacramentText(
                        text = "A sheet provides an elevated surface for displaying content.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )

                    SacramentSheet {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(SacramentTheme.spacing.lg),
                            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
                        ) {
                            SacramentText(
                                text = "Sheet Content",
                                style = SacramentTheme.typography.titleMedium
                            )
                            SacramentText(
                                text = "This is a basic sheet container with content inside. Sheets can be used to group related content on an elevated surface.",
                                style = SacramentTheme.typography.bodyMedium,
                                color = SacramentTheme.colors.text.muted
                            )
                        }
                    }
                }

                // Modal bottom sheet
                CatalogSection("Modal Bottom Sheet") {
                    SacramentText(
                        text = "A modal bottom sheet slides up from the bottom of the screen.",
                        style = SacramentTheme.typography.bodyMedium,
                        color = SacramentTheme.colors.text.muted,
                        modifier = Modifier.padding(bottom = SacramentTheme.spacing.md)
                    )

                    SacramentButton(
                        text = "Show Bottom Sheet",
                        onClick = { showBottomSheet = true },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                // Usage guidelines
                CatalogSection("Usage") {
                    SacramentText(
                        text = "Sheets provide elevated surfaces for grouping content or displaying contextual information.",
                        style = SacramentTheme.typography.bodyMedium
                    )

                    SacramentText(
                        text = "Best practices:",
                        style = SacramentTheme.typography.labelMedium,
                        modifier = Modifier.padding(top = SacramentTheme.spacing.md)
                    )

                    Column(
                        modifier = Modifier.padding(start = SacramentTheme.spacing.md),
                        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xs)
                    ) {
                        SacramentText(
                            text = "• Use basic sheets for grouping related content",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Use bottom sheets for contextual actions",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Keep bottom sheet content focused",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentText(
                            text = "• Provide clear dismiss actions",
                            style = SacramentTheme.typography.bodySmall,
                            color = SacramentTheme.colors.text.muted
                        )
                    }
                }
            }

            // Bottom sheet instance
            if (showBottomSheet) {
                SacramentModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false }
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(SacramentTheme.spacing.xl),
                        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.lg)
                    ) {
                        SacramentText(
                            text = "Bottom Sheet Title",
                            style = SacramentTheme.typography.titleMedium
                        )
                        SacramentText(
                            text = "This is a modal bottom sheet that slides up from the bottom. It's perfect for displaying contextual actions or additional information without leaving the current screen.",
                            style = SacramentTheme.typography.bodyMedium,
                            color = SacramentTheme.colors.text.muted
                        )
                        SacramentButton(
                            text = "Action",
                            onClick = { showBottomSheet = false },
                            modifier = Modifier.fillMaxWidth()
                        )
                        SacramentButton(
                            text = "Close",
                            onClick = { showBottomSheet = false },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
