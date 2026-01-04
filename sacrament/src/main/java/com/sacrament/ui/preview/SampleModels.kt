package com.sacrament.ui.preview

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Sample models and data for design system component previews.
 *
 * Provides consistent test data across previews to ensure visual consistency.
 */

/**
 * Sample icons for use in previews.
 */
object SampleIcons {
    val Add: ImageVector = Icons.Rounded.Add
    val Check: ImageVector = Icons.Rounded.Check
    val Delete: ImageVector = Icons.Rounded.Delete
    val Edit: ImageVector = Icons.Rounded.Edit
    val Favorite: ImageVector = Icons.Rounded.Favorite
    val Info: ImageVector = Icons.Rounded.Info
    val Menu: ImageVector = Icons.Rounded.Menu
    val Search: ImageVector = Icons.Rounded.Search
    val Settings: ImageVector = Icons.Rounded.Settings
    val Warning: ImageVector = Icons.Rounded.Warning
}

/**
 * Sample text content for previews.
 */
object SampleText {
    const val ShortTitle = "Title"
    const val MediumTitle = "Medium Length Title"
    const val LongTitle = "This is a Very Long Title That Might Wrap"
    const val ShortBody = "Body text"
    const val MediumBody = "This is some body text that provides context."
    const val LongBody =
        "This is a longer body text that demonstrates how content wraps " +
            "across multiple lines and provides more context about the component's behavior."
    const val Caption = "Caption text"
    const val Label = "Label"
    const val Placeholder = "Placeholder text"
}

/**
 * Sample user data for previews.
 */
object SampleUsers {
    const val Name = "John Doe"
    const val Email = "john.doe@example.com"
    const val Initials = "JD"
    const val AvatarUrl = "https://example.com/avatar.jpg"
}

/**
 * Sample error messages for previews.
 */
object SampleErrors {
    const val Generic = "Something went wrong"
    const val Network = "Network error. Please check your connection."
    const val NotFound = "Item not found"
    const val Unauthorized = "You don't have permission to access this"
}

/**
 * Sample success messages for previews.
 */
object SampleSuccess {
    const val Saved = "Changes saved successfully"
    const val Deleted = "Item deleted"
    const val Created = "Item created"
}

