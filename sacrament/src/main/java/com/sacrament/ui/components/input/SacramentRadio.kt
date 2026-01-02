package com.sacrament.ui.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.runtime.Composable
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
 * Radio selection control.
 */
@Composable
fun SacramentRadio(
    selected: Boolean,
    onClick: () -> Unit,
    size: SacramentRadioSize = SacramentRadioSize.Medium,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    modifier: Modifier = Modifier,
) {
    val colors = SacramentRadioDefaults.colors()
    val outerSize = SacramentRadioDefaults.size(size)
    val dotSize = SacramentRadioDefaults.dotSize(size)
    val borderColor = when {
        !enabled && selected -> colors.disabledSelected
        !enabled -> colors.disabledUnselected
        selected -> colors.selected
        else -> colors.unselected
    }
    val dotColor = if (enabled) colors.selected else colors.disabledSelected

    Box(
        modifier = modifier
            .size(outerSize)
            .clip(CircleShape)
            .border(SacramentRadioDefaults.borderWidth(), borderColor, CircleShape)
            .selectable(
                selected = selected,
                enabled = enabled,
                role = Role.RadioButton,
                interactionSource = interactionSource,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        if (selected) {
            Box(
                modifier = Modifier
                    .size(dotSize)
                    .clip(CircleShape)
                    .background(dotColor),
            )
        }
    }
}

@Preview
@Composable
private fun SacramentRadioPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentRadio(selected = true, onClick = {})
    }
}
