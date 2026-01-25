package com.sacrament.ui.components.input

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentIcon

/**
 * Switch input control with optional icons.
 */
@Composable
fun SacramentSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    size: SacramentSwitchSize = SacramentSwitchSize.Medium,
    enabled: Boolean = true,
    checkedIcon: ImageVector? = null,
    uncheckedIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    modifier: Modifier = Modifier,
) {
    val colors = SacramentSwitchDefaults.colors()
    val width = SacramentSwitchDefaults.width(size)
    val height = SacramentSwitchDefaults.height(size)
    val thumbSize = SacramentSwitchDefaults.thumbSize(size)
    val padding = SacramentSwitchDefaults.trackPadding()
    val iconSize = SacramentSwitchDefaults.iconSize(size)

    val trackColor = when {
        !enabled -> colors.disabledTrack
        checked -> colors.checkedTrack
        else -> colors.uncheckedTrack
    }
    val thumbColor = when {
        !enabled -> colors.disabledThumb
        checked -> colors.checkedThumb
        else -> colors.uncheckedThumb
    }
    val iconColor = when {
        !enabled -> colors.disabledIcon
        checked -> colors.checkedIcon
        else -> colors.uncheckedIcon
    }
    val thumbOffset by animateDpAsState(
        targetValue = if (checked) width - thumbSize - padding else padding,
        label = "SacramentSwitchThumb",
    )

    Box(
        modifier = modifier
            .size(width = width, height = height)
            .clip(CircleShape)
            .background(trackColor)
            .toggleable(
                value = checked,
                enabled = enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                onValueChange = onCheckedChange,
            ),
        contentAlignment = Alignment.CenterStart,
    ) {
        // Icon on the opposite side of the thumb
        if (checked && checkedIcon != null) {
            Box(
                modifier = Modifier
                    .offset(x = padding)
                    .size(thumbSize),
                contentAlignment = Alignment.Center
            ) {
                SacramentIcon(
                    imageVector = checkedIcon,
                    contentDescription = "Checked",
                    tint = iconColor,
                    size = iconSize
                )
            }
        } else if (!checked && uncheckedIcon != null) {
            Box(
                modifier = Modifier
                    .offset(x = width - thumbSize - padding)
                    .size(thumbSize),
                contentAlignment = Alignment.Center
            ) {
                SacramentIcon(
                    imageVector = uncheckedIcon,
                    contentDescription = "Unchecked",
                    tint = iconColor,
                    size = iconSize
                )
            }
        }
        
        // Thumb
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(thumbSize)
                .clip(CircleShape)
                .background(thumbColor),
        )
    }
}

@Preview(name = "Light Theme - Checked")
@Composable
private fun SacramentSwitchLightCheckedPreview() {
    SacramentTheme(darkTheme = false, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSwitch(
            checked = true,
            onCheckedChange = {},
            checkedIcon = SacramentIcons.SacramentIconDarkMode,
            uncheckedIcon = SacramentIcons.SacramentIconLightMode
        )
    }
}

@Preview(name = "Light Theme - Unchecked")
@Composable
private fun SacramentSwitchLightUncheckedPreview() {
    SacramentTheme(darkTheme = false, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSwitch(
            checked = false,
            onCheckedChange = {},
            checkedIcon = SacramentIcons.SacramentIconDarkMode,
            uncheckedIcon = SacramentIcons.SacramentIconLightMode
        )
    }
}

@Preview(name = "Dark Theme - Checked")
@Composable
private fun SacramentSwitchDarkCheckedPreview() {
    SacramentTheme(darkTheme = true, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSwitch(
            checked = true,
            onCheckedChange = {},
            checkedIcon = SacramentIcons.SacramentIconDarkMode,
            uncheckedIcon = SacramentIcons.SacramentIconLightMode
        )
    }
}

@Preview(name = "Dark Theme - Unchecked")
@Composable
private fun SacramentSwitchDarkUncheckedPreview() {
    SacramentTheme(darkTheme = true, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSwitch(
            checked = false,
            onCheckedChange = {},
            checkedIcon = SacramentIcons.SacramentIconDarkMode,
            uncheckedIcon = SacramentIcons.SacramentIconLightMode
        )
    }
}
