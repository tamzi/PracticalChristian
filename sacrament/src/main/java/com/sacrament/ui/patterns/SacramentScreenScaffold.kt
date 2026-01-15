package com.sacrament.ui.patterns

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.components.action.SacramentFab
import com.sacrament.ui.components.navigation.SacramentBottomBar
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.preview.PreviewTheme
import com.sacrament.ui.preview.SampleText
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.testing.TestTags
import com.sacrament.ui.testing.testTag

/**
 * Screen scaffold pattern built with Compose foundation (Material-free).
 *
 * Provides consistent structure for screens with optional top bar, bottom bar, and FAB.
 * Automatically handles window insets and applies design system colors.
 *
 * The FAB is positioned at the bottom-end corner with 16.dp padding, and is automatically
 * offset above the bottom bar when both are present, preventing overlap with navigation items.
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
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit,
) {
    val colors = SacramentTheme.colors
    val density = LocalDensity.current

    // Track bottom bar height to offset FAB
    var bottomBarHeightPx by remember { mutableIntStateOf(0) }

    Box(
        modifier = modifier
            .testTag(TestTags.Pattern.ScreenScaffold, "Screen scaffold")
            .fillMaxSize()
            .background(colors.surfaces.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
        ) {
            // Top bar
            topBar()

            // Content area - fills remaining space
            Box(
                modifier = Modifier
                    .weight(1f)
                    .imePadding()
            ) {
                // The Column layout manages top/bottom bars, so no padding offset is needed.
                // Content receives zero padding since bars don't overlay the content area.
                content(PaddingValues(top = 0.dp, bottom = 0.dp))
            }

            // Bottom bar - measure its height to offset FAB
            Box(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .onSizeChanged { 
                        bottomBarHeightPx = it.height
                    }
            ) {
                bottomBar()
            }
        }

        // FAB positioned absolutely, offset above bottom bar when present
        // Uses standard FAB positioning: 16.dp from edges + bottom bar height
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 16.dp,
                    bottom = 16.dp + with(density) {
                        bottomBarHeightPx.toDp()
                    }
                )
        ) {
            floatingActionButton()
        }
    }
}

@Preview(name = "With Top Bar")
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
                Column(
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

@Preview(name = "With Bottom Bar and FAB")
@Composable
fun SacramentScreenScaffoldWithBottomBarPreview() {
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
            bottomBar = {
                SacramentBottomBar {
                    SacramentText(text = "Home", style = SacramentTheme.typography.labelSmall)
                    SacramentText(text = "Notes", style = SacramentTheme.typography.labelSmall)
                    SacramentText(text = "Profile", style = SacramentTheme.typography.labelSmall)
                }
            },
            floatingActionButton = {
                SacramentFab(
                    imageVector = SacramentIcons.Add,
                    contentDescription = "Add",
                    onClick = {},
                )
            },
            content = { paddingValues ->
                Column(
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

