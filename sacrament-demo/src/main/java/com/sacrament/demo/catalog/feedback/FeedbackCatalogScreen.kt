package com.sacrament.demo.catalog.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sacrament.demo.catalog.CatalogRow
import com.sacrament.demo.catalog.CatalogSection
import com.sacrament.demo.catalog.CatalogTopAppBar
import com.sacrament.ui.components.feedback.SacramentInlineMessage
import com.sacrament.ui.components.feedback.SacramentInlineMessageTone
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
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

