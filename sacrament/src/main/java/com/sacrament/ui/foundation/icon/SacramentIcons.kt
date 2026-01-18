package com.sacrament.ui.foundation.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.automirrored.rounded.Help
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
 * SacramentIcon(imageVector = SacramentIcons.SacramentIconArrowBack, ...)
 * SacramentIconButton(imageVector = SacramentIcons.SacramentIconSettings, ...)
 * ```
 */
object SacramentIcons {
    // Navigation
    val SacramentIconArrowBack: ImageVector = Icons.AutoMirrored.Rounded.ArrowBack
    val SacramentIconArrowForward: ImageVector = Icons.AutoMirrored.Rounded.ArrowForward
    val SacramentIconArrowForwardIos: ImageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos
    val SacramentIconChevronRight: ImageVector = Icons.Rounded.ChevronRight

    // Actions
    val SacramentIconAdd: ImageVector = Icons.Rounded.Add
    val SacramentIconClose: ImageVector = Icons.Rounded.Close
    val SacramentIconEdit: ImageVector = Icons.Rounded.Edit
    val SacramentIconSave: ImageVector = Icons.Rounded.Save
    val SacramentIconRefresh: ImageVector = Icons.Rounded.Refresh
    val SacramentIconDoneAll: ImageVector = Icons.Rounded.DoneAll
    val SacramentIconDelete: ImageVector = Icons.Rounded.Delete
    val SacramentIconCheck: ImageVector = Icons.Rounded.Check
    val SacramentIconShare: ImageVector = Icons.Rounded.Share
    val SacramentIconSearch: ImageVector = Icons.Rounded.Search
    val SacramentIconMoreVert: ImageVector = Icons.Rounded.MoreVert

    // Content
    val SacramentIconBook: ImageVector = Icons.Rounded.Book
    val SacramentIconBookmark: ImageVector = Icons.Rounded.Bookmark
    val SacramentIconNote: ImageVector = Icons.AutoMirrored.Rounded.Note
    val SacramentIconTag: ImageVector = Icons.Rounded.Tag
    val SacramentIconList: ImageVector = Icons.AutoMirrored.Rounded.List
    val SacramentIconGridOn: ImageVector = Icons.Rounded.GridOn
    val SacramentIconGridView: ImageVector = Icons.Rounded.GridView

    // Navigation destinations
    val SacramentIconHome: ImageVector = Icons.Rounded.Home
    val SacramentIconLocalLibrary: ImageVector = Icons.Rounded.LocalLibrary

    // UI elements
    val SacramentIconMenu: ImageVector = Icons.Filled.Menu
    val SacramentIconNotifications: ImageVector = Icons.Rounded.Notifications
    val SacramentIconSettings: ImageVector = Icons.Rounded.Settings
    val SacramentIconAccessTime: ImageVector = Icons.Rounded.AccessTime

    // User & Profile
    val SacramentIconPerson: ImageVector = Icons.Rounded.Person
    val SacramentIconAccountCircle: ImageVector = Icons.Rounded.AccountCircle
    val SacramentIconEmail: ImageVector = Icons.Rounded.Email
    val SacramentIconLogout: ImageVector = Icons.AutoMirrored.Rounded.Logout

    // Security & Privacy
    val SacramentIconLock: ImageVector = Icons.Rounded.Lock
    val SacramentIconSecurity: ImageVector = Icons.Rounded.Security
    val SacramentIconVisibility: ImageVector = Icons.Rounded.Visibility
    val SacramentIconVisibilityOff: ImageVector = Icons.Rounded.VisibilityOff

    // Calendar & Events
    val SacramentIconEvent: ImageVector = Icons.Rounded.Event
    val SacramentIconCalendarMonth: ImageVector = Icons.Rounded.CalendarMonth

    // Status
    val SacramentIconWarning: ImageVector = Icons.Rounded.Warning
    val SacramentIconFavorite: ImageVector = Icons.Rounded.Favorite
    val SacramentIconVerified: ImageVector = Icons.Rounded.Verified
    val SacramentIconInfo: ImageVector = Icons.Rounded.Info
    val SacramentIconStar: ImageVector = Icons.Rounded.Star

    // Theme & Settings
    val SacramentIconLightMode: ImageVector = Icons.Rounded.LightMode
    val SacramentIconDarkMode: ImageVector = Icons.Rounded.DarkMode
    val SacramentIconLanguage: ImageVector = Icons.Rounded.Language
    val SacramentIconHelp: ImageVector = Icons.AutoMirrored.Rounded.Help
}
