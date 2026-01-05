package com.sacrament.ui.components.feedback

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Inline message tone variants.
 */
enum class SacramentInlineMessageTone {
    Neutral,
    Success,
    Warning,
    Error,
    Info,
}

/**
 * Inline message component for contextual feedback.
 */
@Composable
fun SacramentInlineMessage(
    message: String,
    tone: SacramentInlineMessageTone = SacramentInlineMessageTone.Neutral,
    icon: ImageVector? = null,
    modifier: Modifier = Modifier,
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    val (background, content) = when (tone) {
        SacramentInlineMessageTone.Neutral -> colors.surfaces.surfaceVariant to colors.text.strong
        SacramentInlineMessageTone.Success -> colors.semantic.success.copy(alpha = 0.12f) to colors.semantic.success
        SacramentInlineMessageTone.Warning -> colors.semantic.warning.copy(alpha = 0.12f) to colors.semantic.warning
        SacramentInlineMessageTone.Error -> colors.semantic.error.copy(alpha = 0.12f) to colors.semantic.error
        SacramentInlineMessageTone.Info -> colors.semantic.info.copy(alpha = 0.12f) to colors.semantic.info
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(background)
            .padding(horizontal = spacing.lg, vertical = spacing.sm),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        if (icon != null) {
            SacramentIcon(
                imageVector = icon,
                contentDescription = null,
                tint = content,
                size = SacramentTheme.iconSizes.sm,
            )
        }
        SacramentText(
            text = message,
            style = SacramentTheme.typography.bodyMedium,
            color = content,
        )
    }
}

@Preview
@Composable
private fun SacramentInlineMessagePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentInlineMessage(message = "You're offline.")
    }
}
