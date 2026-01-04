package com.sacrament.ui.patterns

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.preview.PreviewTheme
import com.sacrament.ui.preview.SampleText
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.testing.TestTags
import com.sacrament.ui.testing.testTag

/**
 * Screen scaffold pattern that wraps Material3 Scaffold with design system defaults.
 *
 * Provides consistent structure for screens with optional top bar, bottom bar, and FAB.
 * Automatically handles window insets and applies design system colors.
 *
 * Follows design system parameter order: required content → callbacks → modifier.
 *
 * Usage:
 * ```
 * SacramentScreenScaffold(
 *     topBar = { SacramentTopAppBar(...) },
 *     bottomBar = { BottomNavigationBar(...) },
 *     floatingActionButton = { SacramentFab(...) }
 * ) { paddingValues ->
 *     // Screen content
 * }
 * ```
 */
@Composable
fun SacramentScreenScaffold(
    content: @Composable (PaddingValues) -> Unit,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
) {
    val colors = SacramentTheme.colors
    Scaffold(
        modifier = modifier
            .testTag(TestTags.Pattern.ScreenScaffold, "Screen scaffold")
            .fillMaxSize(),
        topBar = topBar,
        bottomBar = bottomBar,
        floatingActionButton = floatingActionButton,
        containerColor = colors.surfaces.background,
        contentWindowInsets = WindowInsets.statusBars,
    ) { paddingValues -> content(paddingValues) }
}

@Preview
@Composable
fun SacramentScreenScaffoldPreview() {
    PreviewTheme {
        SacramentScreenScaffold(
            topBar = {
                SacramentTopAppBar(
                    title = {
                        SacramentText(
                            text = SampleText.ShortTitle,
                            style = SacramentTheme.typography.titleSmall,
                        )
                    },
                )
            },
            content = { paddingValues ->
            androidx.compose.foundation.layout.Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                SacramentText(
                    text = SampleText.MediumBody,
                    style = SacramentTheme.typography.bodyMedium,
                )
            }
        },
        )
    }
}

