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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.components.navigation.SacramentTopAppBarDefaults
import com.sacrament.ui.foundation.SacramentTheme
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
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    val colors = SacramentTheme.colors
    
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
                // Provide padding values for content compatibility
                // Since bars are in Column layout, they already take space
                // Padding is mainly for system insets and FAB spacing
                content(
                    PaddingValues(
                        // No top/bottom padding needed as bars are in layout
                        // But provide for any edge cases or future compatibility
                        top = 0.dp,
                        bottom = 0.dp
                    )
                )
            }
            
            // Bottom bar
            bottomBar()
        }
        
        // Floating action button (positioned absolutely, above bottom bar)
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
                .windowInsetsPadding(WindowInsets.navigationBars)
        ) {
            floatingActionButton()
        }
    }
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

