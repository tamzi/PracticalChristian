package com.sacrament.ui.components.action

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Primary action button with Sacrament tokens.
 */
@Composable
fun SacramentButton(
    text: String,
    onClick: () -> Unit,
    variant: SacramentButtonVariant = SacramentButtonVariant.Filled,
    tone: SacramentButtonTone = SacramentButtonTone.Brand,
    size: SacramentButtonSize = SacramentButtonSize.Medium,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    modifier: Modifier = Modifier,
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
    val shape = SacramentButtonDefaults.shape(size)
    val borderStroke = if (borderColor != Color.Unspecified) {
        BorderStroke(SacramentButtonDefaults.borderWidth(), borderColor)
    } else {
        null
    }
    val buttonModifier = modifier
        .defaultMinSize(minHeight = SacramentButtonDefaults.height(size))
        .clip(shape)
        .background(containerColor, shape)
        .then(if (borderStroke != null) Modifier.border(borderStroke, shape) else Modifier)
        .clickable(
            enabled = enabled,
            role = Role.Button,
            interactionSource = interactionSource,
            onClick = onClick,
        )
        .padding(SacramentButtonDefaults.contentPadding(size))

    Box(
        modifier = buttonModifier,
        contentAlignment = Alignment.Center,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(SacramentButtonDefaults.iconSpacing(size)),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leadingIcon != null) {
                SacramentIcon(
                    imageVector = leadingIcon,
                    contentDescription = null,
                    tint = contentColor,
                    size = SacramentButtonDefaults.iconSize(size),
                )
            }
            SacramentText(
                text = text,
                style = SacramentButtonDefaults.textStyle(size),
                color = contentColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            if (trailingIcon != null) {
                SacramentIcon(
                    imageVector = trailingIcon,
                    contentDescription = null,
                    tint = contentColor,
                    size = SacramentButtonDefaults.iconSize(size),
                )
            }
        }
    }
}

@Preview(name = "Light Theme")
@Composable
private fun SacramentButtonLightPreview() {
    SacramentTheme(darkTheme = false, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SacramentButton(text = "Primary", onClick = {})
            SacramentButton(
                text = "Outlined",
                onClick = {},
                variant = SacramentButtonVariant.Outlined,
            )
            SacramentButton(
                text = "Ghost",
                onClick = {},
                variant = SacramentButtonVariant.Ghost,
            )
        }
    }
}

@Preview(name = "Dark Theme")
@Composable
private fun SacramentButtonDarkPreview() {
    SacramentTheme(darkTheme = true, navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            SacramentButton(text = "Primary", onClick = {})
            SacramentButton(
                text = "Outlined",
                onClick = {},
                variant = SacramentButtonVariant.Outlined,
            )
            SacramentButton(
                text = "Ghost",
                onClick = {},
                variant = SacramentButtonVariant.Ghost,
            )
        }
    }
}
