package com.sacrament.demo.catalog.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.ui.components.feedback.SacramentInlineMessage
import com.sacrament.ui.components.feedback.SacramentInlineMessageTone
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.SacramentTheme

@Composable
fun FeedbackCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = {
            CatalogTopAppBar(
                title = "Feedback Components",
                onNavigateBack = onNavigateBack
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(SacramentTheme.spacing.lg),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xl)
        ) {
            // Progress Indicators
            CatalogSection("Progress Indicators") {
                CatalogRow("Linear") {
                    SacramentProgressIndicator(
                        variant = SacramentProgressVariant.Linear,
                        progress = 0.6f,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                CatalogRow("Circular") {
                    SacramentProgressIndicator(
                        variant = SacramentProgressVariant.Circular,
                        progress = 0.6f
                    )
                }
            }
            
            // Inline Messages
            CatalogSection("Inline Messages - Tones") {
                CatalogRow("Neutral") {
                    SacramentInlineMessage(
                        message = "This is a neutral message",
                        tone = SacramentInlineMessageTone.Neutral,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                CatalogRow("Success") {
                    SacramentInlineMessage(
                        message = "Operation completed successfully",
                        tone = SacramentInlineMessageTone.Success,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                CatalogRow("Warning") {
                    SacramentInlineMessage(
                        message = "Please review this information",
                        tone = SacramentInlineMessageTone.Warning,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                CatalogRow("Error") {
                    SacramentInlineMessage(
                        message = "An error occurred",
                        tone = SacramentInlineMessageTone.Error,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                CatalogRow("Info") {
                    SacramentInlineMessage(
                        message = "Here's some helpful information",
                        tone = SacramentInlineMessageTone.Info,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
            
            CatalogSection("Snackbars") {
                SacramentText(
                    text = "Snackbar component (interactive demo not shown).",
                    style = SacramentTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun CatalogSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.md)
    ) {
        SacramentText(
            text = title,
            style = SacramentTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = SacramentTheme.spacing.xs)
        )
        content()
    }
}

@Composable
private fun CatalogRow(
    label: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xs)
    ) {
        SacramentText(
            text = label,
            style = SacramentTheme.typography.labelMedium,
            modifier = Modifier.padding(start = SacramentTheme.spacing.xs)
        )
        content()
    }
}

