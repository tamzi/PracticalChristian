package com.practicalchristian.app.feature.profile

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.practicalchristian.app.core.ui.helpers.versionName
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.R
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Profile screen.

 */
@Composable
fun ProfileScreen(
    navigator: AppNavigator,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    ProfileScreenContent(
        state = state,
        onNavigateToTags = { navigator.navigate(AppDestination.Tags) },
        onToggleDarkModeClicked = viewModel::toggleDarkModeTheme,
        onNavigateBackClicked = { navigator.back() },
        onProfilePictureSelected = viewModel::setProfilePictureUri,
        versionName = LocalContext.current.versionName
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreenContent(
    state: ProfileScreenUiState,
    onNavigateToTags: () -> Unit,
    onToggleDarkModeClicked: () -> Unit,
    onNavigateBackClicked: () -> Unit,
    onProfilePictureSelected: (String?) -> Unit,
    versionName: String = LocalContext.current.versionName
) {
    val spacing = SacramentTheme.spacing
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        onProfilePictureSelected(uri?.toString())
    }

    Scaffold(
        topBar = {
            LargeTopAppBar(title = {
                Text(text = "Profile")
            }, navigationIcon = {
                IconButton(onClick = onNavigateBackClicked) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                        contentDescription = "navigate back"
                    )
                }
            }, actions = {
                IconButton(onClick = onToggleDarkModeClicked) {
                    Icon(
                        imageVector = if (state.isDarkThemeEnabled) Icons.Rounded.LightMode else Icons.Rounded.DarkMode,
                        contentDescription = "dark mode icon toggle"
                    )
                }
            })
        },
        containerColor = SacramentTheme.colors.surfaces.background,
    ) {
        Column(modifier = Modifier.padding(it)) {
            // Profile Picture Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.padding16),
                colors = CardDefaults.cardColors(containerColor = SacramentTheme.colors.surfaces.surface)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.padding16),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box {
                        if (state.profilePictureUri != null) {
                            AsyncImage(
                                contentDescription = "profile picture",
                                contentScale = ContentScale.Crop,
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(state.profilePictureUri).crossfade(true).build(),
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .border(3.dp, SacramentTheme.colors.brand.primary, CircleShape)
                                    .clickable { imagePickerLauncher.launch("image/*") })
                        } else {
                            AsyncImage(
                                contentDescription = "profile picture placeholder",
                                contentScale = ContentScale.Crop,
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(R.drawable.sacrament_profile_placeholder).crossfade(true)
                                    .build(),
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .border(3.dp, SacramentTheme.colors.brand.primary, CircleShape)
                                    .clickable { imagePickerLauncher.launch("image/*") })
                        }

                        IconButton(
                            onClick = { imagePickerLauncher.launch("image/*") },
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .size(24.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Edit,
                                contentDescription = "edit profile picture",
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }

                    Text(
                        text = "Tap to change profile picture",
                        style = SacramentTheme.typography.bodySmall,
                        color = SacramentTheme.colors.text.strong.copy(alpha = 0.6f),
                        modifier = Modifier.padding(top = spacing.padding8)
                    )
                }
            }

            // Tags Section
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(spacing.padding16),
                onClick = onNavigateToTags,
                colors = CardDefaults.cardColors(containerColor = SacramentTheme.colors.surfaces.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(spacing.padding16),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Tags")
                    Icon(
                        imageVector = Icons.AutoMirrored.Rounded.ArrowForwardIos,
                        contentDescription = ""
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, text = versionName
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenContentPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ProfileScreenContent(
            state = ProfileScreenUiState(isDarkThemeEnabled = false, profilePictureUri = null),
            onNavigateToTags = {},
            onToggleDarkModeClicked = {},
            onNavigateBackClicked = {},
            onProfilePictureSelected = {},
            versionName = "1.0.0"
        )
    }
}

@Preview(showBackground = true, name = "With Profile Picture")
@Composable
private fun ProfileScreenContentWithPicturePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ProfileScreenContent(
            state = ProfileScreenUiState(
                isDarkThemeEnabled = false,
                profilePictureUri = "content://media/external/images/media/1000"
            ),
            onNavigateToTags = {},
            onToggleDarkModeClicked = {},
            onNavigateBackClicked = {},
            onProfilePictureSelected = {},
            versionName = "1.0.0"
        )
    }
}

@Preview(showBackground = true, name = "Dark Mode")
@Composable
private fun ProfileScreenContentDarkPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        ProfileScreenContent(
            state = ProfileScreenUiState(isDarkThemeEnabled = true),
            onNavigateToTags = {},
            onToggleDarkModeClicked = {},
            onNavigateBackClicked = {},
            onProfilePictureSelected = {},
            versionName = "1.0.0"
        )
    }
}
