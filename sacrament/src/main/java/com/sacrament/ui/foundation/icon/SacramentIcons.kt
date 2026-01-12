package com.sacrament.ui.foundation.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.automirrored.rounded.Note
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.GridOn
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.LocalLibrary
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Centralized icon registry for the Sacrament design system.
 *
 * All icons used across the app should be accessed through this object
 * instead of directly importing Material icons. This provides a single
 * source of truth and makes it easier to swap icon sets in the future.
 *
 * Usage:
 * ```
 * SacramentIcon(imageVector = SacramentIcons.ArrowBack, ...)
 * ```
 */
object SacramentIcons {
    // Navigation
    val ArrowBack: ImageVector = Icons.AutoMirrored.Rounded.ArrowBack
    val ArrowForward: ImageVector = Icons.AutoMirrored.Rounded.ArrowForward
    val ArrowForwardIos: ImageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos
    val ChevronRight: ImageVector = Icons.Rounded.ChevronRight

    // Actions
    val Add: ImageVector = Icons.Rounded.Add
    val Close: ImageVector = Icons.Rounded.Close
    val Edit: ImageVector = Icons.Rounded.Edit
    val Save: ImageVector = Icons.Rounded.Save
    val Refresh: ImageVector = Icons.Rounded.Refresh
    val DoneAll: ImageVector = Icons.Rounded.DoneAll

    // Content
    val Book: ImageVector = Icons.Rounded.Book
    val Bookmark: ImageVector = Icons.Rounded.Bookmark
    val Note: ImageVector = Icons.AutoMirrored.Rounded.Note
    val Tag: ImageVector = Icons.Rounded.Tag
    val List: ImageVector = Icons.AutoMirrored.Rounded.List
    val GridOn: ImageVector = Icons.Rounded.GridOn
    val GridView: ImageVector = Icons.Rounded.GridView

    // Navigation destinations
    val Home: ImageVector = Icons.Rounded.Home
    val LocalLibrary: ImageVector = Icons.Rounded.LocalLibrary

    // UI elements
    val Menu: ImageVector = Icons.Filled.Menu
    val Notifications: ImageVector = Icons.Rounded.Notifications
    val AccessTime: ImageVector = Icons.Rounded.AccessTime

    // Status
    val Warning: ImageVector = Icons.Rounded.Warning
    val Favorite: ImageVector = Icons.Rounded.Favorite
    val Verified: ImageVector = Icons.Rounded.Verified

    // Theme
    val LightMode: ImageVector = Icons.Rounded.LightMode
    val DarkMode: ImageVector = Icons.Rounded.DarkMode
}
