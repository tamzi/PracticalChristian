package com.sacrament.ui.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.color.SacramentColorTokens
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Tag tone variants for semantic intent.
 */
enum class SacramentTagTone {
    Neutral,
    Brand,
    Success,
    Warning,
    Error,
    Info,
}

/**
 * Tag component for labeled metadata.
 */
@Composable
fun SacramentTag(
    text: String,
    modifier: Modifier = Modifier,
    tone: SacramentTagTone = SacramentTagTone.Neutral,
    leadingIcon: ImageVector? = null,
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    val shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)
    val (borderColor, contentColor, background) = tagColorsForTone(tone, colors)

    Row(
        modifier = modifier
            .background(background, shape)
            .border(width = 1.dp, color = borderColor, shape = shape)
            .padding(horizontal = spacing.md, vertical = spacing.xs),
        horizontalArrangement = Arrangement.spacedBy(spacing.xs),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (leadingIcon != null) {
            SacramentIcon(
                imageVector = leadingIcon,
                contentDescription = null,
                tint = contentColor,
                size = SacramentTheme.iconSizes.sm,
            )
        }
        SacramentText(
            text = text,
            style = SacramentTheme.typography.labelMedium,
            color = contentColor,
        )
    }
}

@Composable
private fun tagColorsForTone(
    tone: SacramentTagTone,
    colors: SacramentColorTokens,
): Triple<Color, Color, Color> = when (tone) {
    SacramentTagTone.Neutral -> Triple(colors.text.muted, colors.text.muted, Color.Transparent)
    SacramentTagTone.Brand -> Triple(colors.brand.primary, colors.brand.primary, colors.brand.primary.copy(alpha = 0.08f))
    SacramentTagTone.Success -> Triple(colors.semantic.success, colors.semantic.success, colors.semantic.success.copy(alpha = 0.08f))
    SacramentTagTone.Warning -> Triple(colors.semantic.warning, colors.semantic.warning, colors.semantic.warning.copy(alpha = 0.08f))
    SacramentTagTone.Error -> Triple(colors.semantic.error, colors.semantic.error, colors.semantic.error.copy(alpha = 0.08f))
    SacramentTagTone.Info -> Triple(colors.semantic.info, colors.semantic.info, colors.semantic.info.copy(alpha = 0.08f))
}
