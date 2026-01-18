package com.sacrament.ui.preview

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Preview theme wrapper for design system component previews.
 *
 * Provides consistent theme setup with white background for @Preview composables.
 *
 * Usage:
 * ```
 * @Preview
 * @Composable
 * fun MyComponentPreview() {
 *     PreviewTheme {
 *         MyComponent()
 *     }
 * }
 * ```
 */
@Composable
fun PreviewTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {
    SacramentTheme(
        darkTheme = darkTheme,
        navigationBar = Bar.SURFACE,
        statusBar = Bar.BACKGROUND,
    ) {
        Box(
            modifier = Modifier.background(Color.White)
        ) {
            content()
        }
    }
}

/**
 * Preview parameter provider for light/dark theme variants.
 */
object PreviewThemeParameterProvider :
    androidx.compose.ui.tooling.preview.PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true) // light, dark
}

/**
 * Preview annotation for light theme.
 */
@Preview(
    name = "Light Theme",
    group = "Theme",
    showBackground = true,
)
annotation class LightPreview

/**
 * Preview annotation for dark theme.
 */
@Preview(
    name = "Dark Theme",
    group = "Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
annotation class DarkPreview

