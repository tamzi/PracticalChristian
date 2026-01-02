package com.sacrament.ui.components.feedback

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Data for toast messages.
 */
data class SacramentToastMessage(
    val id: String,
    val message: String,
    val tone: SacramentSnackbarTone = SacramentSnackbarTone.Neutral,
)

/**
 * Toast host that stacks snackbars.
 */
@Composable
fun SacramentToastHost(
    toasts: List<SacramentToastMessage>,
    modifier: Modifier = Modifier,
    onDismiss: ((String) -> Unit)? = null,
) {
    val spacing = SacramentTheme.spacing
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        toasts.forEach { toast ->
            SacramentSnackbar(
                message = toast.message,
                tone = toast.tone,
                actionLabel = if (onDismiss != null) "Dismiss" else null,
                onAction = if (onDismiss != null) ({ onDismiss(toast.id) }) else null,
            )
        }
    }
}

@Preview
@Composable
private fun SacramentToastHostPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentToastHost(
            toasts = listOf(
                SacramentToastMessage(id = "1", message = "Saved"),
                SacramentToastMessage(id = "2", message = "Offline", tone = SacramentSnackbarTone.Warning),
            ),
        )
    }
}
