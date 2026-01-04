@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.practicalchristian.app.feature.schedules

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.DoneAll
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.primitives.SacramentCenteredColumn
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime
import kotlinx.datetime.minus
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox
import kotlin.time.Clock

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

@OptIn(ExperimentalMaterial3Api::class)
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
    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
            IconButton(onClick = onNavigateToSettings) {
                Icon(
                    modifier = Modifier
                        .height(28.dp)
                        .width(28.dp),
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "menu"
                )
            }
        }, title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "PracticalChristian",
                fontSize = 24.sp,
                fontWeight = FontWeight.Light
            )
        }, actions = {
            IconButton(
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
        })
    }, snackbarHost = {
        val message = state.error ?: state.success ?: ""
        AnimatedVisibility(visible = message.isNotBlank()) {
            Snackbar {
                Row {
                    Text(modifier = Modifier.weight(1f), text = message)
                    OutlinedButton(onClick = onRemoveAlertMessage) {
                        Text(text = "ok")
                    }
                }
            }
        }
    },
        containerColor = SacramentTheme.colors.surfaces.background,
    ) { values ->
        Column(modifier = Modifier.padding(values)) {
            when (val result = state.listState) {
                is UiListState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Warning,
                            contentDescription = "error",
                            tint = SacramentTheme.colors.semantic.error,
                            modifier = Modifier
                                .padding(bottom = spacing.padding12)
                                .width(48.dp)
                                .height(48.dp)
                        )
                        Text(
                            text = "Error",
                            color = SacramentTheme.colors.semantic.error,
                            style = SacramentTheme.typography.titleLarge,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = result.message,
                            color = SacramentTheme.colors.semantic.error,
                            style = SacramentTheme.typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(
                                top = spacing.padding8,
                                start = spacing.padding32,
                                end = spacing.padding32
                            )
                        )
                    }
                }

                UiListState.Idle -> {
                    SacramentEmptyState(
                        icon = Icons.AutoMirrored.Rounded.List,
                        title = "Welcome",
                        contentDescription = "error fetching results",
                        description = "Please wait while we're setting things up"
                    )
                }

                UiListState.Loading -> {
                    SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }

                is UiListState.Success -> {
                    when (val success = result.data) {
                        UiSuccessState.Empty -> {
                            SacramentEmptyState(
                                icon = Icons.AutoMirrored.Rounded.List,
                                title = "Empty",
                                contentDescription = "empty icon",
                                description = "You don't have a schedule.\nSetup to continue",
                                action = {
                                    Button(onClick = onNavigateToSetup) {
                                        Text(text = "Setup")
                                    }
                                }
                            )
                        }

                        is UiSuccessState.Data -> {
                            val list = success.data
                            AnimatedVisibility(visible = state.isLoading) {
                                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
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
@OptIn(ExperimentalMaterial3Api::class)
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
            IconButton(
                modifier = Modifier.padding(horizontal = spacing.padding16),
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = SacramentTheme.colors.utilities.successAction,
                    contentColor = SacramentTheme.colors.utilities.onSuccessAction
                ),
                onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Rounded.DoneAll, contentDescription = "")
            }
        }, background = Color.Transparent, onSwipe = onItemSwipeClicked
    )

    val actions = if (item.isItemInTheFuture or item.isComplete) listOf() else listOf(action)

    SwipeableActionsBox(
        modifier = modifier,
        backgroundUntilSwipeThreshold = SacramentTheme.colors.surfaces.background,
        startActions = actions,
        endActions = actions
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.padding8),
            shape = RoundedCornerShape(10),
            colors = CardDefaults.cardColors(
                containerColor = item.status.color, contentColor = Color.Black
            ),
            onClick = onItemClick
        ) {
            Column(modifier = Modifier.padding(spacing.padding36)) {
                Column {
                    Text(
                        text = item.date.asFullDayString(),
                    )
                }
                Spacer(modifier = Modifier.height(spacing.padding36))
                Column(
                    modifier = Modifier.padding()
                ) {
                    Text(
                        text = item.start.book.name.sentence,
                        fontSize = SacramentTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold
                    )
                    AnimatedVisibility(visible = item.isSameBook) {
                        Text(text = "Chapter ${item.start.chapter} - ${item.end.chapter}")
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
@OptIn(kotlin.time.ExperimentalTime::class)
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
@OptIn(kotlin.time.ExperimentalTime::class)
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
@OptIn(kotlin.time.ExperimentalTime::class)
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
@OptIn(kotlin.time.ExperimentalTime::class)
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
@OptIn(kotlin.time.ExperimentalTime::class)
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
@OptIn(kotlin.time.ExperimentalTime::class)
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
@OptIn(kotlin.time.ExperimentalTime::class)
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
