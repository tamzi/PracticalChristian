package com.practicalchristian.app.feature.books

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.domain.extensions.sentence
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.ui.helpers.ItemState
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentDivider
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Book details screen showing chapters.

 */
@Composable
fun BookScreen(
    id: Int,
    navigator: AppNavigator,
    viewModel: BookViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getItem(id = id)
    }

    BookScreenContent(
        state = state,
        onNavigateBackClicked = { navigator.back() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookScreenContent(
    state: BookUiState, onNavigateBackClicked: () -> Unit
) {
    val spacing = SacramentTheme.spacing
    Scaffold(
        modifier = Modifier,
        topBar = {
            TopAppBar(
                title = { Text(text = state.name.ifBlank { "book" }.sentence) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBackClicked) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "navigate back"
                        )
                    }
                })
        },
        containerColor = SacramentTheme.colors.surfaces.background,
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            when (val data = state.result) {
                ItemState.Loading -> {
                    SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                        CircularProgressIndicator()
                    }
                }

                is ItemState.Error -> {
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
                            text = data.message ?: "Unknown error occurred",
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

                is ItemState.Success -> {
                    val item = data.item
                    LazyVerticalGrid(columns = GridCells.Fixed(4)) {
                        items(item.chapters) {
                            Card(
                                modifier = Modifier.height(100.dp),
                                onClick = { },
                                shape = RoundedCornerShape(0.dp),
                                colors = CardDefaults.cardColors(containerColor = SacramentTheme.colors.surfaces.background),
                            ) {
                                Box(modifier = Modifier.fillMaxSize()) {
                                    SacramentDivider(
                                        modifier = Modifier
                                            .width(1.dp)
                                            .fillMaxHeight()
                                            .align(Alignment.CenterEnd)
                                    )
                                    SacramentDivider(
                                        modifier = Modifier
                                            .width(1.dp)
                                            .fillMaxHeight()
                                            .align(Alignment.CenterStart)
                                    )
                                    SacramentDivider(
                                        modifier = Modifier.align(Alignment.TopCenter)
                                    )

                                    SacramentDivider(
                                        modifier = Modifier.align(Alignment.BottomCenter)
                                    )
                                    Text(
                                        modifier = Modifier.align(alignment = Alignment.Center),
                                        text = "${it + 1}"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, name = "BookScreen - Loading")
@Composable
private fun BookScreenContentLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BookScreenContent(
            state = BookUiState(
                name = "Genesis", result = ItemState.Loading
            ), onNavigateBackClicked = { })
    }
}

@Preview(showBackground = true, name = "BookScreen - Success (50 chapters)")
@Composable
private fun BookScreenContentSuccessPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BookScreenContent(
            state = BookUiState(
                name = "Genesis", result = ItemState.Success(
                    Book(id = 1, name = "Genesis", chapters = 50)
                )
            ), onNavigateBackClicked = { })
    }
}

@Preview(showBackground = true, name = "BookScreen - Success (22 chapters)")
@Composable
private fun BookScreenContentSuccessMediumPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BookScreenContent(
            state = BookUiState(
                name = "Revelation", result = ItemState.Success(
                    Book(id = 66, name = "Revelation", chapters = 22)
                )
            ), onNavigateBackClicked = { })
    }
}

@Preview(showBackground = true, name = "BookScreen - Success (1 chapter)")
@Composable
private fun BookScreenContentSuccessSmallPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BookScreenContent(
            state = BookUiState(
                name = "Obadiah", result = ItemState.Success(
                    Book(id = 31, name = "Obadiah", chapters = 1)
                )
            ), onNavigateBackClicked = { })
    }
}

@Preview(showBackground = true, name = "BookScreen - Error")
@Composable
private fun BookScreenContentErrorPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BookScreenContent(
            state = BookUiState(
                name = "", result = ItemState.Error("Unable to load book data")
            ), onNavigateBackClicked = { })
    }
}

@Preview(showBackground = true, name = "BookScreen - Error (No book name)")
@Composable
private fun BookScreenContentErrorNoNamePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BookScreenContent(
            state = BookUiState(
                name = "", result = ItemState.Error("Book not found")
            ), onNavigateBackClicked = { })
    }
}
