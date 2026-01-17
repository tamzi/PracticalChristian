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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * List item with optional leading/trailing content and supporting text.
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

@Preview
@Composable
private fun SacramentListItemPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        Surface(color = Color.White) {
            SacramentListItem(
                headline = { SacramentText(text = "List item") },
                supporting = { SacramentText(text = "Supporting text", style = SacramentTheme.typography.bodySmall) },
                trailing = { SacramentText(text = ">", style = SacramentTheme.typography.bodySmall) },
            )
        }
    }
}

@Preview
@Composable
private fun SacramentListItemNoLeadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        Surface(color = Color.White) {
            SacramentListItem(
                headline = { SacramentText(text = "List item without leading icon") },
                supporting = { SacramentText(text = "Supporting text", style = SacramentTheme.typography.bodySmall) },
            )
        }
    }
}

@Preview
@Composable
private fun SacramentListItemWithIconPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        Surface(color = Color.White) {
            SacramentListItem(
                leading = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings",
                        modifier = Modifier.size(24.dp),
                        tint = Color.Gray
                    )
                },
                headline = { SacramentText(text = "List item with icon") },
                supporting = { SacramentText(text = "Supporting text", style = SacramentTheme.typography.bodySmall) },
            )
        }
    }
}
