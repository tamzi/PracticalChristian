package com.practicalchristian.app.feature.notifications.hub

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.GridView
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material.icons.rounded.Verified
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * UI state for the notifications hub screen.
 */
data class NotificationsHubScreenUiState(
    val sections: List<NotificationSection> = emptyList(),
    val unreadCount: Int = 0,
    val listState: UiListState<List<NotificationSection>> = UiListState.Idle,
)

/**
 * ViewModel for the notifications hub screen.
 *
 * Uses local sample data until the notifications repository is available.
 */
@HiltViewModel
class NotificationsHubViewModel @Inject constructor(
) : StatefulViewModel<NotificationsHubScreenUiState>(NotificationsHubScreenUiState()) {

    init {
        observeNotifications()
    }

    private fun observeNotifications() {
        viewModelScope.launch {
            update { copy(listState = UiListState.Loading) }

            // Simulate loading delay
            delay(500)

            val sampleSections = createSampleNotifications()
            val unreadCount = sampleSections.sumOf { section ->
                section.notifications.count { it.isUnread }
            }

            update {
                copy(
                    sections = sampleSections,
                    unreadCount = unreadCount,
                    listState = UiListState.Success(data = UiSuccessState.Data(data = sampleSections))
                )
            }
        }
    }

    /**
     * Mark a notification as read.
     */
    fun markAsRead(notificationId: String) {
        viewModelScope.launch {
            // Update local state for now
            update {
                val updatedSections = sections.map { section ->
                    section.copy(
                        notifications = section.notifications.map { notification ->
                            if (notification.id == notificationId && notification.isUnread) {
                                notification.copy(isUnread = false)
                            } else {
                                notification
                            }
                        }
                    )
                }
                val newUnreadCount = updatedSections.sumOf { section ->
                    section.notifications.count { it.isUnread }
                }
                copy(
                    sections = updatedSections,
                    unreadCount = newUnreadCount,
                    listState = UiListState.Success(
                        data = if (updatedSections.isEmpty()) {
                            UiSuccessState.Empty
                        } else {
                            UiSuccessState.Data(data = updatedSections)
                        }
                    )
                )
            }
        }
    }

    /**
     * Mark all notifications as read.
     */
    fun markAllAsRead() {
        viewModelScope.launch {
            // Update local state for now
            update {
                val updatedSections = sections.map { section ->
                    section.copy(
                        notifications = section.notifications.map { it.copy(isUnread = false) }
                    )
                }
                copy(
                    sections = updatedSections,
                    unreadCount = 0,
                    listState = UiListState.Success(
                        data = if (updatedSections.isEmpty()) {
                            UiSuccessState.Empty
                        } else {
                            UiSuccessState.Data(data = updatedSections)
                        }
                    )
                )
            }
        }
    }

    /**
     * Refresh notifications.
     */
    fun refresh() {
        observeNotifications()
    }

    /**
     * Create sample notifications for development.
     */
    private fun createSampleNotifications(): List<NotificationSection> = listOf(
        createTodaySection(),
        createThisWeekSection(),
    )

    private fun createTodaySection(): NotificationSection = NotificationSection(
        title = "Today",
        notifications = listOf(
            sampleNotificationItem(
                id = "1",
                iconVector = Icons.Rounded.Favorite,
                tone = NotificationIconTone.Accent,
                text = "Time for your morning prayer. Start your day with " +
                    "gratitude and reflection",
                isUnread = true,
            ),
            sampleNotificationItem(
                id = "2",
                iconVector = Icons.Rounded.GridView,
                tone = NotificationIconTone.Success,
                text = "Your daily reading plan is ready. Continue your journey " +
                    "through Scripture",
                isUnread = true,
            ),
            sampleNotificationItem(
                id = "3",
                iconVector = Icons.Rounded.Verified,
                tone = NotificationIconTone.Info,
                text = "New meditation available: Finding peace in difficult times",
                isUnread = true,
                hasOverlay = true,
            ),
        ),
    )

    private fun createThisWeekSection(): NotificationSection = NotificationSection(
        title = "This week",
        notifications = listOf(
            sampleNotificationItem(
                id = "4",
                iconVector = Icons.Rounded.GridView,
                tone = NotificationIconTone.Success,
                text = "You've completed 5 days of your reading plan. Keep up " +
                    "the great work!",
                isUnread = true,
            ),
            sampleNotificationItem(
                id = "5",
                iconVector = Icons.Rounded.Favorite,
                tone = NotificationIconTone.Accent,
                text = "Don't forget to review your bookmarked verses from this week",
                isUnread = false,
            ),
            sampleNotificationItem(
                id = "6",
                iconVector = Icons.Rounded.Notifications,
                tone = NotificationIconTone.Success,
                text = "Your evening prayer reminder is set for 8:00 PM",
                isUnread = false,
            ),
            sampleNotificationItem(
                id = "7",
                iconVector = Icons.Rounded.Favorite,
                tone = NotificationIconTone.Accent,
                text = "New devotional book added to your library: Daily Wisdom",
                isUnread = false,
            ),
        ),
    )

    private fun sampleNotificationItem(
        id: String,
        iconVector: ImageVector,
        tone: NotificationIconTone,
        text: String,
        isUnread: Boolean,
        hasOverlay: Boolean = false,
    ): NotificationItem = NotificationItem(
        id = id,
        iconData = NotificationIconData(
            iconVector = iconVector,
            tone = tone,
            hasOverlay = hasOverlay,
        ),
        text = text,
        isUnread = isUnread,
    )
}

/**
 * Data class representing a notification item.
 */
data class NotificationItem(
    val id: String,
    val iconData: NotificationIconData,
    val text: String,
    val isUnread: Boolean,
)

/**
 * Data class representing a notification section.
 */
data class NotificationSection(
    val title: String,
    val notifications: List<NotificationItem>,
)

/**
 * Data class for notification icon configuration.
 *
 * NOTE: Color and ImageVector are stored in StateFlow (in-memory) which is fine,
 * but if we need to save state across process death, we should use identifiers
 * (e.g., enum for icon type, color hex string) instead.
 */
data class NotificationIconData(
    val iconVector: ImageVector,
    val tone: NotificationIconTone,
    val hasOverlay: Boolean = false,
)

enum class NotificationIconTone {
    Accent,
    Success,
    Info,
}
