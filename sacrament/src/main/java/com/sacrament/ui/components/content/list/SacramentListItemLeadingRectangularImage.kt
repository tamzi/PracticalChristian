package com.sacrament.ui.components.content.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.preview.PreviewTheme
import com.sacrament.ui.primitives.SacramentText

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
