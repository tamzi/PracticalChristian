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
 * TODO: When notification domain models and repository are available,
 * replace sample data with actual repository calls.
 */
@HiltViewModel
class NotificationsHubViewModel @Inject constructor(
    // TODO: Inject NotificationsRepository when available
    // private val notificationsRepository: NotificationsRepository
) : StatefulViewModel<NotificationsHubScreenUiState>(NotificationsHubScreenUiState()) {

    init {
        observeNotifications()
    }

    private fun observeNotifications() {
        viewModelScope.launch {
            update { copy(listState = UiListState.Loading) }
            
            // Simulate loading delay
            delay(500)
            
            // TODO: Replace with actual repository call
            // notificationsRepository.notifications.collectLatest { notifications ->
            //     val sections = groupNotificationsByTimePeriod(notifications)
            //     val unreadCount = notifications.count { it.isUnread }
            //     update {
            //         copy(
            //             sections = sections,
            //             unreadCount = unreadCount,
            //             listState = if (sections.isEmpty()) {
            //                 UiListState.Success(data = UiSuccessState.Empty)
            //             } else {
            //                 UiListState.Success(data = UiSuccessState.Data(data = sections))
            //             }
            //         )
            //     }
            // }
            
            // Sample data for now
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
            // TODO: Call repository to mark notification as read
            // notificationsRepository.markAsRead(notificationId)
            
            // Update local state for now
            update {
                val updatedSections = sections.map { section ->
                    section.copy(
                        notifications = section.notifications.map { notification ->
                            if (notification.id == notificationId) {
                                notification.copy(isUnread = false)
                            } else {
                                notification
                            }
                        }
                    )
                }
                val unreadCount = updatedSections.sumOf { section ->
                    section.notifications.count { it.isUnread }
                }
                copy(
                    sections = updatedSections,
                    unreadCount = unreadCount
                )
            }
        }
    }

    /**
     * Mark all notifications as read.
     */
    fun markAllAsRead() {
        viewModelScope.launch {
            // TODO: Call repository to mark all notifications as read
            // notificationsRepository.markAllAsRead()
            
            // Update local state for now
            update {
                val updatedSections = sections.map { section ->
                    section.copy(
                        notifications = section.notifications.map { it.copy(isUnread = false) }
                    )
                }
                copy(
                    sections = updatedSections,
                    unreadCount = 0
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
     * TODO: Remove when repository is available.
     */
    private fun createSampleNotifications(): List<NotificationSection> {
        return listOf(
            NotificationSection(
                title = "Today",
                notifications = listOf(
                    NotificationItem(
                        id = "1",
                        iconData = NotificationIconData(
                            iconVector = Icons.Rounded.Favorite,
                            tone = NotificationIconTone.Accent,
                        ),
                        text = "What if your next Crush is online right now?",
                        isUnread = true,
                    ),
                    NotificationItem(
                        id = "2",
                        iconData = NotificationIconData(
                            iconVector = Icons.Rounded.GridView,
                            tone = NotificationIconTone.Success,
                        ),
                        text = "Choose who you want to meet! Certified profiles, online at the same time, new profiles...",
                        isUnread = true,
                    ),
                    NotificationItem(
                        id = "3",
                        iconData = NotificationIconData(
                            iconVector = Icons.Rounded.Verified,
                            tone = NotificationIconTone.Info,
                            hasOverlay = true,
                        ),
                        text = "Target: Certified profiles! No need to compromise to make great connections",
                        isUnread = true,
                    ),
                ),
            ),
            NotificationSection(
                title = "This week",
                notifications = listOf(
                    NotificationItem(
                        id = "4",
                        iconData = NotificationIconData(
                            iconVector = Icons.Rounded.GridView,
                            tone = NotificationIconTone.Success,
                        ),
                        text = "Meet people according to your mood and your interests",
                        isUnread = true,
                    ),
                    NotificationItem(
                        id = "5",
                        iconData = NotificationIconData(
                            iconVector = Icons.Rounded.Favorite,
                            tone = NotificationIconTone.Accent,
                        ),
                        text = "Find your next Crush. Take a look at the latest happners you crossed paths with!",
                        isUnread = false,
                    ),
                    NotificationItem(
                        id = "6",
                        iconData = NotificationIconData(
                            iconVector = Icons.Rounded.Notifications,
                            tone = NotificationIconTone.Success,
                        ),
                        text = "Explore the Map and easily find the people you cross paths with. Your Crush may be right here",
                        isUnread = false,
                    ),
                    NotificationItem(
                        id = "7",
                        iconData = NotificationIconData(
                            iconVector = Icons.Rounded.Favorite,
                            tone = NotificationIconTone.Accent,
                        ),
                        text = "Your future Crush may have signed up at the same time as you 👀",
                        isUnread = false,
                    ),
                ),
            ),
        )
    }
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
 * 
 * TODO: When notification domain models are available, replace this with proper domain models.
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
