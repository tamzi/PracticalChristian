package com.sacrament.ui.patterns

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.sacrament.ui.preview.SampleIcons
import com.sacrament.ui.preview.SampleText
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentSurface
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.testing.TestTags
import com.sacrament.ui.testing.testTag

/**
 * Empty/info state molecule with icon, title, and optional description/action.
 *
 * Follows design system parameter order: required content → callbacks → appearance → modifier.
 *
 * Usage:
 * `SacramentEmptyState(icon = Icons.Rounded.Info, title = "No items", contentDescription = "...", description = "Try again later")`
 */
@Composable
fun SacramentEmptyState(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    action: (@Composable () -> Unit)? = null,
    contentDescription: String,
    description: String? = null,
) {
    val spacing = SacramentTheme.spacing
    SacramentCenteredColumn(
        modifier = modifier
            .testTag(TestTags.Pattern.EmptyState, contentDescription)
            .fillMaxSize()
            .padding(spacing.lg),
    ) {
        Image(
            modifier = Modifier
                .height(75.dp)
                .width(75.dp),
            painter = rememberVectorPainter(icon),
            contentDescription = contentDescription,
            colorFilter = ColorFilter.tint(SacramentTheme.colors.text.muted),
        )
        SacramentText(
            modifier = Modifier.padding(top = spacing.lg),
            text = title,
            style = SacramentTheme.typography.displaySmall,
        )
        if (description != null) {
            SacramentText(
                modifier = Modifier.padding(top = spacing.sm),
                text = description,
                style = SacramentTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center),
            )
        }
        AnimatedVisibility(modifier = Modifier.padding(top = spacing.lg), visible = action != null) {
            action?.invoke()
        }
    }
}

@Preview
@Composable
fun SacramentEmptyStatePreview() {
    PreviewTheme {
        SacramentSurface(color = Color.White, shape = RoundedCornerShape(0.dp)) {
            SacramentEmptyState(
                icon = SampleIcons.Info,
                title = SampleText.ShortTitle,
                contentDescription = "Empty state preview",
                description = SampleText.MediumBody,
            )
        }
    }
}
