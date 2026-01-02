package com.sacrament.ui.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText
import androidx.compose.ui.unit.dp

/**
 * Avatar sizes.
 */
enum class SacramentAvatarSize {
    Small,
    Medium,
    Large,
}

/**
 * Avatar component for profile imagery or initials.
 */
@Composable
fun SacramentAvatar(
    modifier: Modifier = Modifier,
    size: SacramentAvatarSize = SacramentAvatarSize.Medium,
    backgroundColor: Color = SacramentTheme.colors.surfaces.surfaceVariant,
    contentColor: Color = SacramentTheme.colors.text.strong,
    imageVector: ImageVector? = null,
    initials: String? = null,
) {
    val diameter = when (size) {
        SacramentAvatarSize.Small -> 32.dp
        SacramentAvatarSize.Medium -> 40.dp
        SacramentAvatarSize.Large -> 56.dp
    }
    val textStyle = when (size) {
        SacramentAvatarSize.Small -> SacramentTheme.typography.labelSmall
        SacramentAvatarSize.Medium -> SacramentTheme.typography.labelMedium
        SacramentAvatarSize.Large -> SacramentTheme.typography.labelLarge
    }

    Box(
        modifier = modifier
            .size(diameter)
            .background(backgroundColor, CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        when {
            imageVector != null -> {
                SacramentIcon(
                    imageVector = imageVector,
                    contentDescription = initials,
                    tint = contentColor,
                    size = diameter / 2,
                )
            }
            initials != null -> {
                SacramentText(
                    text = initials,
                    style = textStyle,
                    color = contentColor,
                )
            }
        }
    }
}

@Preview
@Composable
private fun SacramentAvatarPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        SacramentAvatar(initials = "FT")
    }
}
