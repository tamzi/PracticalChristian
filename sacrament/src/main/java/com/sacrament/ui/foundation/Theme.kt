package com.sacrament.ui.foundation

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.sacrament.ui.foundation.color.DarkSacramentColors
import com.sacrament.ui.foundation.color.SacramentColorTokens
import com.sacrament.ui.foundation.color.LightSacramentColors
import com.sacrament.ui.foundation.elevation.SacramentElevation
import com.sacrament.ui.foundation.icon.SacramentIconSizes
import com.sacrament.ui.foundation.layout.SacramentSpacing
import com.sacrament.ui.foundation.motion.SacramentMotion
import com.sacrament.ui.foundation.shape.SacramentRadii
import com.sacrament.ui.foundation.typography.SacramentTypography
import com.sacrament.ui.foundation.typography.DefaultSacramentTypography

enum class Bar {
    SURFACE,
    BACKGROUND,
}

private fun Bar.resolveColor(colors: SacramentColorTokens): Color = when (this) {
    Bar.SURFACE -> colors.surfaces.surface
    Bar.BACKGROUND -> colors.surfaces.background
}

val LocalColors = staticCompositionLocalOf { LightSacramentColors }
val LocalSpacing = staticCompositionLocalOf { SacramentSpacing() }
val LocalRadii = staticCompositionLocalOf { SacramentRadii() }
val LocalElevation = staticCompositionLocalOf { SacramentElevation() }
val LocalIconSizes = staticCompositionLocalOf { SacramentIconSizes() }
val LocalTypography = staticCompositionLocalOf { DefaultSacramentTypography }
val LocalMotion = staticCompositionLocalOf { SacramentMotion() }

/**
 * Design system tokens provided by SacramentTheme.
 */
object SacramentTheme {
    val colors: SacramentColorTokens
        @Composable get() = LocalColors.current

    val spacing: SacramentSpacing
        @Composable get() = LocalSpacing.current

    val radii: SacramentRadii
        @Composable get() = LocalRadii.current

    val elevation: SacramentElevation
        @Composable get() = LocalElevation.current

    val iconSizes: SacramentIconSizes
        @Composable get() = LocalIconSizes.current

    val typography: SacramentTypography
        @Composable get() = LocalTypography.current

    val motion: SacramentMotion
        @Composable get() = LocalMotion.current
}

/**
 * Sacrament theme wrapper. Always use this at the app root to supply tokens.
 *
 * Usage:
 * `SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) { ... }`
 */
@Composable
fun SacramentTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    navigationBar: Bar,
    statusBar: Bar,
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) DarkSacramentColors else LightSacramentColors
    val spacing = SacramentSpacing()
    val radii = SacramentRadii()
    val elevation = SacramentElevation()
    val iconSizes = SacramentIconSizes()
    val typography = DefaultSacramentTypography
    val motion = SacramentMotion()
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            @Suppress("DEPRECATION")
            window.statusBarColor = statusBar.resolveColor(colors).toArgb()
            @Suppress("DEPRECATION")
            window.navigationBarColor = navigationBar.resolveColor(colors).toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    CompositionLocalProvider(
        LocalColors provides colors,
        LocalSpacing provides spacing,
        LocalRadii provides radii,
        LocalElevation provides elevation,
        LocalIconSizes provides iconSizes,
        LocalTypography provides typography,
        LocalMotion provides motion,
        content = content,
    )
}
