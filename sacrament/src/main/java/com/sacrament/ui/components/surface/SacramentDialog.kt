package com.sacrament.ui.components.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Dialog surface with title, content, and actions.
 */
@Composable
fun SacramentDialog(
    onDismissRequest: () -> Unit,
    title: @Composable () -> Unit,
    text: @Composable () -> Unit,
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    colors: SacramentDialogColors = SacramentDialogDefaults.colors(),
    modifier: Modifier = Modifier,
) {
    val spacing = SacramentTheme.spacing
    val shape = SacramentDialogDefaults.shape()

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .clip(shape)
                .background(colors.container, shape)
                .padding(spacing.lg),
            verticalArrangement = Arrangement.spacedBy(spacing.md),
        ) {
            title()
            text()
            Row(
                horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            ) {
                if (dismissButton != null) {
                    dismissButton()
                }
                confirmButton()
            }
        }
    }
}

@Preview
@Composable
private fun SacramentDialogPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentDialog(
            onDismissRequest = {},
            title = { SacramentText(text = "Title", style = SacramentTheme.typography.titleSmall) },
            text = { SacramentText(text = "Dialog message") },
            confirmButton = { SacramentText(text = "OK") },
            dismissButton = { SacramentText(text = "Cancel") },
        )
    }
}
