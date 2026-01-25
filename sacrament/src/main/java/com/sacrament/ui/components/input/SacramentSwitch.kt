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
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Switch input control.
 */
@Composable
fun SacramentSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    size: SacramentSwitchSize = SacramentSwitchSize.Medium,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    modifier: Modifier = Modifier,
) {
    val colors = SacramentSwitchDefaults.colors()
    val width = SacramentSwitchDefaults.width(size)
    val height = SacramentSwitchDefaults.height(size)
    val thumbSize = SacramentSwitchDefaults.thumbSize(size)
    val padding = SacramentSwitchDefaults.trackPadding()

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
        SacramentSwitch(checked = true, onCheckedChange = {})
    }
}

@Preview(name = "Light Theme - Unchecked")
@Composable
private fun SacramentSwitchLightUncheckedPreview() {
    SacramentTheme(darkTheme = false, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSwitch(checked = false, onCheckedChange = {})
    }
}

@Preview(name = "Dark Theme - Checked")
@Composable
private fun SacramentSwitchDarkCheckedPreview() {
    SacramentTheme(darkTheme = true, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSwitch(checked = true, onCheckedChange = {})
    }
}

@Preview(name = "Dark Theme - Unchecked")
@Composable
private fun SacramentSwitchDarkUncheckedPreview() {
    SacramentTheme(darkTheme = true, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentSwitch(checked = false, onCheckedChange = {})
    }
}
