package com.practicalchristian.app.feature.books.list

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.ui.components.BottomNavScreen
import com.practicalchristian.app.core.ui.components.SharedBottomNavigationBar
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.helpers.ViewType
import com.practicalchristian.app.core.ui.helpers.sentence
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.components.surface.SacramentCardDefaults
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentDivider
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.primitives.SacramentText
import com.sacrament.ui.foundation.icon.SacramentIcons

/**
 * Books screen - List of books.
 */
@Composable
fun BooksScreen(
    navigator: AppNavigator,
    viewModel: BooksViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BooksScreenContent(
        state = state,
        onChangeViewTypeClicked = viewModel::toggleViewType,
        onBookClicked = { id ->
            navigator.navigate(AppDestination.BookDetails(bookId = id))
        },
        onNavigateToHome = { navigator.navigate(AppDestination.Home) },
        onNavigateToNotes = { navigator.navigate(AppDestination.Notes) }
    )
}

@Composable
fun BooksScreenContent(
    state: BooksScreenUiState,
    onChangeViewTypeClicked: () -> Unit,
    onBookClicked: (Int) -> Unit,
    onNavigateToHome: () -> Unit = {},
    onNavigateToNotes: () -> Unit = {}
) {
    val spacing = SacramentTheme.spacing
    SacramentScreenScaffold(
        topBar = {
            SacramentTopAppBar(
                title = { SacramentText(text = "Books", style = SacramentTheme.typography.titleSmall) },
                actions = {
                    SacramentIconButton(
                        imageVector = when (state.view) {
                            ViewType.GRID -> SacramentIcons.List
                            ViewType.LIST -> SacramentIcons.GridOn
                        },
                        contentDescription = "grid",
                        onClick = onChangeViewTypeClicked
                    )
                }
            )
        },
        bottomBar = {
            SharedBottomNavigationBar(
                selectedScreen = BottomNavScreen.BOOKS,
                onHomeClick = onNavigateToHome,
                onBooksClick = { /* Already on Books */ },
                onNotesClick = onNavigateToNotes
            )
        }
    ) { values ->
        Column(
            modifier = Modifier.padding(values)
        ) {
            when (val result = state.listState) {
                is UiListState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
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

                is UiListState.Success<List<Book>> -> {
                    when (val success = result.data) {
                        UiSuccessState.Empty -> {
                            SacramentEmptyState(
                                icon = SacramentIcons.List,
                                title = "Empty",
                                contentDescription = "empty icon",
                                description = "No Books found."
                            )
                        }

                        is UiSuccessState.Data<List<Book>> -> {
                            val list = success.data
                            AnimatedContent(state.view, label = "") { type ->
                                when (type) {
                                    ViewType.GRID -> {
                                        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
                                            items(list.size) { index ->
                                                val book = list[index]
                                                BookItem(
                                                    isGrid = true,
                                                    book = book,
                                                    isFirst = index == 0,
                                                    isLast = index == list.lastIndex,
                                                    onBookClicked = onBookClicked,
                                                )
                                            }
                                        }
                                    }

                                    ViewType.LIST -> {
                                        LazyColumn {
                                            items(list.size) { index ->
                                                val book = list[index]
                                                BookItem(
                                                    isGrid = false,
                                                    book = book,
                                                    isFirst = index == 0,
                                                    isLast = index == list.lastIndex,
                                                    onBookClicked = onBookClicked,
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
        }
    }
}

@Composable
private fun BookItem(
    isGrid: Boolean,
    book: Book,
    isFirst: Boolean,
    isLast: Boolean,
    onBookClicked: (Int) -> Unit,
) {
    val spacing = SacramentTheme.spacing
    val height = if (isGrid) 100.dp else 75.dp
    SacramentCard(
        modifier = Modifier.height(height),
        colors = SacramentCardDefaults.colors(),
        onClick = { onBookClicked.invoke(book.id) },
        contentPadding = PaddingValues(0.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            SacramentDivider(
                modifier = Modifier.align(Alignment.TopCenter)
            )
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
                modifier = Modifier.align(Alignment.BottomCenter)
            )
            val padding = if (isGrid) spacing.padding16 else 0.dp
            SacramentText(
                text = book.name.sentence,
                style = SacramentTheme.typography.bodyMedium,
                modifier = Modifier
                    .padding(start = spacing.padding16, bottom = padding)
                    .align(alignment = if (isGrid) Alignment.BottomStart else Alignment.CenterStart)
            )
        }
    }
}

@Preview(showBackground = true, name = "BooksScreen - Loading")
@Composable
private fun BooksScreenLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BooksScreenContent(
            state = BooksScreenUiState(
            view = ViewType.LIST, listState = UiListState.Loading
        ), onChangeViewTypeClicked = {}, onBookClicked = {})
    }
}

@Preview(showBackground = true, name = "BooksScreen - Empty")
@Composable
private fun BooksScreenEmptyPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BooksScreenContent(
            state = BooksScreenUiState(
            view = ViewType.LIST, listState = UiListState.Success(UiSuccessState.Empty)
        ), onChangeViewTypeClicked = {}, onBookClicked = {})
    }
}

@Preview(showBackground = true, name = "BooksScreen - Error")
@Composable
private fun BooksScreenErrorPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BooksScreenContent(
            state = BooksScreenUiState(
            view = ViewType.LIST,
            listState = UiListState.Error("Failed to load books. Please check your internet connection.")
        ), onChangeViewTypeClicked = {}, onBookClicked = {})
    }
}

@Preview(showBackground = true, name = "BooksScreen - List View with Books")
@Composable
private fun BooksScreenListViewPreview() {
    val sampleBooks = listOf(
        Book(id = 1, name = "Genesis", chapters = 50),
        Book(id = 2, name = "Exodus", chapters = 40),
        Book(id = 3, name = "Leviticus", chapters = 27),
        Book(id = 4, name = "Numbers", chapters = 36),
        Book(id = 5, name = "Deuteronomy", chapters = 34)
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BooksScreenContent(
            state = BooksScreenUiState(
            view = ViewType.LIST, listState = UiListState.Success(UiSuccessState.Data(sampleBooks))
        ), onChangeViewTypeClicked = {}, onBookClicked = {})
    }
}

@Preview(showBackground = true, name = "BooksScreen - Grid View with Books")
@Composable
private fun BooksScreenGridViewPreview() {
    val sampleBooks = listOf(
        Book(id = 1, name = "Genesis", chapters = 50),
        Book(id = 2, name = "Exodus", chapters = 40),
        Book(id = 3, name = "Leviticus", chapters = 27),
        Book(id = 4, name = "Numbers", chapters = 36),
        Book(id = 5, name = "Deuteronomy", chapters = 34),
        Book(id = 6, name = "Joshua", chapters = 24),
        Book(id = 7, name = "Judges", chapters = 21),
        Book(id = 8, name = "Ruth", chapters = 4),
        Book(id = 9, name = "1 Samuel", chapters = 31)
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BooksScreenContent(
            state = BooksScreenUiState(
            view = ViewType.GRID, listState = UiListState.Success(UiSuccessState.Data(sampleBooks))
        ), onChangeViewTypeClicked = {}, onBookClicked = {})
    }
}

@Preview(showBackground = true, name = "BooksScreen - Single Book")
@Composable
private fun BooksScreenSingleBookPreview() {
    val singleBook = listOf(
        Book(id = 1, name = "Psalm", chapters = 150)
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BooksScreenContent(
            state = BooksScreenUiState(
            view = ViewType.LIST, listState = UiListState.Success(UiSuccessState.Data(singleBook))
        ), onChangeViewTypeClicked = {}, onBookClicked = {})
    }
}

@Preview(showBackground = true, name = "BooksScreen - Idle State")
@Composable
private fun BooksScreenIdlePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BooksScreenContent(
            state = BooksScreenUiState(
            view = ViewType.LIST, listState = UiListState.Idle
        ), onChangeViewTypeClicked = {}, onBookClicked = {})
    }
}

@Preview(showBackground = true, name = "BookItem - Grid View")
@Composable
private fun BookItemGridPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BookItem(
            isGrid = true,
            book = Book(id = 1, name = "Genesis", chapters = 50),
            isFirst = true,
            isLast = false,
            onBookClicked = {}
        )
    }
}

@Preview(showBackground = true, name = "BookItem - List View")
@Composable
private fun BookItemListPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        BookItem(
            isGrid = false,
            book = Book(id = 1, name = "Exodus", chapters = 40),
            isFirst = false,
            isLast = false,
            onBookClicked = {}
        )
    }
}
