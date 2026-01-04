package com.sacrament.ui.patterns

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.preview.PreviewTheme
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.testing.TestTags
import com.sacrament.ui.testing.testTag

/**
 * Loading state pattern with centered progress indicator.
 *
 * Usage:
 * `SacramentLoadingState()`
 */
@Composable
fun SacramentLoadingState(
    modifier: Modifier = Modifier,
    variant: SacramentProgressVariant = SacramentProgressVariant.Circular,
) {
    val spacing = SacramentTheme.spacing

    SacramentCenteredColumn(
        modifier = modifier
            .testTag(TestTags.Pattern.LoadingState, "Loading state")
            .fillMaxSize()
            .padding(spacing.lg),
    ) {
        SacramentProgressIndicator(variant = variant)
    }
}

@Preview
@Composable
fun SacramentLoadingStatePreview() {
    PreviewTheme {
        SacramentLoadingState()
    }
}

