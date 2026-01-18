package com.practicalchristian.app.feature.onboarding

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentText
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    navigator: AppNavigator,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.shouldNavigateToSetup, state.shouldNavigateToHome) {
        when {
            state.shouldNavigateToSetup -> {
                navigator.replaceWith(AppDestination.Setup)
                viewModel.onNavigationComplete()
            }
            state.shouldNavigateToHome -> {
                navigator.replaceWith(AppDestination.Home)
                viewModel.onNavigationComplete()
            }
        }
    }

    OnboardingScreenContent(
        isLoading = state.isLoading,
        onCreateAccount = { navigator.navigate(AppDestination.Authentication) }
    )
}

private data class OnboardingPage(
    val title: String,
    val description: String,
    val accentColor: Color,
    @param:DrawableRes val imageRes: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreenContent(
    isLoading: Boolean,
    onCreateAccount: () -> Unit
) {
    val colors = SacramentTheme.colors
    val spacing = SacramentTheme.spacing
    val pages = remember(colors) {
        listOf(
            OnboardingPage(
                title = "Unhurried Scripture",
                description = "Short, guided devotionals that meet you in the quiet of your day.",
                accentColor = colors.onboarding.accentPrimary,
                imageRes = R.drawable.feature_onboarding_unhurried_scripture
            ),
            OnboardingPage(
                title = "Reflect and Pray",
                description = "Private journal and prayer lists, encrypted by default.",
                accentColor = colors.onboarding.accentSecondary,
                imageRes = R.drawable.feature_onboarding_reflect_and_pray
            ),
            OnboardingPage(
                title = "Build Your Rhythm",
                description = "Choose 5, 10, or 20 minute sessions with gentle reminders.",
                accentColor = colors.onboarding.accentTertiary,
                imageRes = R.drawable.feature_onboarding_build_your_rhythm
            ),
            OnboardingPage(
                title = "Offline and Yours",
                description = "Download scripture and keep your progress anywhere.",
                accentColor = colors.onboarding.accentQuaternary,
                imageRes = R.drawable.feature_onboarding_offline_and_yours
            )
        )
    }
    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()
    val titleColor = Color.White
    val bodyColor = Color.White.copy(alpha = 0.85f)

    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { pageIndex ->
            val page = pages[pageIndex]
            val isLastPage = pagerState.currentPage == pages.lastIndex
            val onNext: () -> Unit = {
                if (isLastPage) {
                    onCreateAccount()
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Image(
                    painter = painterResource(page.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(horizontal = spacing.padding22, vertical = spacing.padding18)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Spacer(modifier = Modifier.width(spacing.padding1))
                        SacramentButton(
                            text = "Skip",
                            onClick = onCreateAccount,
                            enabled = !isLoading,
                            variant = SacramentButtonVariant.Ghost
                        )
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    FooterPanel(
                        title = page.title,
                        description = page.description,
                        totalPages = pages.size,
                        currentPage = pagerState.currentPage,
                        titleColor = titleColor,
                        bodyColor = bodyColor,
                        accentColor = page.accentColor,
                        isLoading = isLoading,
                        onNext = onNext,
                        modifier = Modifier.padding(
                            horizontal = spacing.padding6,
                            vertical = spacing.padding12
                        )
                    )
                }
            }
        }
    }
}

@Composable
private fun FooterPanel(
    title: String,
    description: String,
    totalPages: Int,
    currentPage: Int,
    titleColor: Color,
    bodyColor: Color,
    accentColor: Color,
    isLoading: Boolean,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = SacramentTheme.spacing
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.padding20, vertical = spacing.padding18)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                SacramentText(
                    text = title,
                    style = SacramentTheme.typography.titleLarge,
                    color = titleColor
                )
                Spacer(modifier = Modifier.height(spacing.padding8))
                SacramentText(
                    text = description,
                    style = SacramentTheme.typography.bodyMedium,
                    color = bodyColor
                )
                Spacer(modifier = Modifier.height(spacing.padding14))
                DotIndicator(
                    totalPages = totalPages,
                    currentPage = currentPage,
                    activeColor = titleColor,
                    inactiveColor = titleColor.copy(alpha = 0.35f)
                )
            }
            Spacer(modifier = Modifier.width(spacing.padding16))
            ProgressRingNextButton(
                progress = (currentPage + 1) / totalPages.toFloat(),
                accentColor = accentColor,
                enabled = !isLoading,
                onClick = onNext
            )
        }
    }
}

@Composable
private fun ProgressRingNextButton(
    progress: Float,
    accentColor: Color,
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val animatedProgress by animateFloatAsState(
        targetValue = progress.coerceIn(0f, 1f),
        label = "onboardingProgress"
    )
    val ringThickness = 4.dp
    val ringSize = 72.dp
    val buttonSize = 56.dp
    val trackColor = Color.White.copy(alpha = 0.25f)
    val ringColor = Color.White

    Box(
        modifier = modifier.size(ringSize),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.matchParentSize()) {
            val strokeWidth = ringThickness.toPx()
            val inset = strokeWidth / 2f
            val diameter = size.minDimension - strokeWidth
            val style = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            drawArc(
                color = trackColor,
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                topLeft = androidx.compose.ui.geometry.Offset(inset, inset),
                size = androidx.compose.ui.geometry.Size(diameter, diameter),
                style = style
            )
            drawArc(
                color = ringColor,
                startAngle = -90f,
                sweepAngle = 360f * animatedProgress,
                useCenter = false,
                topLeft = androidx.compose.ui.geometry.Offset(inset, inset),
                size = androidx.compose.ui.geometry.Size(diameter, diameter),
                style = style
            )
        }
        SacramentIconButton(
            imageVector = SacramentIcons.SacramentIconArrowForward,
            contentDescription = "Next",
            onClick = onClick,
            enabled = enabled,
            modifier = Modifier
                .size(buttonSize)
                .shadow(10.dp, CircleShape)
                .background(Color.White, CircleShape)
        )
    }
}

@Composable
private fun DotIndicator(
    totalPages: Int,
    currentPage: Int,
    activeColor: Color,
    inactiveColor: Color
) {
    val spacing = SacramentTheme.spacing
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        repeat(totalPages) { index ->
            val isActive = index == currentPage
            val size by animateDpAsState(
                targetValue = if (isActive) 10.dp else 6.dp,
                label = "dotSize"
            )
            val color by animateColorAsState(
                targetValue = if (isActive) activeColor else inactiveColor,
                label = "dotColor"
            )
            Box(
                modifier = Modifier
                    .padding(end = spacing.padding6)
                    .size(size)
                    .background(color, CircleShape)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun OnboardingScreenPreview() {
    OnboardingScreenContent(
        isLoading = false,
        onCreateAccount = {}
    )
}
