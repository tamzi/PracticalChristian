package com.sacrament.ui.components.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Chip molecule for short, selectable labels.
 *
 * Usage:
 * `SacramentChip(label = "Morning", selected = true, onClick = { ... })`
 */
@Composable
fun SacramentChip(
    label: String,
    onClick: (() -> Unit)? = null,
    selected: Boolean = false,
    leadingIcon: ImageVector? = null,
    modifier: Modifier = Modifier,
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    val background = if (selected) {
        colors.brand.primary
    } else {
        colors.surfaces.surfaceVariant
    }
    val contentColor = if (selected) {
        colors.text.onBrand
    } else {
        colors.text.muted
    }
    val shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)
    val rowModifier = if (onClick != null) {
        modifier
            .clip(shape)
            .background(background)
            .clickable(onClick = onClick)
            .padding(horizontal = spacing.md, vertical = spacing.xs)
    } else {
        modifier
            .clip(shape)
            .background(background)
            .padding(horizontal = spacing.md, vertical = spacing.xs)
    }

    Row(
        modifier = rowModifier,
        horizontalArrangement = Arrangement.spacedBy(spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) {
            Image(
                painter = rememberVectorPainter(leadingIcon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(contentColor),
            )
        }
        SacramentText(
            text = label,
            style = SacramentTheme.typography.labelLarge,
            color = contentColor,
        )
    }
}
