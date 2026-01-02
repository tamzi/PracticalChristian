package com.sacrament.ui.components.action

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon

/**
 * Icon-only action button.
 */
@Composable
fun SacramentIconButton(
    imageVector: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    variant: SacramentButtonVariant = SacramentButtonVariant.Ghost,
    tone: SacramentButtonTone = SacramentButtonTone.Brand,
    size: SacramentIconButtonSize = SacramentIconButtonSize.Medium,
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
    val shape = SacramentIconButtonDefaults.shape()
    val borderStroke = if (borderColor != Color.Unspecified) {
        BorderStroke(SacramentButtonDefaults.borderWidth(), borderColor)
    } else {
        null
    }

    Box(
        modifier = modifier
            .size(SacramentIconButtonDefaults.containerSize(size))
            .clip(shape)
            .background(containerColor, shape)
            .then(if (borderStroke != null) Modifier.border(borderStroke, shape) else Modifier)
            .clickable(
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        SacramentIcon(
            imageVector = imageVector,
            contentDescription = contentDescription,
            tint = contentColor,
            size = SacramentIconButtonDefaults.iconSize(size),
        )
    }
}

@Preview
@Composable
private fun SacramentIconButtonPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentIconButton(
            imageVector = Icons.Rounded.Star,
            contentDescription = "Favorite",
            onClick = {},
        )
    }
}
