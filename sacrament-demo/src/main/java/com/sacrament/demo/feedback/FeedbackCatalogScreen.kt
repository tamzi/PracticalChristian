package com.sacrament.demo.feedback

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
import com.sacrament.ui.components.feedback.SacramentInlineMessage
import com.sacrament.ui.components.feedback.SacramentInlineMessageTone
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

/**
 * Feedback Components catalog screen.
 *
 * Displays examples of feedback components that communicate system status and messages
 * to users, including progress indicators, inline messages, and snackbars.
 *
 * Navigation path: Home → Feedback Components (this screen)
 *
 * Components demonstrated:
 * - **Progress Indicators**: Linear and Circular variants at 60% progress
 * - **Inline Messages**: Neutral, Success, Warning, Error, Info tones
 * - **Snackbars**: Interactive demo not shown (requires state management)
 *
 * @param onNavigateBack Callback to navigate back to the catalog home screen
 */
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

/**
 * Row component for displaying feedback component examples.
 *
 * Used exclusively in FeedbackCatalogScreen to present labeled examples of
 * feedback components (progress indicators, inline messages, snackbars).
 *
 * @param label Descriptive label for the example (e.g., "Linear", "Success")
 * @param content The composable content to display below the label
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

