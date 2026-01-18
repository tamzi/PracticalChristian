package com.sacrament.ui.testing

import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag

/**
 * Test tags for UI testing and accessibility.
 *
 * Provides consistent test identifiers across the design system.
 * Use these tags in composables to enable reliable UI tests.
 *
 * Usage:
 * ```
 * Modifier.testTag(TestTags.Button.Primary)
 * ```
 */
object TestTags {
    /**
     * Button test tags.
     */
    object Button {
        const val Primary = "button_primary"
        const val Secondary = "button_secondary"
        const val Tertiary = "button_tertiary"
        const val Icon = "button_icon"
        const val Fab = "button_fab"
    }

    /**
     * Navigation test tags.
     */
    object Navigation {
        const val TopAppBar = "navigation_top_app_bar"
        const val BottomBar = "navigation_bottom_bar"
        const val Tab = "navigation_tab"
        const val Rail = "navigation_rail"
    }

    /**
     * Input test tags.
     */
    object Input {
        const val TextField = "input_text_field"
        const val SearchField = "input_search_field"
        const val Checkbox = "input_checkbox"
        const val Radio = "input_radio"
        const val Switch = "input_switch"
    }

    /**
     * Content test tags.
     */
    object Content {
        const val ListItem = "content_list_item"
        const val Card = "content_card"
        const val Avatar = "content_avatar"
        const val Chip = "content_chip"
        const val Badge = "content_badge"
    }

    /**
     * Pattern test tags.
     */
    object Pattern {
        const val ScreenScaffold = "pattern_screen_scaffold"
        const val EmptyState = "pattern_empty_state"
        const val ErrorState = "pattern_error_state"
        const val LoadingState = "pattern_loading_state"
    }

    /**
     * Feedback test tags.
     */
    object Feedback {
        const val Snackbar = "feedback_snackbar"
        const val Toast = "feedback_toast"
        const val ProgressIndicator = "feedback_progress_indicator"
        const val InlineMessage = "feedback_inline_message"
    }
}

/**
 * Extension function to add test tag with optional content description.
 *
 * Usage:
 * ```
 * Modifier.testTag(TestTags.Button.Primary, "Submit button")
 * ```
 */
fun Modifier.testTag(
    tag: String,
    contentDescription: String? = null,
): Modifier {
    return this.semantics {
        testTag = tag
        if (contentDescription != null) {
            this.contentDescription = contentDescription
        }
    }
}

