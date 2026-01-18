package com.sacrament.ui.components.content

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.preview.PreviewTheme
import com.sacrament.ui.primitives.SacramentImage
import com.sacrament.ui.primitives.SacramentText

/**
 * Basic list item with text only.
 *
 * @param headline The main text to display
 * @param modifier Modifier to apply to the list item
 * @param supporting Optional supporting/description text
 * @param overline Optional text above the headline
 * @param onClick Optional click handler
 */
@Composable
fun SacramentListItem(
    headline: String,
    modifier: Modifier = Modifier,
    supporting: String? = null,
    overline: String? = null,
    onClick: (() -> Unit)? = null,
) {
    SacramentListItem(
        modifier = modifier,
        headline = {
            SacramentText(
                text = headline,
                style = SacramentTheme.typography.bodyLarge,
                color = SacramentTheme.colors.text.strong
            )
        },
        supporting = supporting?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        overline = overline?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodySmall,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        onClick = onClick
    )
}

/**
 * List item with a trailing content (defaults to chevron icon, but customizable).
 *
 * @param headline The main text to display
 * @param modifier Modifier to apply to the list item
 * @param supporting Optional supporting/description text
 * @param overline Optional text above the headline
 * @param trailing Optional custom trailing content (defaults to chevron icon)
 * @param onClick Optional click handler
 */
@Composable
fun SacramentListItemWithTrailingIcon(
    headline: String,
    modifier: Modifier = Modifier,
    supporting: String? = null,
    overline: String? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    SacramentListItem(
        modifier = modifier,
        headline = {
            SacramentText(
                text = headline,
                style = SacramentTheme.typography.bodyLarge,
                color = SacramentTheme.colors.text.strong
            )
        },
        supporting = supporting?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        overline = overline?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodySmall,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        trailing = trailing ?: {
            Icon(
                imageVector = SacramentIcons.SacramentIconChevronRight,
                contentDescription = null,
                modifier = Modifier.size(SacramentTheme.iconSizes.md),
                tint = SacramentTheme.colors.text.muted
            )
        },
        onClick = onClick
    )
}

/**
 * List item with a leading image.
 *
 * @param headline The main text to display
 * @param imageUrl The URL of the image to display (nullable, shows placeholder if null)
 * @param modifier Modifier to apply to the list item
 * @param supporting Optional supporting/description text
 * @param overline Optional text above the headline
 * @param imageSize Size of the circular image (default: xl)
 * @param imageContentDescription Content description for the image (for accessibility)
 * @param onClick Optional click handler
 */
@Composable
fun SacramentListItemLeadingImage(
    headline: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    supporting: String? = null,
    overline: String? = null,
    imageSize: Dp = SacramentTheme.iconSizes.xl,
    imageContentDescription: String? = null,
    onClick: (() -> Unit)? = null,
) {
    SacramentListItem(
        modifier = modifier,
        headline = {
            SacramentText(
                text = headline,
                style = SacramentTheme.typography.bodyLarge,
                color = SacramentTheme.colors.text.strong
            )
        },
        supporting = supporting?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        overline = overline?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodySmall,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        leading = {
            SacramentImage(
                imageUrl = imageUrl,
                contentDescription = imageContentDescription,
                size = imageSize,
                shape = CircleShape
            )
        },
        onClick = onClick
    )
}

/**
 * List item with a leading image and trailing content (defaults to chevron icon, but customizable).
 *
 * @param headline The main text to display
 * @param imageUrl The URL of the image to display (nullable, shows placeholder if null)
 * @param modifier Modifier to apply to the list item
 * @param supporting Optional supporting/description text
 * @param overline Optional text above the headline
 * @param imageSize Size of the circular image (default: xl)
 * @param imageContentDescription Content description for the image (for accessibility)
 * @param trailing Optional custom trailing content (defaults to chevron icon)
 * @param onClick Optional click handler
 */
@Composable
fun SacramentListItemLeadingImageAndTrailingIcon(
    headline: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    supporting: String? = null,
    overline: String? = null,
    imageSize: Dp = SacramentTheme.iconSizes.xl,
    imageContentDescription: String? = null,
    trailing: @Composable (() -> Unit)? = null,
    onClick: (() -> Unit)? = null,
) {
    SacramentListItem(
        modifier = modifier,
        headline = {
            SacramentText(
                text = headline,
                style = SacramentTheme.typography.bodyLarge,
                color = SacramentTheme.colors.text.strong
            )
        },
        supporting = supporting?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        overline = overline?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodySmall,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        leading = {
            SacramentImage(
                imageUrl = imageUrl,
                contentDescription = imageContentDescription,
                size = imageSize,
                shape = CircleShape
            )
        },
        trailing = trailing ?: {
            Icon(
                imageVector = SacramentIcons.SacramentIconChevronRight,
                contentDescription = null,
                modifier = Modifier.size(SacramentTheme.iconSizes.md),
                tint = SacramentTheme.colors.text.muted
            )
        },
        onClick = onClick
    )
}

/**
 * List item with a rounded rectangular image on the left (for thumbnails, previews).
 *
 * @param headline The main text to display
 * @param imageUrl The URL of the image to display (nullable, shows placeholder if null)
 * @param modifier Modifier to apply to the list item
 * @param supporting Optional supporting/description text
 * @param overline Optional text above the headline
 * @param imageSize Size of the rounded image (default: xl)
 * @param imageContentDescription Content description for the image (for accessibility)
 * @param onClick Optional click handler
 */
@Composable
fun SacramentListItemLeadingRoundedImage(
    headline: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    supporting: String? = null,
    overline: String? = null,
    imageSize: Dp = SacramentTheme.iconSizes.xl,
    imageContentDescription: String? = null,
    onClick: (() -> Unit)? = null,
) {
    SacramentListItem(
        modifier = modifier,
        headline = {
            SacramentText(
                text = headline,
                style = SacramentTheme.typography.bodyLarge,
                color = SacramentTheme.colors.text.strong
            )
        },
        supporting = supporting?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        overline = overline?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodySmall,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        leading = {
            SacramentImage(
                imageUrl = imageUrl,
                contentDescription = imageContentDescription,
                size = imageSize,
                shape = RoundedCornerShape(SacramentTheme.radii.sm)
            )
        },
        onClick = onClick
    )
}

/**
 * List item with a rectangular (wide) image on the left (for banners, wide format images).
 *
 * @param headline The main text to display
 * @param imageUrl The URL of the image to display (nullable, shows placeholder if null)
 * @param modifier Modifier to apply to the list item
 * @param supporting Optional supporting/description text
 * @param overline Optional text above the headline
 * @param imageWidth Width of the rectangular image
 * @param imageHeight Height of the rectangular image
 * @param imageContentDescription Content description for the image (for accessibility)
 * @param onClick Optional click handler
 */
@Composable
fun SacramentListItemLeadingRectangularImage(
    headline: String,
    imageUrl: String?,
    modifier: Modifier = Modifier,
    supporting: String? = null,
    overline: String? = null,
    imageWidth: Dp = 56.dp,
    imageHeight: Dp = SacramentTheme.iconSizes.xl,
    imageContentDescription: String? = null,
    onClick: (() -> Unit)? = null,
) {
    val shape = RoundedCornerShape(SacramentTheme.radii.xs)
    
    SacramentListItem(
        modifier = modifier,
        headline = {
            SacramentText(
                text = headline,
                style = SacramentTheme.typography.bodyLarge,
                color = SacramentTheme.colors.text.strong
            )
        },
        supporting = supporting?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        overline = overline?.let {
            {
                SacramentText(
                    text = it,
                    style = SacramentTheme.typography.bodySmall,
                    color = SacramentTheme.colors.text.muted
                )
            }
        },
        leading = {
            if (imageUrl != null) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = imageContentDescription,
                    modifier = Modifier
                        .size(width = imageWidth, height = imageHeight)
                        .clip(shape),
                    contentScale = ContentScale.Crop
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(width = imageWidth, height = imageHeight)
                        .clip(shape)
                        .background(SacramentTheme.colors.surfaces.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = SacramentIcons.SacramentIconPerson,
                        contentDescription = null,
                        modifier = Modifier.size(imageHeight * 0.5f),
                        tint = SacramentTheme.colors.text.muted
                    )
                }
            }
        },
        onClick = onClick
    )
}

/**
 * Base list item component with fully customizable content slots.
 *
 * For most use cases, prefer the convenience variants:
 * - SacramentListItem(headline, supporting) for text-only items
 * - SacramentListItemWithTrailingIcon(...) for text with chevron
 * - SacramentListItemLeadingImage(...) for items with circular avatar images
 * - SacramentListItemLeadingImageAndTrailingIcon(...) for items with avatar + chevron
 * - SacramentListItemLeadingRoundedImage(...) for rounded thumbnail images
 * - SacramentListItemLeadingRectangularImage(...) for rectangular banner images
 *
 * Use this base version when you need full control over the content slots.
 */
@Composable
fun SacramentListItem(
    modifier: Modifier = Modifier,
    headline: @Composable () -> Unit,
    onClick: (() -> Unit)? = null,
    overline: @Composable (() -> Unit)? = null,
    supporting: @Composable (() -> Unit)? = null,
    leading: @Composable (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    val spacing = SacramentTheme.spacing
    Row(
        modifier = modifier
            .fillMaxWidth()
            .then(if (onClick != null) Modifier.clickable(role = Role.Button, onClick = onClick) else Modifier)
            .padding(vertical = spacing.sm, horizontal = spacing.lg),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        if (leading != null) {
            leading()
        }
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(spacing.xs)) {
            if (overline != null) {
                overline()
            }
            headline()
            if (supporting != null) {
                supporting()
            }
        }
        if (trailing != null) {
            Spacer(modifier = Modifier.width(spacing.sm))
            trailing()
        }
    }
}

// Previews demonstrating the simple API

@Preview
@Composable
private fun SacramentListItemPreview() {
    PreviewTheme {
        SacramentListItem(
            headline = "Basic list item",
            supporting = "Supporting text"
        )
    }
}

@Preview
@Composable
private fun SacramentListItemWithTrailingIconPreview() {
    PreviewTheme {
        SacramentListItemWithTrailingIcon(
            headline = "Settings",
            supporting = "App preferences and configuration",
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun SacramentListItemLeadingImagePreview() {
    PreviewTheme {
        SacramentListItemLeadingImage(
            headline = "John Doe",
            supporting = "john.doe@example.com",
            imageUrl = null,
            imageSize = SacramentTheme.iconSizes.lg,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun SacramentListItemLeadingImageAndTrailingIconPreview() {
    PreviewTheme {
        SacramentListItemLeadingImageAndTrailingIcon(
            headline = "Manage Profile",
            supporting = "Update your personal information",
            imageUrl = null,// Shows placeholder avatar
            imageSize = SacramentTheme.iconSizes.lg,
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun SacramentListItemLeadingRoundedImagePreview() {
    PreviewTheme {
        SacramentListItemLeadingRoundedImage(
            headline = "Rounded image item",
            supporting = "For thumbnails and previews",
            imageUrl = null
        )
    }
}

@Preview
@Composable
private fun SacramentListItemLeadingRectangularImagePreview() {
    PreviewTheme {
        SacramentListItemLeadingRectangularImage(
            headline = "Rectangular image item",
            supporting = "For banners and wide format images",
            imageUrl = null
        )
    }
}

