package com.sacrament.ui.components.action

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.color.SacramentColorTokens

/**
 * Visual variants for buttons.
 */
enum class SacramentButtonVariant {
    Filled,
    Outlined,
    Ghost,
}

/**
 * Semantic tones for action components.
 */
enum class SacramentButtonTone {
    Brand,
    Neutral,
    Success,
    Warning,
    Error,
    Info,
}

internal object SacramentButtonTokens {
    const val FilledPressedAlpha = 0.9f
    const val GhostPressedAlpha = 0.12f
    const val DisabledBorderAlpha = 0.4f

    @Composable
    fun toneColor(
        tone: SacramentButtonTone,
        colors: SacramentColorTokens = SacramentTheme.colors,
    ): Color = when (tone) {
        SacramentButtonTone.Brand -> colors.brand.primary
        SacramentButtonTone.Neutral -> colors.text.strong
        SacramentButtonTone.Success -> colors.semantic.success
        SacramentButtonTone.Warning -> colors.semantic.warning
        SacramentButtonTone.Error -> colors.semantic.error
        SacramentButtonTone.Info -> colors.semantic.info
    }

    @Composable
    fun onToneColor(colors: SacramentColorTokens = SacramentTheme.colors): Color = colors.text.onBrand
}
