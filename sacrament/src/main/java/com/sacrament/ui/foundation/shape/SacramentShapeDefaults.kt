package com.sacrament.ui.foundation.shape

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Named radius sizes mapped to the theme radii scale.
 */
enum class SacramentRadiusSize {
    NONE,
    XS,
    SM,
    MD,
    LG,
    XL,
}

/**
 * Corner radius scale mapping (atom).
 *
 * Usage:
 * `shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG)`
 */
object SacramentShapeDefaults {
    @Composable
    fun rounded(size: SacramentRadiusSize): Shape {
        val radii = SacramentTheme.radii
        val radius = when (size) {
            SacramentRadiusSize.NONE -> radii.none
            SacramentRadiusSize.XS -> radii.xs
            SacramentRadiusSize.SM -> radii.sm
            SacramentRadiusSize.MD -> radii.md
            SacramentRadiusSize.LG -> radii.lg
            SacramentRadiusSize.XL -> radii.xl
        }
        return RoundedCornerShape(radius)
    }
}
