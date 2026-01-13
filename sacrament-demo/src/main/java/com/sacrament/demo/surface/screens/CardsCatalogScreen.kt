package com.sacrament.demo.surface.screens

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
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Cards detail screen.
 *
 * Displays examples of SacramentCard component variations.
 *
 * Navigation path: Home → Surface Components → Cards (this screen)
 *
 * Cards are elevated containers that group related content and actions.
 * They provide a clear visual boundary and can contain diverse content types.
 *
 * @param onNavigateBack Callback to navigate back to Surface Components overview
 */
@Composable
fun CardsCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Cards",
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
            CatalogSection("Card Examples") {
                CatalogRow("Basic Card") {
                    SacramentCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        SacramentText(
                            text = "This is a basic card with simple text content.",
                            modifier = Modifier.padding(SacramentTheme.spacing.md)
                        )
                    }
                }
                
                CatalogRow("Card with Title and Body") {
                    SacramentCard(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(SacramentTheme.spacing.md),
                            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)
                        ) {
                            SacramentText(
                                text = "Card Title",
                                style = SacramentTheme.typography.titleMedium,
                                color = SacramentTheme.colors.text.strong
                            )
                            SacramentText(
                                text = "This card demonstrates structured content with a title and body text. Cards can contain any composable content.",
                                style = SacramentTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

/**
 * Row component for displaying card examples.
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
