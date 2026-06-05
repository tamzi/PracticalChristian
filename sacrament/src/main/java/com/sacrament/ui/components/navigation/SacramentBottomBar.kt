package com.sacrament.ui.components.navigation

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

private const val SPOTLIGHT_ANIMATION_DURATION_MILLIS = 300

/**
 * Bottom navigation bar container with animated selected-item spotlight support.
 *
 * @param selectedIndex Index of the selected item in [content].
 * @param itemCount Number of items rendered by [content], used to position the indicator.
 */
@Composable
fun SacramentBottomBar(
    modifier: Modifier = Modifier,
    selectedIndex: Int = 0,
    itemCount: Int = SacramentBottomBarDefaults.itemCount(),
    colors: SacramentBottomBarColors = SacramentBottomBarDefaults.colors(),
    content: @Composable RowScope.() -> Unit,
) {
    val spacing = SacramentTheme.spacing
    val radii = SacramentTheme.radii
    val resolvedItemCount = itemCount.coerceAtLeast(1)
    val boundedSelectedIndex = selectedIndex.coerceIn(0, resolvedItemCount - 1)
    val showSpotlightGlow = colors.spotlightGlow.alpha > 0f
    val containerShape = RoundedCornerShape(
        topStart = radii.xl,
        topEnd = radii.xl,
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(SacramentBottomBarDefaults.height())
            .navigationBarsPadding()
            .clip(containerShape)
            .background(colors.container),
    ) {
        val horizontalPadding = spacing.lg
        val itemWidth = (maxWidth - horizontalPadding * 2) / resolvedItemCount
        val indicatorOffset by animateDpAsState(
            targetValue = horizontalPadding + itemWidth * boundedSelectedIndex,
            animationSpec = tween(
                durationMillis = SPOTLIGHT_ANIMATION_DURATION_MILLIS,
                easing = FastOutSlowInEasing,
            ),
            label = "SacramentBottomBarIndicator",
        )

        if (showSpotlightGlow) {
            SpotlightGlow(
                color = colors.spotlightGlow,
                width = itemWidth,
                offset = indicatorOffset,
                modifier = Modifier.align(Alignment.TopStart),
            )
        }

        IndicatorPill(
            color = colors.selectedIndicator,
            width = itemWidth,
            offset = indicatorOffset,
            modifier = Modifier.align(Alignment.CenterStart),
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(horizontal = horizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            content = content,
        )
    }
}

@Composable
private fun IndicatorPill(
    color: androidx.compose.ui.graphics.Color,
    width: Dp,
    offset: Dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .offset(x = offset)
            .width(width)
            .height(SacramentBottomBarDefaults.indicatorHeight())
            .padding(horizontal = SacramentTheme.spacing.xs)
            .clip(RoundedCornerShape(SacramentTheme.radii.xl))
            .background(color),
    )
}

@Composable
private fun SpotlightGlow(
    color: androidx.compose.ui.graphics.Color,
    width: Dp,
    offset: Dp,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .offset(x = offset)
            .width(width)
            .height(SacramentBottomBarDefaults.spotlightHeight())
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        color,
                        color.copy(alpha = 0f),
                    ),
                ),
            ),
    )
}

@Preview(name = "Light Theme")
@Composable
private fun SacramentBottomBarLightPreview() {
    SacramentTheme(darkTheme = false, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentBottomBar(selectedIndex = 1) {
            SacramentText(text = "Home", style = SacramentTheme.typography.labelSmall)
            SacramentText(text = "Books", style = SacramentTheme.typography.labelSmall)
            SacramentText(text = "Notes", style = SacramentTheme.typography.labelSmall)
        }
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun SacramentBottomBarDarkPreview() {
    SacramentTheme(darkTheme = true, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentBottomBar(selectedIndex = 1) {
            SacramentText(text = "Home", style = SacramentTheme.typography.labelSmall)
            SacramentText(text = "Books", style = SacramentTheme.typography.labelSmall)
            SacramentText(text = "Notes", style = SacramentTheme.typography.labelSmall)
        }
    }
}
