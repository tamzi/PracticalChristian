@file:OptIn(ExperimentalTime::class)

package com.practicalchristian.app.feature.schedules.hub

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.ScheduleDomain
import com.practicalchristian.app.core.domain.models.ScheduleEntry
import com.practicalchristian.app.core.domain.models.ScheduleItem
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.helpers.asFullDayString
import com.practicalchristian.app.core.ui.helpers.color
import com.practicalchristian.app.core.ui.helpers.sentence
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.R
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.components.surface.SacramentCardColors
import com.sacrament.ui.components.surface.SacramentCardDefaults
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

/**
 * Schedules screen - List of schedules.
 */
@Composable
fun SchedulesScreen(
    navigator: AppNavigator,
    viewModel: SchedulesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ScheduleScreenContent(
        state = state,
        onNavigateToSettings = { navigator.navigate(AppDestination.Settings) },
        onNavigateToProfile = { navigator.navigate(AppDestination.Profile) },
        onNavigateToSetup = { navigator.navigate(AppDestination.Setup) },
        onNavigateToScheduleDetails = { scheduleId ->
            navigator.navigate(AppDestination.ScheduleDetails(scheduleId = scheduleId))
        },
        onItemSwiped = viewModel::onItemSwiped,
        onRemoveAlertMessage = {
            viewModel.updateError()
            viewModel.updateSuccess()
        }
    )
}

@Composable
fun ScheduleScreenContent(
    state: SchedulesScreenUiState,
    onNavigateToSettings: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToSetup: () -> Unit,
    onNavigateToScheduleDetails: (Int) -> Unit,
    onItemSwiped: (ScheduleDomain) -> Unit,
    onRemoveAlertMessage: () -> Unit,
) {
    val spacing = SacramentTheme.spacing
    SacramentScreenScaffold(topBar = {
        SacramentTopAppBar(
            navigationIcon = {
                SacramentIconButton(
                    imageVector = SacramentIcons.Menu,
                    contentDescription = "menu",
                    onClick = onNavigateToSettings,
                    modifier = Modifier
                        .height(28.dp)
                        .width(28.dp)
                )
            },
            title = {
                SacramentText(
                    text = "PracticalChristian",
                    style = SacramentTheme.typography.titleLarge.copy(fontSize = 24.sp, fontWeight = FontWeight.Light),
                    modifier = Modifier.fillMaxWidth()
                )
            },
            actions = {
                androidx.compose.foundation.clickable(
                    onClick = onNavigateToProfile,
                    modifier = Modifier.padding(end = spacing.padding16)
                ) {
                    if (state.profilePictureUri != null) {
                        AsyncImage(
                            contentDescription = "profile picture",
                            contentScale = ContentScale.Crop,
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(state.profilePictureUri).crossfade(true).build(),
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .border(2.dp, SacramentTheme.colors.text.strong, CircleShape)
                        )
                    } else {
                        AsyncImage(
                            contentDescription = "profile picture placeholder",
                            contentScale = ContentScale.Crop,
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(R.drawable.sacrament_profile_placeholder).crossfade(true).build(),
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                                .border(2.dp, SacramentTheme.colors.text.strong, CircleShape)
                        )
                    }
                }
            }
        )
    } { values ->
        Column(modifier = padding(values)) {
            when (val result = state.listState) {
                is UiListState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SacramentIcon(
                            imageVector = SacramentIcons.Warning,
                            contentDescription = "error",
                            tint = SacramentTheme.colors.semantic.error,
                            modifier = Modifier
                                .padding(bottom = spacing.padding12)
                                .width(48.dp)
                                .height(48.dp)
                        )
                        SacramentText(
                            text = "Error",
                            color = SacramentTheme.colors.semantic.error,
                            style = SacramentTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth()
                        )
                        SacramentText(
                            text = result.message,
                            color = SacramentTheme.colors.semantic.error,
                            style = SacramentTheme.typography.bodyLarge,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = spacing.padding8,
                                    start = spacing.padding32,
                                    end = spacing.padding32
                                )
                        )
                    }
                }

                UiListState.Idle -> {
                    SacramentEmptyState(
                        icon = SacramentIcons.List,
                        title = "Welcome",
                        contentDescription = "error fetching results",
                        description = "Please wait while we're setting things up"
                    )
                }

                UiListState.Loading -> {
                    SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                        SacramentProgressIndicator(variant = SacramentProgressVariant.Circular)
                    }
                }

                is UiListState.Success -> {
                    when (val success = result.data) {
                        UiSuccessState.Empty -> {
                            SacramentEmptyState(
                                icon = SacramentIcons.List,
                                title = "Empty",
                                contentDescription = "empty icon",
                                description = "You don't have a schedule.\nSetup to continue",
                                action = {
                                    SacramentButton(
                                        text = "Setup",
                                        onClick = onNavigateToSetup
                                    )
                                }
                            )
                        }

                        is UiSuccessState.Data -> {
                            val list = success.data
                            AnimatedVisibility(visible = state.isLoading) {
                                SacramentProgressIndicator(
                                    variant = SacramentProgressVariant.Linear,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            LazyColumn {
                                itemsIndexed(list) { index, item ->
                                    ScheduleItem(
                                        item = item,
                                        isFirst = index == 0,
                                        isLast = index == list.lastIndex,
                                        onItemClick = {
                                            onNavigateToScheduleDetails(item.id)
                                        },
                                        onItemSwipeClicked = {
                                            onItemSwiped.invoke(item)
                                        })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ScheduleItem(
    item: ScheduleDomain,
    isFirst: Boolean,
    isLast: Boolean,
    modifier: Modifier = Modifier,
    onItemClick: () -> Unit = {},
    onItemSwipeClicked: () -> Unit,
) {
    val spacing = SacramentTheme.spacing
    val action = SwipeAction(
        icon = {
            SacramentIconButton(
                imageVector = SacramentIcons.DoneAll,
                contentDescription = "",
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(horizontal = spacing.padding16)
            )
        }, background = Color.Transparent, onSwipe = onItemSwipeClicked
    )

    val actions = if (item.isItemInTheFuture or item.isComplete) listOf() else listOf(action)

    SwipeableActionsBox(
        modifier = modifier,
        backgroundUntilSwipeThreshold = SacramentTheme.colors.surfaces.background,
        startActions = actions,
        endActions = actions
    ) {
        SacramentCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.padding8),
            colors = SacramentCardColors(
                container = item.status.color,
                border = SacramentCardDefaults.colors().border
            ),
            onClick = onItemClick,
            contentPadding = PaddingValues(spacing.padding36)
        ) {
            Column {
                SacramentText(
                    text = item.date.asFullDayString(),
                    style = SacramentTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(spacing.padding36))
                Column {
                    SacramentText(
                        text = item.start.book.name.sentence,
                        style = SacramentTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    AnimatedVisibility(visible = item.isSameBook) {
                        SacramentText(
                            text = "Chapter ${item.start.chapter} - ${item.end.chapter}",
                            style = SacramentTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "SchedulesScreen - Loading")
@Composable
private fun SchedulesScreenLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = SchedulesScreenUiState(
                listState = UiListState.Loading
            ),
            onNavigateToSettings = {},
            onNavigateToProfile = {},
            onNavigateToSetup = {},
            onNavigateToScheduleDetails = {},
            onItemSwiped = {},
            onRemoveAlertMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "SchedulesScreen - Empty")
@Composable
private fun SchedulesScreenEmptyPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = SchedulesScreenUiState(
                listState = UiListState.Success(UiSuccessState.Empty)
            ),
            onNavigateToSettings = {},
            onNavigateToProfile = {},
            onNavigateToSetup = {},
            onNavigateToScheduleDetails = {},
            onItemSwiped = {},
            onRemoveAlertMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "SchedulesScreen - Error")
@Composable
private fun SchedulesScreenErrorPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = SchedulesScreenUiState(
                listState = UiListState.Error("Unable to load schedule data")
            ),
            onNavigateToSettings = {},
            onNavigateToProfile = {},
            onNavigateToSetup = {},
            onNavigateToScheduleDetails = {},
            onItemSwiped = {},
            onRemoveAlertMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "SchedulesScreen - With Schedules")
@Composable
@OptIn(ExperimentalTime::class)
private fun SchedulesScreenWithDataPreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val sampleSchedules = listOf(
        ScheduleDomain(
            id = 1,
            date = now.date.minus(1, DateTimeUnit.DAY).atTime(12, 0),
            start = ScheduleItem(Book(1, "Genesis", 50), 1),
            end = ScheduleItem(Book(1, "Genesis", 50), 3),
            entry = ScheduleEntry("entry1", 1, now.date.minus(1, DateTimeUnit.DAY).atTime(12, 0))
        ), ScheduleDomain(
            id = 2,
            date = now,
            start = ScheduleItem(Book(1, "Genesis", 50), 4),
            end = ScheduleItem(Book(1, "Genesis", 50), 6),
            entry = null
        ), ScheduleDomain(
            id = 3,
            date = now.date.plus(1, DateTimeUnit.DAY).atTime(12, 0),
            start = ScheduleItem(Book(2, "Exodus", 40), 1),
            end = ScheduleItem(Book(2, "Exodus", 40), 2),
            entry = null
        )
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = SchedulesScreenUiState(
                listState = UiListState.Success(UiSuccessState.Data(sampleSchedules))
            ),
            onNavigateToSettings = {},
            onNavigateToProfile = {},
            onNavigateToSetup = {},
            onNavigateToScheduleDetails = {},
            onItemSwiped = {},
            onRemoveAlertMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "SchedulesScreen - With Loading Indicator")
@Composable
@OptIn(ExperimentalTime::class)
private fun SchedulesScreenWithLoadingIndicatorPreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val sampleSchedules = listOf(
        ScheduleDomain(
            id = 1,
            date = now,
            start = ScheduleItem(Book(1, "Genesis", 50), 1),
            end = ScheduleItem(Book(1, "Genesis", 50), 3),
            entry = null
        )
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = SchedulesScreenUiState(
                isLoading = true, listState = UiListState.Success(UiSuccessState.Data(sampleSchedules))
            ),
            onNavigateToSettings = {},
            onNavigateToProfile = {},
            onNavigateToSetup = {},
            onNavigateToScheduleDetails = {},
            onItemSwiped = {},
            onRemoveAlertMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "SchedulesScreen - With Error Message")
@Composable
@OptIn(ExperimentalTime::class)
private fun SchedulesScreenWithErrorMessagePreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val sampleSchedules = listOf(
        ScheduleDomain(
            id = 1,
            date = now,
            start = ScheduleItem(Book(1, "Genesis", 50), 1),
            end = ScheduleItem(Book(1, "Genesis", 50), 3),
            entry = null
        )
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = SchedulesScreenUiState(
                error = "Something went wrong",
                listState = UiListState.Success(UiSuccessState.Data(sampleSchedules))
            ),
            onNavigateToSettings = {},
            onNavigateToProfile = {},
            onNavigateToSetup = {},
            onNavigateToScheduleDetails = {},
            onItemSwiped = {},
            onRemoveAlertMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "SchedulesScreen - With Success Message")
@Composable
@OptIn(ExperimentalTime::class)
private fun SchedulesScreenWithSuccessMessagePreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val sampleSchedules = listOf(
        ScheduleDomain(
            id = 1,
            date = now,
            start = ScheduleItem(Book(1, "Genesis", 50), 1),
            end = ScheduleItem(Book(1, "Genesis", 50), 3),
            entry = null
        )
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = SchedulesScreenUiState(
                success = "Schedule completed successfully!",
                listState = UiListState.Success(UiSuccessState.Data(sampleSchedules))
            ),
            onNavigateToSettings = {},
            onNavigateToProfile = {},
            onNavigateToSetup = {},
            onNavigateToScheduleDetails = {},
            onItemSwiped = {},
            onRemoveAlertMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "SchedulesScreen - Idle State")
@Composable
private fun SchedulesScreenIdlePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleScreenContent(
            state = SchedulesScreenUiState(
                listState = UiListState.Idle
            ),
            onNavigateToSettings = {},
            onNavigateToProfile = {},
            onNavigateToSetup = {},
            onNavigateToScheduleDetails = {},
            onItemSwiped = {},
            onRemoveAlertMessage = {}
        )
    }
}

@Preview(showBackground = true, name = "ScheduleItem - Completed")
@Composable
@OptIn(ExperimentalTime::class)
private fun ScheduleItemCompletedPreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleItem(
            item = ScheduleDomain(
                id = 1,
                date = now.date.minus(1, DateTimeUnit.DAY).atTime(12, 0),
                start = ScheduleItem(Book(1, "Genesis", 50), 1),
                end = ScheduleItem(Book(1, "Genesis", 50), 3),
                entry = ScheduleEntry("entry1", 1, now.date.minus(1, DateTimeUnit.DAY).atTime(12, 0))
            ), isFirst = true, isLast = false, onItemClick = {}, onItemSwipeClicked = {}
        )
    }
}

@Preview(showBackground = true, name = "ScheduleItem - Pending")
@Composable
@OptIn(ExperimentalTime::class)
private fun ScheduleItemPendingPreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleItem(
            item = ScheduleDomain(
                id = 2,
                date = now,
                start = ScheduleItem(Book(1, "Genesis", 50), 4),
                end = ScheduleItem(Book(1, "Genesis", 50), 6),
                entry = null
            ), isFirst = false, isLast = false, onItemClick = {}, onItemSwipeClicked = {}
        )
    }
}

@Preview(showBackground = true, name = "ScheduleItem - Future")
@Composable
@OptIn(ExperimentalTime::class)
private fun ScheduleItemFuturePreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ScheduleItem(
            item = ScheduleDomain(
                id = 3,
                date = now.date.plus(1, DateTimeUnit.DAY).atTime(12, 0),
                start = ScheduleItem(Book(2, "Exodus", 40), 1),
                end = ScheduleItem(Book(2, "Exodus", 40), 2),
                entry = null
            ), isFirst = false, isLast = true, onItemClick = {}, onItemSwipeClicked = {}
        )
    }
}
