package com.sacrament.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * Preview parameter providers for design system component previews.
 *
 * Provides parameterized preview data for testing component variants.
 */

/**
 * Provider for boolean values (enabled/disabled, selected/unselected, etc.).
 */
class BooleanPreviewParameterProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(false, true)
}

/**
 * Provider for size variants.
 */
class SizePreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("Small", "Medium", "Large")
}

/**
 * Provider for tone/intent variants.
 */
class TonePreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("Neutral", "Brand", "Success", "Warning", "Error", "Info")
}

/**
 * Provider for variant types.
 */
class VariantPreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("Filled", "Outlined", "Ghost", "Text")
}

/**
 * Provider for loading states.
 */
class LoadingStatePreviewParameterProvider : PreviewParameterProvider<String> {
    override val values: Sequence<String>
        get() = sequenceOf("Idle", "Loading", "Success", "Error")
}

