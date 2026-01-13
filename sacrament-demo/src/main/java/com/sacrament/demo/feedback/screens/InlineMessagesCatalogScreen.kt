package com.sacrament.demo.feedback.screens

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
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentText

@Composable
fun InlineMessagesCatalogScreen(onNavigateBack: () -> Unit) {
    SacramentScreenScaffold(
        topBar = { CatalogTopAppBar(title = "Inline Messages", onNavigateBack = onNavigateBack) }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = SacramentTheme.spacing.xl, vertical = SacramentTheme.spacing.xxl),
            verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.xxl)
        ) {
            CatalogSection("Inline Message Tones") {
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
        }
    }
}

@Composable
private fun CatalogRow(label: String, content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.sm)) {
        SacramentText(text = label, style = SacramentTheme.typography.bodyMedium, color = SacramentTheme.colors.text.muted)
        content()
    }
}
