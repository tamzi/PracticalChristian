package com.practicalchristian.app.feature.home.home

import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.practicalchristian.app.core.ui.components.BottomNavigationBar
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.practicalchristian.app.core.ui.navigation.NavigationEvent
import com.sacrament.ui.R
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import kotlinx.datetime.LocalDate

/**
 * Modern Home landing screen for PracticalChristian Bible reading app.
 * Features daily schedule, devotions, and reading goals.
 */
@Composable
fun HomeScreen(
    navigator: AppNavigator,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val activity = LocalActivity.current
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { event ->
            when (event) {
                is NavigationEvent.ReplaceWith -> navigator.replaceWith(event.destination)
                is NavigationEvent.NavigateTo -> navigator.navigate(event.destination)
                NavigationEvent.NavigateBack -> navigator.back()
            }
        }
    }

    HomeScreenContent(
        state = state,
        onNavigateToProfile = viewModel::navigateToProfile,
        onNavigateToSchedules = viewModel::navigateToSchedules,
        onNavigateToBooks = viewModel::navigateToBooks,
        onNavigateToNotes = viewModel::navigateToNotes,
        onNavigateToSettings = viewModel::navigateToSettings,
        onDaySelected = viewModel::onDaySelected,
        navigateBack = { activity?.finish() }
    )
}

@Composable
fun HomeScreenContent(
    state: HomeScreenUiState,
    onNavigateToProfile: () -> Unit = {},
    onNavigateToSchedules: () -> Unit = {},
    onNavigateToBooks: () -> Unit = {},
    onNavigateToNotes: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    onDaySelected: (LocalDate) -> Unit = {},
    navigateBack: () -> Unit = {},
) {
    BackHandler {
        navigateBack()
    }
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onHomeClick = { /* Already on home */ },
                onBooksClick = onNavigateToBooks,
                onNotesClick = onNavigateToNotes
            )
        },
        containerColor = SacramentTheme.colors.surfaces.background,
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(SacramentTheme.colors.surfaces.background)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = spacing.padding20)
        ) {
            Spacer(modifier = Modifier.height(spacing.padding16))

            // Header
            HeaderSection(
                userName = state.userName,
                profilePictureUri = state.profilePictureUri,
                onProfileClick = onNavigateToProfile,
                onNotificationClick = { /* TODO */ }
            )

            Spacer(modifier = Modifier.height(spacing.padding24))

            // Title
            Text(
                text = "Your Daily\nBible Reading",
                style = SacramentTheme.typography.displaySmall,
                fontWeight = FontWeight.Bold,
                lineHeight = 44.sp
            )

            Spacer(modifier = Modifier.height(spacing.padding24))

            // Week Calendar
            WeekCalendarSection(
                selectedDate = state.selectedDate,
                weekDays = state.weekDays,
                onDaySelected = onDaySelected
            )

            Spacer(modifier = Modifier.height(spacing.padding24))

            // Your Progress Section
            YourProgressSection(
                daysStreak = state.daysStreak,
                chaptersCompleted = state.chaptersCompleted
            )

            Spacer(modifier = Modifier.height(spacing.padding24))

            // Current Reading Section (if available)
            state.currentReading?.let { reading ->
                CurrentReadingSection(
                    reading = reading,
                    onContinueReading = onNavigateToSchedules
                )
                Spacer(modifier = Modifier.height(spacing.padding24))
            }

            // Begin Reading Card
            BeginReadingCard(
                onStartReading = onNavigateToSchedules
            )

            Spacer(modifier = Modifier.height(spacing.padding20))

            // Devotion Cards - Swipeable
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(spacing.padding12)
            ) {
                item {
                    DevotionCard(
                        title = "Morning Devotion",
                        subtitle = "Start your day",
                        emoji = "☀️",
                        backgroundColor = colors.surfaces.sunlight,
                        modifier = Modifier.width(280.dp),
                        onClick = onNavigateToSchedules
                    )
                }
                item {
                    DevotionCard(
                        title = "Evening Reflection",
                        subtitle = "Reflect and unwind",
                        emoji = "🌙",
                        backgroundColor = colors.surfaces.lavender,
                        modifier = Modifier.width(280.dp),
                        onClick = onNavigateToSchedules
                    )
                }
                item {
                    DevotionCard(
                        title = "Verse of the Day",
                        subtitle = "Daily inspiration",
                        emoji = "✨",
                        backgroundColor = colors.surfaces.rose,
                        modifier = Modifier.width(280.dp),
                        onClick = onNavigateToSchedules
                    )
                }
                item {
                    DevotionCard(
                        title = "Devotion of the Day",
                        subtitle = "Guided reflection",
                        emoji = "🙏",
                        backgroundColor = colors.surfaces.sky,
                        modifier = Modifier.width(280.dp),
                        onClick = onNavigateToSchedules
                    )
                }
            }

            Spacer(modifier = Modifier.height(spacing.padding24))

            // Today's Goal Section
            TodaysGoalSection(
                onSeeAllClick = onNavigateToSchedules
            )

            Spacer(modifier = Modifier.height(spacing.padding24))

            // Plans Section
            if (state.availablePlans.isNotEmpty()) {
                PlansSection(
                    plans = state.availablePlans,
                    onPlanClick = { /* TODO: Navigate to plan details */ },
                    onViewAllClick = { /* TODO: Navigate to plans list */ }
                )
                Spacer(modifier = Modifier.height(spacing.padding24))
            }
        }
    }
}

@Composable
private fun HeaderSection(
    userName: String,
    profilePictureUri: String?,
    onProfileClick: () -> Unit,
    onNotificationClick: () -> Unit
) {
    val spacing = SacramentTheme.spacing
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(spacing.padding12)
        ) {
            // Profile Picture
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .border(2.dp, SacramentTheme.colors.brand.primary, CircleShape)
                    .clickable { onProfileClick() }
            ) {
                if (profilePictureUri != null) {
                    AsyncImage(
                        contentDescription = "profile picture",
                        contentScale = ContentScale.Crop,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(profilePictureUri)
                            .crossfade(true)
                            .build(),
                        modifier = Modifier.fillMaxSize()
                    )
                } else {
                    AsyncImage(
                        contentDescription = "profile picture placeholder",
                        contentScale = ContentScale.Crop,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(R.drawable.sacrament_profile_placeholder)
                            .crossfade(true)
                            .build(),
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Column {
                Text(
                    text = "PracticalChristian",
                    style = SacramentTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Hello ${userName}",
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.text.muted
                )
            }
        }

        Row(horizontalArrangement = Arrangement.spacedBy(spacing.padding8)) {
            IconButton(
                onClick = onNotificationClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = "Notifications",
                    tint = SacramentTheme.colors.text.strong
                )
            }
        }
    }
}

@Composable
private fun WeekCalendarSection(
    selectedDate: LocalDate,
    weekDays: List<DayCalendar>,
    onDaySelected: (LocalDate) -> Unit
) {
    val spacing = SacramentTheme.spacing
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(spacing.padding12)
    ) {
        items(weekDays) { day ->
            DayItem(
                day = day,
                isSelected = day.date == selectedDate,
                onDaySelected = { onDaySelected(day.date) }
            )
        }
    }
}

@Composable
private fun DayItem(
    day: DayCalendar,
    isSelected: Boolean,
    onDaySelected: () -> Unit
) {
    val spacing = SacramentTheme.spacing
    val backgroundColor = if (isSelected) {
        SacramentTheme.colors.brand.primary
    } else {
        SacramentTheme.colors.surfaces.surfaceVariant
    }

    val contentColor = if (isSelected) {
        SacramentTheme.colors.text.onBrand
    } else {
        SacramentTheme.colors.text.muted
    }

    Box(
        modifier = Modifier
            .width(64.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .clickable { onDaySelected() }
            .padding(vertical = spacing.padding12),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing.padding4)
        ) {
            Text(
                text = day.dayOfWeek,
                style = SacramentTheme.typography.bodySmall,
                color = contentColor.copy(alpha = 0.7f),
                fontWeight = FontWeight.Medium
            )
            Text(
                text = day.dayOfMonth.toString(),
                style = SacramentTheme.typography.titleLarge,
                color = contentColor,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun BeginReadingCard(
    onStartReading: () -> Unit
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surfaces.lavender
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.padding20)
        ) {
            Column(
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Text(
                    text = "Let's Begin Today's Reading",
                    style = SacramentTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = colors.text.strong
                )
                Spacer(modifier = Modifier.height(spacing.padding8))
                Text(
                    text = "Every word holds wisdom—let's\nbegin your journey within.",
                    style = SacramentTheme.typography.bodyMedium,
                    color = colors.text.muted,
                    lineHeight = 20.sp
                )
            }

            Button(
                onClick = onStartReading,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .height(48.dp),
                shape = RoundedCornerShape(24.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = SacramentTheme.colors.brand.primary
                )
            ) {
                Text(
                    text = "Start Reading",
                    style = SacramentTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun DevotionCard(
    title: String,
    subtitle: String,
    emoji: String,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    Card(
        modifier = modifier
            .height(160.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.padding16),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = emoji,
                fontSize = 48.sp,
                modifier = Modifier.padding(top = spacing.padding8)
            )
            Column {
                Text(
                    text = title,
                    style = SacramentTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = colors.text.strong
                )
                Text(
                    text = subtitle,
                    style = SacramentTheme.typography.bodySmall,
                    color = colors.text.muted
                )
            }
        }
    }
}

@Composable
private fun TodaysGoalSection(
    onSeeAllClick: () -> Unit
) {
    val spacing = SacramentTheme.spacing
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Today's Goal",
                style = SacramentTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = onSeeAllClick) {
                Text(
                    text = "See all",
                    style = SacramentTheme.typography.bodyMedium,
                    color = SacramentTheme.colors.brand.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.padding12))

        // Goal Items
        GoalItem(
            emoji = "📖",
            title = "Read today's scripture",
            onClick = onSeeAllClick
        )

        Spacer(modifier = Modifier.height(spacing.padding8))

        GoalItem(
            emoji = "✍️",
            title = "Journal your reflection",
            onClick = onSeeAllClick
        )
    }
}

@Composable
private fun GoalItem(
    emoji: String,
    title: String,
    onClick: () -> Unit
) {
    val spacing = SacramentTheme.spacing
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(72.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = SacramentTheme.colors.surfaces.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp,
            pressedElevation = 1.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = spacing.padding16),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(spacing.padding12)
            ) {
                Text(
                    text = emoji,
                    fontSize = 28.sp
                )
                Text(
                    text = title,
                    style = SacramentTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Medium
                )
            }
            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = "Go",
                tint = SacramentTheme.colors.text.muted
            )
        }
    }
}

// Bottom navigation bar is imported from components package

@Composable
private fun YourProgressSection(
    daysStreak: Int,
    chaptersCompleted: Int
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Your Progress",
                style = SacramentTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(spacing.padding16))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.padding12)
        ) {
            // Days Streak Card
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surfaces.peach
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spacing.padding16),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🔥",
                        fontSize = 40.sp
                    )
                    Spacer(modifier = Modifier.height(spacing.padding8))
                    Text(
                        text = daysStreak.toString().padStart(2, '0'),
                        style = SacramentTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = colors.brand.primary
                    )
                    Text(
                        text = "Days Streak",
                        style = SacramentTheme.typography.bodySmall,
                        color = colors.text.muted
                    )
                }
            }

            // Chapters Card
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(120.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = colors.surfaces.mint
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 2.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(spacing.padding16),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "📖",
                        fontSize = 40.sp
                    )
                    Spacer(modifier = Modifier.height(spacing.padding8))
                    Text(
                        text = chaptersCompleted.toString(),
                        style = SacramentTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        color = colors.brand.primary
                    )
                    Text(
                        text = "Chapters",
                        style = SacramentTheme.typography.bodySmall,
                        color = colors.text.muted
                    )
                }
            }
        }
    }
}

@Composable
private fun CurrentReadingSection(
    reading: ReadingProgress,
    onContinueReading: () -> Unit
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surfaces.lavenderSoft
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp,
            pressedElevation = 2.dp
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(spacing.padding20)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Matthew Chapter ${reading.bookName.substringAfter(" ")}",
                        style = SacramentTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold,
                        color = colors.text.strong
                    )
                    Spacer(modifier = Modifier.height(spacing.padding4))
                    Text(
                        text = "Chapters ${reading.chaptersCompleted} of ${reading.totalChapters} Finished",
                        style = SacramentTheme.typography.bodyMedium,
                        color = colors.text.muted
                    )
                    Spacer(modifier = Modifier.height(spacing.padding12))

                    // Partner avatars
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(-spacing.padding8)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(colors.brand.primary)
                        )
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(colors.brand.secondary)
                        )
                    }
                }

                // Circular Progress
                Box(
                    modifier = Modifier.size(90.dp),
                    contentAlignment = Alignment.Center
                ) {
                    androidx.compose.material3.CircularProgressIndicator(
                        progress = { reading.progressPercentage },
                        modifier = Modifier.size(90.dp),
                        color = colors.brand.primary,
                        strokeWidth = 8.dp,
                        trackColor = colors.utilities.progressTrack
                    )
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${(reading.progressPercentage * 100).toInt()}%",
                            style = SacramentTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = colors.brand.primary
                        )
                        Text(
                            text = "done",
                            style = SacramentTheme.typography.bodySmall,
                            color = colors.text.muted
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(spacing.padding16))

            // Continue Reading Button
        Button(
            onClick = onContinueReading,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colors.brand.primary
            )
        ) {
            Text(
                text = "Continue Reading",
                style = SacramentTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = colors.text.onBrand
            )
            }
        }
    }
}

@Composable
private fun PlansSection(
    plans: List<ReadingPlan>,
    onPlanClick: (ReadingPlan) -> Unit,
    onViewAllClick: () -> Unit
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Plans only for you",
                style = SacramentTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            TextButton(onClick = onViewAllClick) {
                Text(
                    text = "View All",
                    style = SacramentTheme.typography.bodyMedium,
                    color = colors.brand.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(spacing.padding12))

        Column(
            verticalArrangement = Arrangement.spacedBy(spacing.padding12)
        ) {
            plans.forEach { plan ->
                PlanCard(
                    plan = plan,
                    onClick = { onPlanClick(plan) }
                )
            }
        }
    }
}

@Composable
private fun PlanCard(
    plan: ReadingPlan,
    onClick: () -> Unit
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.surfaces.sunlightSoft
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp,
            pressedElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.padding16),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = colors.surfaces.peachSoft,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = spacing.padding12, vertical = spacing.padding6)
                ) {
                    Text(
                        text = "${plan.daysCount} days plan",
                        style = SacramentTheme.typography.labelMedium,
                        color = colors.brand.tertiary,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                Spacer(modifier = Modifier.height(spacing.padding8))

                Text(
                    text = plan.title,
                    style = SacramentTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = colors.text.strong,
                    lineHeight = 22.sp
                )

                Spacer(modifier = Modifier.height(spacing.padding8))

                Text(
                    text = "Start this Plan",
                    style = SacramentTheme.typography.bodyMedium,
                    color = colors.brand.primary,
                    fontWeight = FontWeight.Medium
                )
            }

            // Illustration placeholder
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors.surfaces.roseTint),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📚",
                    fontSize = 48.sp
                )
            }
        }
    }
}

// Preview
@Preview(showBackground = true, name = "Home Screen - Full")
@Composable
fun HomeScreenPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        HomeScreenContent(
            state = HomeScreenUiState(
                userName = "Mark",
                daysStreak = 7,
                chaptersCompleted = 24,
                weekDays = listOf(
                    DayCalendar(
                        date = LocalDate(2025, 1, 20),
                        dayOfWeek = "Mon",
                        dayOfMonth = 20
                    ),
                    DayCalendar(
                        date = LocalDate(2025, 1, 21),
                        dayOfWeek = "Tue",
                        dayOfMonth = 21
                    ),
                    DayCalendar(
                        date = LocalDate(2025, 1, 22),
                        dayOfWeek = "Wed",
                        dayOfMonth = 22
                    ),
                    DayCalendar(
                        date = LocalDate(2025, 1, 23),
                        dayOfWeek = "Thu",
                        dayOfMonth = 23
                    ),
                    DayCalendar(
                        date = LocalDate(2025, 1, 24),
                        dayOfWeek = "Fri",
                        dayOfMonth = 24
                    ),
                    DayCalendar(
                        date = LocalDate(2025, 1, 25),
                        dayOfWeek = "Sat",
                        dayOfMonth = 25
                    ),
                    DayCalendar(
                        date = LocalDate(2025, 1, 26),
                        dayOfWeek = "Sun",
                        dayOfMonth = 26
                    )
                ),
                selectedDate = LocalDate(2025, 1, 23),
                currentReading = ReadingProgress(
                    bookName = "Matthew 5",
                    chaptersCompleted = 3,
                    totalChapters = 5,
                    progressPercentage = 0.75f
                ),
                availablePlans = listOf(
                    ReadingPlan(
                        id = 1,
                        title = "Overcoming Anxiety Through God's Promises",
                        description = "Find peace and strength through scripture",
                        daysCount = 14
                    ),
                    ReadingPlan(
                        id = 2,
                        title = "Become you own Spiritual Guide by god's helps",
                        description = "Deepen your relationship with God",
                        daysCount = 14
                    )
                )
            )
        )
    }
}

@Preview(showBackground = true, name = "Progress Section Only")
@Composable
fun YourProgressSectionPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val spacing = SacramentTheme.spacing
        Box(modifier = Modifier.padding(spacing.padding20)) {
            YourProgressSection(
                daysStreak = 7,
                chaptersCompleted = 24
            )
        }
    }
}

@Preview(showBackground = true, name = "Current Reading Section")
@Composable
fun CurrentReadingSectionPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val spacing = SacramentTheme.spacing
        Box(modifier = Modifier.padding(spacing.padding20)) {
            CurrentReadingSection(
                reading = ReadingProgress(
                    bookName = "Matthew 5",
                    chaptersCompleted = 3,
                    totalChapters = 5,
                    progressPercentage = 0.75f
                ),
                onContinueReading = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Plans Section")
@Composable
fun PlansSectionPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val spacing = SacramentTheme.spacing
        Box(modifier = Modifier.padding(spacing.padding20)) {
            PlansSection(
                plans = listOf(
                    ReadingPlan(
                        id = 1,
                        title = "Overcoming Anxiety Through God's Promises",
                        description = "Find peace and strength through scripture",
                        daysCount = 14
                    ),
                    ReadingPlan(
                        id = 2,
                        title = "Become you own Spiritual Guide by god's helps",
                        description = "Deepen your relationship with God",
                        daysCount = 14
                    )
                ),
                onPlanClick = {},
                onViewAllClick = {}
            )
        }
    }
}

@Preview(showBackground = true, name = "Single Plan Card")
@Composable
fun PlanCardPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        val spacing = SacramentTheme.spacing
        Box(modifier = Modifier.padding(spacing.padding20)) {
            PlanCard(
                plan = ReadingPlan(
                    id = 1,
                    title = "Overcoming Anxiety Through God's Promises",
                    description = "Find peace and strength through scripture",
                    daysCount = 14
                ),
                onClick = {}
            )
        }
    }
}
