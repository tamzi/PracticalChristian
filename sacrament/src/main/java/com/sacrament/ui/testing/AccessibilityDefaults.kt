package com.sacrament.ui.testing

import androidx.compose.foundation.layout.sizeIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Accessibility defaults for design system components.
 *
 * Provides minimum touch targets and semantic defaults per Material Design guidelines.
 */

/**
 * Minimum touch target size (48dp) as per Material Design guidelines.
 */
const val MIN_TOUCH_TARGET_SIZE_DP = 48f

/**
 * Minimum touch target size in dp.
 */
val MIN_TOUCH_TARGET_SIZE = 48.dp

/**
 * Modifier to ensure minimum touch target size.
 *
 * Applies minimum 48dp touch target for accessibility compliance.
 *
 * Usage:
 * ```
 * Modifier
 *     .minimumTouchTarget()
 *     .clickable { ... }
 * ```
 */
fun Modifier.minimumTouchTarget(): Modifier {
    return this.sizeIn(
        minWidth = MIN_TOUCH_TARGET_SIZE,
        minHeight = MIN_TOUCH_TARGET_SIZE,
    )
}

/**
 * Semantic defaults for common UI patterns.
 */
object AccessibilityDefaults {
    /**
     * Default content descriptions for common actions.
     */
    object ContentDescriptions {
        const val Close = "Close"
        const val Back = "Back"
        const val More = "More options"
        const val Search = "Search"
        const val Settings = "Settings"
        const val Edit = "Edit"
        const val Delete = "Delete"
        const val Save = "Save"
        const val Cancel = "Cancel"
        const val Add = "Add"
        const val Remove = "Remove"
        const val Favorite = "Favorite"
        const val Share = "Share"
    }

    /**
     * Default roles for semantic elements.
     */
    object Roles {
        const val Button = "Button"
        const val Checkbox = "Checkbox"
        const val RadioButton = "Radio button"
        const val Switch = "Switch"
        const val TextField = "Text field"
        const val Tab = "Tab"
    }
}

