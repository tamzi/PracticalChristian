package com.practicalchristian.app.feature.notifications.hub

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.content.SacramentBadge
import com.sacrament.ui.components.content.SacramentBadgeTone
import com.sacrament.ui.components.content.SacramentListItem
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.color.SacramentColorTokens
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.foundation.shape.SacramentRadiusSize
import com.sacrament.ui.foundation.shape.SacramentShapeDefaults
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentDivider
import com.sacrament.ui.primitives.SacramentDividerTone
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentSurface
import com.sacrament.ui.primitives.SacramentText

/**
 * Notifications hub screen displaying grouped notifications.
 */
@Composable
fun NotificationsHubScreen(
    navigator: AppNavigator,
    viewModel: NotificationsHubViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NotificationsHubScreenContent(
        state = state,
        onNavigateBack = { navigator.back() },
        onNotificationClick = { notificationId ->
            viewModel.markAsRead(notificationId)
        },
    )
}

@Composable
private fun NotificationsHubScreenContent(
    state: NotificationsHubScreenUiState,
    onNavigateBack: () -> Unit,
    onNotificationClick: (String) -> Unit = {},
) {
    val spacing = SacramentTheme.spacing
    val colors = SacramentTheme.colors

    SacramentScreenScaffold(
        topBar = {
            SacramentTopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(spacing.sm),
                    ) {
                        SacramentText(
                            text = "Notifications",
                            style = SacramentTheme.typography.headlineLarge,
                        )
                        if (state.unreadCount > 0) {
                            SacramentBadge(
                                text = state.unreadCount.toString(),
                                tone = SacramentBadgeTone.Error,
                            )
                        }
                    }
                },
                navigationIcon = {
                    SacramentIconButton(
                        imageVector = SacramentIcons.SacramentIconArrowBack,
                        contentDescription = "Back",
                        onClick = onNavigateBack,
                    )
                },
            )
        },
    ) { paddingValues ->
        when (val listState = state.listState) {
            is UiListState.Loading -> {
                SacramentCenteredColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    SacramentProgressIndicator()
                }
            }

            is UiListState.Error -> {
                SacramentEmptyState(
                    icon = SacramentIcons.SacramentIconNotifications,
                    title = "Error",
                    contentDescription = "error loading notifications",
                    description = listState.message,
                )
            }

            is UiListState.Success -> {
                when (val success = listState.data) {
                    is UiSuccessState.Empty -> {
                        SacramentEmptyState(
                            icon = SacramentIcons.SacramentIconNotifications,
                            title = "No notifications",
                            contentDescription = "empty notifications",
                            description = "You're all caught up!",
                        )
                    }

                    is UiSuccessState.Data -> {
                        LazyColumn(
                            modifier = Modifier.fillMaxSize(),
                            contentPadding = paddingValues,
                        ) {
                            success.data.forEach { section ->
                                item {
                                    // Section header
                                    SacramentText(
                                        text = section.title,
                                        style = SacramentTheme.typography.titleSmall,
                                        color = colors.text.muted,
                                        modifier = Modifier.padding(
                                            start = spacing.padding16,
                                            top = spacing.padding20,
                                            bottom = spacing.padding8,
                                        ),
                                    )
                                }

                                itemsIndexed(
                                    items = section.notifications,
                                    key = { _, notification -> notification.id }
                                ) { index, notification ->
                                    NotificationItemRow(
                                        notification = notification,
                                        onClick = { onNotificationClick(notification.id) },
                                        showDivider = index < section.notifications.lastIndex,
                                        modifier = Modifier.fillMaxWidth(),
                                    )
                                }
                            }
                        }
                    }
                }
            }

            UiListState.Idle -> {
                // Initial state, will transition to Loading
            }
        }
    }
}

@Composable
private fun NotificationItemRow(
    notification: NotificationItem,
    onClick: () -> Unit = {},
    showDivider: Boolean = false,
    modifier: Modifier = Modifier,
) {
    val spacing = SacramentTheme.spacing
    val colors = SacramentTheme.colors
    val dividerIndent = spacing.lg + 48.dp + spacing.md

    Column(
        modifier = modifier,
    ) {
        SacramentListItem(
            headline = {
                SacramentText(
                    text = notification.text,
                    style = SacramentTheme.typography.bodyLarge,
                    color = colors.text.strong,
                )
            },
            leading = { NotificationIcon(iconData = notification.iconData) },
            trailing = {
                if (notification.isUnread) {
                    SacramentSurface(
                        modifier = Modifier.size(10.dp),
                        color = colors.semantic.error,
                        shape = CircleShape,
                    ) {}
                }
            },
            onClick = onClick,
        )

        if (showDivider) {
            SacramentDivider(
                modifier = Modifier
                    .padding(start = dividerIndent, top = spacing.padding12),
                tone = SacramentDividerTone.Background,
            )
        }
    }
}

@Composable
private fun NotificationIcon(
    iconData: NotificationIconData,
    modifier: Modifier = Modifier,
) {
    val colors = SacramentTheme.colors

    SacramentSurface(
        modifier = modifier.size(48.dp),
        color = colors.surfaces.surfaceVariant,
        shape = SacramentShapeDefaults.rounded(SacramentRadiusSize.LG),
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Box {
                SacramentIcon(
                    imageVector = iconData.iconVector,
                    contentDescription = null,
                    tint = notificationIconTint(iconData.tone, colors),
                    size = 24.dp,
                )
                // Overlay indicator if needed
                if (iconData.hasOverlay) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(10.dp)
                            .background(
                                colors.semantic.error,
                                CircleShape
                            ),
                    )
                }
            }
        }
    }
}

private fun notificationIconTint(
    tone: NotificationIconTone,
    colors: SacramentColorTokens,
): Color = when (tone) {
    NotificationIconTone.Accent -> colors.brand.secondary
    NotificationIconTone.Success -> colors.semantic.success
    NotificationIconTone.Info -> colors.semantic.info
}

@Preview(showBackground = true)
@Composable
@Suppress("UnusedPrivateMember")
private fun NotificationsHubScreenPreview() {
    SacramentTheme(navigationBar = Bar.BACKGROUND, statusBar = Bar.BACKGROUND) {
        NotificationsHubScreenContent(
            state = NotificationsHubScreenUiState(
                sections = listOf(
                    NotificationSection(
                        title = "Today",
                        notifications = listOf(
                            NotificationItem(
                                id = "1",
                                iconData = NotificationIconData(
                                        iconVector = SacramentIcons.SacramentIconFavorite,
                                        tone = NotificationIconTone.Accent,
                                    ),
                                    text = "Time for your morning prayer. Start your day with " +
                                        "gratitude and reflection",
                                    isUnread = true,
                                ),
                            ),
                    ),
                ),
                unreadCount = 2,
                listState = UiListState.Success(
                    data = UiSuccessState.Data(
                        data = listOf(
                            NotificationSection(
                                title = "Today",
                                notifications = listOf(
                                    NotificationItem(
                                        id = "1",
                                        iconData = NotificationIconData(
                                        iconVector = SacramentIcons.SacramentIconFavorite,
                                        tone = NotificationIconTone.Accent,
                                    ),
                                    text = "Time for your morning prayer. Start your day with " +
                                        "gratitude and reflection",
                                    isUnread = true,
                                ),
                                ),
                            ),
                        )
                    )
                ),
            ),
            onNavigateBack = {},
        )
    }
}
