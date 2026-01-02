package com.sacrament.ui.components.feedback

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults

/**
 * Snackbar tone variants.
 */
enum class SacramentSnackbarTone {
    Neutral,
    Success,
    Warning,
    Error,
    Info,
}

/**
 * Colors for snackbars.
 */
@Immutable
data class SacramentSnackbarColors(
    val container: Color,
    val content: Color,
)

/**
 * Defaults for Sacrament snackbars.
 */
object SacramentSnackbarDefaults {
    @Composable
    fun colors(tone: SacramentSnackbarTone): SacramentSnackbarColors {
        val colors = SacramentTheme.colors
        return when (tone) {
            SacramentSnackbarTone.Neutral -> SacramentSnackbarColors(
                container = colors.text.strong,
                content = colors.text.inverse,
            )
            SacramentSnackbarTone.Success -> SacramentSnackbarColors(
                container = colors.semantic.success,
                content = colors.text.onBrand,
            )
            SacramentSnackbarTone.Warning -> SacramentSnackbarColors(
                container = colors.semantic.warning,
                content = colors.text.onBrand,
            )
            SacramentSnackbarTone.Error -> SacramentSnackbarColors(
                container = colors.semantic.error,
                content = colors.text.onBrand,
            )
            SacramentSnackbarTone.Info -> SacramentSnackbarColors(
                container = colors.semantic.info,
                content = colors.text.onBrand,
            )
        }
    }

    @Composable
    fun shape(): Shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)
}
