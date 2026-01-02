package com.sacrament.ui.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Snackbar component for transient feedback.
 */
@Composable
fun SacramentSnackbar(
    message: String,
    modifier: Modifier = Modifier,
    tone: SacramentSnackbarTone = SacramentSnackbarTone.Neutral,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
) {
    val spacing = SacramentTheme.spacing
    val colors = SacramentSnackbarDefaults.colors(tone)
    val shape = SacramentSnackbarDefaults.shape()

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.container, shape)
            .padding(horizontal = spacing.lg, vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        SacramentText(
            text = message,
            style = SacramentTheme.typography.bodyMedium,
            color = colors.content,
        )
        if (actionLabel != null && onAction != null) {
            SacramentText(
                text = actionLabel,
                style = SacramentTheme.typography.labelMedium,
                color = colors.content,
                modifier = Modifier.clickable(onClick = onAction),
            )
        }
    }
}

@Preview
@Composable
private fun SacramentSnackbarPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSnackbar(message = "Saved", actionLabel = "Undo", onAction = {})
    }
}
