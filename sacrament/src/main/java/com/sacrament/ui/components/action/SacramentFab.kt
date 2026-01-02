package com.sacrament.ui.components.action

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Edit
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Floating action button for primary actions.
 */
@Composable
fun SacramentFab(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    variant: SacramentButtonVariant = SacramentButtonVariant.Filled,
    tone: SacramentButtonTone = SacramentButtonTone.Brand,
    size: SacramentFabSize = SacramentFabSize.Medium,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
) {
    val colors = SacramentButtonDefaults.colors(variant, tone)
    val pressed by interactionSource.collectIsPressedAsState()
    val containerColor = when {
        !enabled -> colors.disabledContainer
        pressed -> colors.pressedContainer
        else -> colors.container
    }
    val borderColor = when {
        !enabled -> colors.disabledBorder
        pressed -> colors.pressedBorder
        else -> colors.border
    }
    val contentColor = if (enabled) colors.content else colors.disabledContent
    val shape = SacramentFabDefaults.shape(extended = label != null)
    val borderStroke = if (borderColor != Color.Unspecified) {
        BorderStroke(SacramentButtonDefaults.borderWidth(), borderColor)
    } else {
        null
    }
    val height = SacramentFabDefaults.height(size)
    val baseModifier = modifier
        .defaultMinSize(minWidth = if (label == null) height else 0.dp, minHeight = height)
        .shadow(SacramentFabDefaults.elevation(enabled), shape)
        .clip(shape)
        .background(containerColor, shape)
        .then(if (borderStroke != null) Modifier.border(borderStroke, shape) else Modifier)
        .clickable(
            enabled = enabled,
            role = Role.Button,
            interactionSource = interactionSource,
            onClick = onClick,
        )

    if (label == null) {
        Box(
            modifier = baseModifier.size(height),
            contentAlignment = Alignment.Center,
        ) {
            SacramentIcon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = contentColor,
                size = SacramentFabDefaults.iconSize(size),
            )
        }
    } else {
        val spacing = SacramentTheme.spacing
        Row(
            modifier = baseModifier.padding(SacramentFabDefaults.contentPadding(size)),
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SacramentIcon(
                imageVector = imageVector,
                contentDescription = contentDescription,
                tint = contentColor,
                size = SacramentFabDefaults.iconSize(size),
            )
            SacramentText(
                text = label,
                style = SacramentButtonDefaults.textStyle(SacramentButtonSize.Medium),
                color = contentColor,
                maxLines = 1,
            )
        }
    }
}

@Preview
@Composable
private fun SacramentFabPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SacramentTheme.spacing.lg),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            SacramentFab(
                imageVector = Icons.Rounded.Add,
                contentDescription = "Add",
                onClick = {},
            )
            SacramentFab(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit",
                label = "Edit",
                onClick = {},
            )
        }
    }
}
