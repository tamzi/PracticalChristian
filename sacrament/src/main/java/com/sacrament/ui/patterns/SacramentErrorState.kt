package com.sacrament.ui.patterns

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.preview.PreviewTheme
import com.sacrament.ui.preview.SampleErrors
import com.sacrament.ui.preview.SampleText
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.testing.TestTags
import com.sacrament.ui.testing.testTag

/**
 * Error state molecule with icon, title, optional message, and optional retry action.
 *
 * Follows design system parameter order: required content → callbacks → appearance → modifier.
 *
 * Usage:
 * `SacramentErrorState(title = "Error", message = "Something went wrong", contentDescription = "...") { RetryButton() }`
 */
@Composable
fun SacramentErrorState(
    modifier: Modifier = Modifier,
    title: String,
    action: (@Composable () -> Unit)? = null,
    contentDescription: String,
    message: String? = null,
    icon: ImageVector = Icons.Rounded.Warning,
) {
    val spacing = SacramentTheme.spacing
    val colors = SacramentTheme.colors

    SacramentCenteredColumn(
        modifier = modifier
            .testTag(TestTags.Pattern.ErrorState, contentDescription)
            .fillMaxSize()
            .background(Color.White)
            .padding(spacing.lg),
    ) {
        Image(
            modifier = Modifier
                .height(75.dp)
                .width(75.dp),
            painter = rememberVectorPainter(icon),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(colors.semantic.error),
        )
        SacramentText(
            modifier = Modifier.padding(top = spacing.lg),
            text = title,
            style = SacramentTheme.typography.displaySmall,
            color = colors.semantic.error,
        )
        if (message != null) {
            SacramentText(
                modifier = Modifier.padding(top = spacing.sm),
                text = message,
                style = SacramentTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
                color = colors.text.muted,
            )
        }
        AnimatedVisibility(
            modifier = Modifier.padding(top = spacing.lg),
            visible = action != null,
        ) {
            action?.invoke()
        }
    }
}

@Preview
@Composable
fun SacramentErrorStatePreview() {
    PreviewTheme {
        SacramentErrorState(
            title = SampleText.ShortTitle,
            contentDescription = "Error state preview",
            message = SampleErrors.Network,
        )
    }
}
