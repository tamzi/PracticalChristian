package com.sacrament.ui.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.color.SacramentColorTokens
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults
import com.sacrament.ui.primitives.SacramentText

/**
 * Badge tone variants for semantic intent.
 */
enum class SacramentBadgeTone {
    Neutral,
    Brand,
    Success,
    Warning,
    Error,
    Info,
}

/**
 * Badge atom for short status labels.
 *
 * Usage:
 * `SacramentBadge(text = "New", tone = SacramentBadgeTone.Brand)`
 */
@Composable
fun SacramentBadge(
    text: String,
    tone: SacramentBadgeTone = SacramentBadgeTone.Neutral,
    modifier: Modifier = Modifier,
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    val shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)
    val (background, content) = badgeColorsForTone(tone, colors)

    Box(
        modifier = modifier
            .background(background, shape)
            .padding(horizontal = spacing.md, vertical = spacing.xs),
    ) {
        SacramentText(
            text = text,
            style = SacramentTheme.typography.labelMedium,
            color = content,
        )
    }
}

@Composable
private fun badgeColorsForTone(
    tone: SacramentBadgeTone,
    colors: SacramentColorTokens,
): Pair<Color, Color> = when (tone) {
    SacramentBadgeTone.Neutral -> colors.surfaces.surfaceVariant to colors.text.muted
    SacramentBadgeTone.Brand -> colors.brand.primary.copy(alpha = 0.16f) to colors.brand.primary
    SacramentBadgeTone.Success -> colors.semantic.success.copy(alpha = 0.16f) to colors.semantic.success
    SacramentBadgeTone.Warning -> colors.semantic.warning.copy(alpha = 0.16f) to colors.semantic.warning
    SacramentBadgeTone.Error -> colors.semantic.error.copy(alpha = 0.16f) to colors.semantic.error
    SacramentBadgeTone.Info -> colors.semantic.info.copy(alpha = 0.16f) to colors.semantic.info
}
