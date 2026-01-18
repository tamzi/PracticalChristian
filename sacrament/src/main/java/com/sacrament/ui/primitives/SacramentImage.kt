package com.sacrament.ui.primitives

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.preview.PreviewTheme

/**
 * Image component with placeholder support for when images fail to load or are empty.
 *
 * Displays an AsyncImage from a URL with a placeholder icon shown in a colored box
 * when the image URL is null or fails to load.
 *
 * @param imageUrl The URL of the image to load (can be null)
 * @param contentDescription Content description for accessibility
 * @param modifier Modifier to apply to the image
 * @param size Size of the image
 * @param shape Shape to clip the image to
 * @param contentScale How to scale the image content
 * @param placeholderIcon Icon to show when image is unavailable
 */
@Composable
fun SacramentImage(
    imageUrl: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    size: Dp = SacramentTheme.iconSizes.xl,
    shape: Shape = CircleShape,
    contentScale: ContentScale = ContentScale.Crop,
    placeholderIcon: ImageVector = SacramentIcons.SacramentIconPerson,
) {
    if (imageUrl != null) {
        AsyncImage(
            model = imageUrl,
            contentDescription = contentDescription,
            modifier = modifier
                .size(size)
                .clip(shape),
            contentScale = contentScale
        )
    } else {
        SacramentImagePlaceholder(
            modifier = modifier,
            size = size,
            shape = shape,
            icon = placeholderIcon
        )
    }
}

/**
 * Placeholder component for images that displays an icon in a colored box.
 *
 * Used as a fallback when images are unavailable or fail to load.
 *
 * @param modifier Modifier to apply to the placeholder
 * @param size Size of the placeholder box
 * @param shape Shape to clip the placeholder to
 * @param icon Icon to display in the placeholder
 */
@Composable
fun SacramentImagePlaceholder(
    modifier: Modifier = Modifier,
    size: Dp = SacramentTheme.iconSizes.xl,
    shape: Shape = CircleShape,
    icon: ImageVector = SacramentIcons.SacramentIconPerson,
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(shape)
            .background(SacramentTheme.colors.surfaces.surfaceVariant),
        contentAlignment = Alignment.Center
    ) {
        SacramentIcon(
            imageVector = icon,
            contentDescription = null,
            tint = SacramentTheme.colors.text.muted,
            size = size * 0.5f // Icon is half the size of the container
        )
    }
}

// Previews

@Preview
@Composable
private fun SacramentImagePlaceholderPreview() {
    PreviewTheme {
        SacramentImagePlaceholder(
            size = 56.dp,
            shape = CircleShape
        )
    }
}

@Preview
@Composable
private fun SacramentImageWithNullUrlPreview() {
    PreviewTheme {
        SacramentImage(
            imageUrl = null,
            contentDescription = "Profile picture",
            size = 56.dp
        )
    }
}
