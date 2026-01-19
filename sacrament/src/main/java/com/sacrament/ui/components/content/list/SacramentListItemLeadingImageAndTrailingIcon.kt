package com.sacrament.ui.components.content.list

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.preview.PreviewTheme
import com.sacrament.ui.primitives.SacramentImage
import com.sacrament.ui.primitives.SacramentText

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
