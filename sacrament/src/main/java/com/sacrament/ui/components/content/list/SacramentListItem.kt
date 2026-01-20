package com.sacrament.ui.components.content.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.preview.PreviewTheme
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
            .then(
                if (onClick != null) Modifier.clickable(
                    role = Role.Button,
                    onClick = onClick
                ) else
                    Modifier
            )
            .padding(vertical = spacing.sm, horizontal = spacing.lg),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(spacing.md),
    ) {
        if (leading != null) {
            leading()
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(spacing.xs)
        ) {
            if (overline != null) {
                overline()
            }
            headline()
            if (supporting != null) {
                supporting()
            }
        }
        if (trailing != null) {
            Spacer(
                modifier = Modifier.width(spacing.sm)
            )
            trailing()
        }
    }
}

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
