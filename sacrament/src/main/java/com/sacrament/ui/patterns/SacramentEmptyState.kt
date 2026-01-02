package com.sacrament.ui.patterns

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentText

/**
 * Empty/info state molecule with icon, title, and optional description/action.
 *
 * Usage:
 * `SacramentEmptyState(icon = Icons.Rounded.Info, title = "No items", description = "Try again later", contentDescription = "...")`
 */
@Composable
fun SacramentEmptyState(
    icon: ImageVector,
    title: String,
    description: String?,
    contentDescription: String,
    modifier: Modifier = Modifier,
    action: (@Composable () -> Unit)? = null,
) {
    val spacing = SacramentTheme.spacing
    SacramentCenteredColumn(
        modifier = modifier
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
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentEmptyState(
            icon = Icons.Rounded.Warning,
            title = "Kumethoka",
            description = "Sijui Kumeenda aje",
            contentDescription = "an icon to show end of the world"
        )
    }
}
