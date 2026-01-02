package com.sacrament.ui.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon

/**
 * Checkbox input control.
 */
@Composable
fun SacramentCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    size: SacramentCheckboxSize = SacramentCheckboxSize.Medium,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    modifier: Modifier = Modifier,
) {
    val colors = SacramentCheckboxDefaults.colors()
    val shape = SacramentCheckboxDefaults.shape()
    val boxSize = SacramentCheckboxDefaults.size(size)
    val backgroundColor = when {
        !enabled -> colors.disabledBackground
        checked -> colors.checkedBackground
        else -> colors.disabledBackground.copy(alpha = 0f)
    }
    val borderColor = when {
        !enabled -> colors.disabledBorder
        checked -> colors.checkedBorder
        else -> colors.uncheckedBorder
    }
    val checkmarkColor = if (enabled) colors.checkmark else colors.disabledCheckmark

    Box(
        modifier = modifier
            .size(boxSize)
            .clip(shape)
            .background(backgroundColor, shape)
            .border(SacramentCheckboxDefaults.borderWidth(), borderColor, shape)
            .toggleable(
                value = checked,
                enabled = enabled,
                role = Role.Checkbox,
                interactionSource = interactionSource,
                onValueChange = onCheckedChange,
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (checked) {
            SacramentIcon(
                imageVector = Icons.Rounded.Check,
                contentDescription = null,
                tint = checkmarkColor,
                size = SacramentTheme.iconSizes.sm,
            )
        }
    }
}

@Preview
@Composable
private fun SacramentCheckboxPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentCheckbox(checked = true, onCheckedChange = {})
    }
}
