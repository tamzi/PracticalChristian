package com.sacrament.ui.foundation.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Padding presets (atom) built on the spacing scale.
 *
 * Usage:
 * `Modifier.padding(SacramentPaddingDefaults.screen())`
 */
object SacramentPaddingDefaults {
    @Composable
    fun screen(): PaddingValues = PaddingValues(
        horizontal = SacramentTheme.spacing.lg,
        vertical = SacramentTheme.spacing.lg,
    )

    @Composable
    fun card(): PaddingValues = PaddingValues(
        horizontal = SacramentTheme.spacing.lg,
        vertical = SacramentTheme.spacing.md,
    )

    @Composable
    fun listItem(): PaddingValues = PaddingValues(
        horizontal = SacramentTheme.spacing.lg,
        vertical = SacramentTheme.spacing.sm,
    )
}
