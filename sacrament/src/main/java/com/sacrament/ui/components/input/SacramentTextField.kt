package com.sacrament.ui.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText

/**
 * Text input field backed by Sacrament tokens.
 */
@Composable
fun SacramentTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    size: SacramentTextFieldSize = SacramentTextFieldSize.Medium,
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    label: String? = null,
    placeholder: String? = null,
    supportingText: String? = null,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
) {
    val colors = SacramentTextFieldDefaults.colors()
    val focused by interactionSource.collectIsFocusedAsState()
    val borderColor = when {
        !enabled -> colors.disabledBorder
        isError -> colors.errorBorder
        focused -> colors.focusedBorder
        else -> colors.border
    }
    val background = if (enabled) colors.background else colors.disabledBackground
    val textColor = if (enabled) colors.text else colors.disabledText
    val placeholderColor = if (enabled) colors.placeholder else colors.disabledPlaceholder
    val iconColor = if (enabled) colors.icon else colors.disabledPlaceholder
    val shape = SacramentTextFieldDefaults.shape()
    val spacing = SacramentTheme.spacing
    val fieldModifier = modifier
        .fillMaxWidth()
        .defaultMinSize(minHeight = SacramentTextFieldDefaults.minHeight(size))
        .clip(shape)
        .background(background, shape)
        .border(SacramentTextFieldDefaults.borderWidth(), borderColor, shape)
        .padding(SacramentTextFieldDefaults.contentPadding(size))

    Column(verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
        if (label != null) {
            SacramentText(
                text = label,
                style = SacramentTheme.typography.labelSmall,
                color = if (enabled) colors.placeholder else colors.disabledPlaceholder,
            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = fieldModifier,
            enabled = enabled,
            singleLine = singleLine,
            maxLines = maxLines,
            textStyle = SacramentTheme.typography.bodyMedium.copy(color = textColor),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            decorationBox = { innerTextField ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (leadingIcon != null) {
                        SacramentIcon(
                            imageVector = leadingIcon,
                            contentDescription = null,
                            tint = iconColor,
                            size = SacramentTheme.iconSizes.md,
                        )
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty() && placeholder != null) {
                            SacramentText(
                                text = placeholder,
                                style = SacramentTheme.typography.bodyMedium,
                                color = placeholderColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                        innerTextField()
                    }
                    if (trailingIcon != null) {
                        SacramentIcon(
                            imageVector = trailingIcon,
                            contentDescription = null,
                            tint = iconColor,
                            size = SacramentTheme.iconSizes.md,
                        )
                    }
                }
            },
        )
        if (supportingText != null) {
            val supportColor = if (isError) colors.errorBorder else colors.placeholder
            SacramentText(
                text = supportingText,
                style = SacramentTheme.typography.bodySmall,
                color = supportColor,
            )
        }
    }
}

@Preview
@Composable
private fun SacramentTextFieldPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            SacramentTextField(
                value = "",
                onValueChange = {},
                label = "Email",
                placeholder = "name@example.com",
                supportingText = "We never share your email.",
            )
            SacramentTextField(
                value = "Hello",
                onValueChange = {},
                isError = true,
                supportingText = "Something went wrong.",
            )
        }
    }
}
