package com.sacrament.ui.foundation.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.automirrored.rounded.Logout
import androidx.compose.material.icons.automirrored.rounded.Note
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.AccessTime
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Bookmark
import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Event
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.GridOn
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Help
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Language
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.LocalLibrary
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Security
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Centralized icon registry for the Sacrament design system.
 *
 * All icons used across the app should be accessed through this object
 * instead of directly importing Material icons. This provides a single
 * source of truth and makes it easier to swap icon sets in the future.
 *
 * Icons are organized by category:
 * - Navigation (arrows, chevrons)
 * - Actions (add, edit, delete, share, search)
 * - Content (book, note, tag, list, grid)
 * - Navigation destinations (home, library)
 * - UI elements (menu, notifications, settings)
 * - User & Profile (person, account, email, logout)
 * - Security & Privacy (lock, security, visibility)
 * - Calendar & Events (event, calendar)
 * - Status (warning, favorite, verified, info, star)
 * - Theme & Settings (light/dark mode, language, help)
 *
 * Usage:
 * ```
 * SacramentIcon(imageVector = SacramentIcons.ArrowBack, ...)
 * SacramentIconButton(imageVector = SacramentIcons.Settings, ...)
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
    val Delete: ImageVector = Icons.Rounded.Delete
    val Check: ImageVector = Icons.Rounded.Check
    val Share: ImageVector = Icons.Rounded.Share
    val Search: ImageVector = Icons.Rounded.Search
    val MoreVert: ImageVector = Icons.Rounded.MoreVert

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
    val Settings: ImageVector = Icons.Rounded.Settings
    val AccessTime: ImageVector = Icons.Rounded.AccessTime

    // User & Profile
    val Person: ImageVector = Icons.Rounded.Person
    val AccountCircle: ImageVector = Icons.Rounded.AccountCircle
    val Email: ImageVector = Icons.Rounded.Email
    val Logout: ImageVector = Icons.AutoMirrored.Rounded.Logout

    // Security & Privacy
    val Lock: ImageVector = Icons.Rounded.Lock
    val Security: ImageVector = Icons.Rounded.Security
    val Visibility: ImageVector = Icons.Rounded.Visibility
    val VisibilityOff: ImageVector = Icons.Rounded.VisibilityOff

    // Calendar & Events
    val Event: ImageVector = Icons.Rounded.Event
    val CalendarMonth: ImageVector = Icons.Rounded.CalendarMonth

    // Status
    val Warning: ImageVector = Icons.Rounded.Warning
    val Favorite: ImageVector = Icons.Rounded.Favorite
    val Verified: ImageVector = Icons.Rounded.Verified
    val Info: ImageVector = Icons.Rounded.Info
    val Star: ImageVector = Icons.Rounded.Star

    // Theme & Settings
    val LightMode: ImageVector = Icons.Rounded.LightMode
    val DarkMode: ImageVector = Icons.Rounded.DarkMode
    val Language: ImageVector = Icons.Rounded.Language
    val Help: ImageVector = Icons.Rounded.Help
}
