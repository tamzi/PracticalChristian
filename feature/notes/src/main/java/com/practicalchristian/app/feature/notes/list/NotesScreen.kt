@file:OptIn(kotlin.time.ExperimentalTime::class)

package com.practicalchristian.app.feature.notes.list

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.NoteBook
import com.practicalchristian.app.core.domain.models.NoteDomain
import com.practicalchristian.app.core.domain.models.NoteType
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.ui.components.BottomNavScreen
import com.practicalchristian.app.core.ui.components.SharedBottomNavigationBar
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.navigation.AppDestination
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentFab
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.components.surface.SacramentCardColors
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentDivider
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Clock

/**
 * Notes screen - List of notes.

 */
@Composable
fun NotesScreen(
    navigator: AppNavigator,
    viewModel: NotesViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    NotesScreenContent(
        state = state,
        onNavigateToNote = { noteId ->
            navigator.navigate(AppDestination.NoteDetails(noteId = noteId))
        },
        onNavigateToNewNote = {
            navigator.navigate(AppDestination.EditNote(id = null))
        },
        onNavigateToHome = { navigator.navigate(AppDestination.Home) },
        onNavigateToBooks = { navigator.navigate(AppDestination.Books) }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesScreenContent(
    state: NotesScreenUiState,
    onNavigateToNote: (String) -> Unit,
    onNavigateToNewNote: () -> Unit,
    onNavigateToHome: () -> Unit = {},
    onNavigateToBooks: () -> Unit = {}
) {
    val spacing = SacramentTheme.spacing
    SacramentScreenScaffold(
        topBar = {
            SacramentTopAppBar(
                title = {
                    SacramentText(
                        text = "Notes",
                        style = SacramentTheme.typography.titleSmall,
                    )
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(visible = state.listState.hasData) {
                SacramentFab(
                    imageVector = SacramentIcons.SacramentIconAdd,
                    contentDescription = "Create note",
                    onClick = onNavigateToNewNote,
                )
            }
        },
        bottomBar = {
            SharedBottomNavigationBar(
                selectedScreen = BottomNavScreen.NOTES,
                onHomeClick = onNavigateToHome,
                onBooksClick = onNavigateToBooks,
                onNotesClick = { /* Already on Notes */ }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            SacramentDivider()
            when (val result = state.listState) {
                is UiListState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SacramentIcon(
                            imageVector = SacramentIcons.SacramentIconWarning,
                            contentDescription = "error",
                            tint = SacramentTheme.colors.semantic.error,
                            modifier = Modifier.padding(bottom = spacing.padding12),
                            size = 48.dp,
                        )
                        SacramentText(
                            text = "Error",
                            color = SacramentTheme.colors.semantic.error,
                            style = SacramentTheme.typography.titleLarge.copy(textAlign = TextAlign.Center),
                        )
                        SacramentText(
                            text = result.message,
                            color = SacramentTheme.colors.semantic.error,
                            style = SacramentTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
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
                        icon = SacramentIcons.SacramentIconList,
                        title = "Welcome",
                        contentDescription = "error fetching results",
                        description = "Please wait while we're setting things up"
                    )
                }

                UiListState.Loading -> {
                    SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                        SacramentProgressIndicator()
                    }
                }

                is UiListState.Success -> {
                    when (val success = result.data) {
                        UiSuccessState.Empty -> {
                            SacramentEmptyState(
                                icon = SacramentIcons.SacramentIconNote,
                                title = "Empty",
                                contentDescription = "empty icon",
                                description = "You don't have any Notes.\nClick on the button below to create",
                                action = {
                                    SacramentButton(
                                        text = "create",
                                        onClick = onNavigateToNewNote,
                                    )
                                }
                            )
                        }

                        is UiSuccessState.Data -> {
                            val list = success.data
                            LazyColumn {
                                items(list) { note ->
                                    val cardColor = note.tags.firstOrNull()?.color?.toColorInt()
                                        ?.let { Color(it) }
                                        ?: SacramentTheme.colors.surfaces.surfaceVariant
                                    val contentColor = if (cardColor.luminance() < 0.5f) {
                                        SacramentTheme.colors.text.inverse
                                    } else {
                                        SacramentTheme.colors.text.strong
                                    }
                                    SacramentCard(
                                        modifier = Modifier
                                            .padding(horizontal = spacing.padding16)
                                            .padding(top = spacing.padding16)
                                            .combinedClickable(
                                                onLongClick = {},
                                                onClick = { onNavigateToNote(note.id) },
                                            ),
                                        onClick = null,
                                        colors = SacramentCardColors(
                                            container = cardColor,
                                            border = SacramentTheme.colors.text.muted.copy(alpha = 0.2f),
                                        ),
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(spacing.padding16)
                                                .fillMaxWidth()
                                        ) {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceBetween
                                            ) {
                                                SacramentText(
                                                    text = note.displayBook,
                                                    style = SacramentTheme.typography.labelSmall,
                                                    color = contentColor,
                                                )
                                                SacramentText(
                                                    text = note.displayDate,
                                                    style = SacramentTheme.typography.labelSmall,
                                                    color = contentColor,
                                                )
                                            }
                                            SacramentText(
                                                modifier = Modifier.padding(top = spacing.padding16),
                                                text = note.title,
                                                style = SacramentTheme.typography.titleSmall.copy(fontWeight = FontWeight.Bold),
                                                color = contentColor,
                                            )
                                            SacramentText(
                                                modifier = Modifier.padding(top = spacing.padding8),
                                                text = note.content,
                                                overflow = TextOverflow.Ellipsis,
                                                maxLines = 2,
                                                style = SacramentTheme.typography.bodySmall.copy(fontWeight = FontWeight.Normal),
                                                color = contentColor,
                                            )
                                            SacramentText(
                                                modifier = Modifier.padding(top = spacing.padding8),
                                                text = note.tags.joinToString(separator = " | ") { it.name },
                                                style = SacramentTheme.typography.labelLarge,
                                                color = contentColor,
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

@Preview(showBackground = true, name = "NotesScreen - Loading")
@Composable
private fun NotesScreenLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NotesScreenContent(
            state = NotesScreenUiState(
                listState = UiListState.Loading
            ), onNavigateToNote = {}, onNavigateToNewNote = {})
    }
}

@Preview(showBackground = true, name = "NotesScreen - Empty")
@Composable
private fun NotesScreenEmptyPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NotesScreenContent(
            state = NotesScreenUiState(
                listState = UiListState.Success(UiSuccessState.Empty)
            ), onNavigateToNote = {}, onNavigateToNewNote = {})
    }
}

@Preview(showBackground = true, name = "NotesScreen - Error")
@Composable
private fun NotesScreenErrorPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NotesScreenContent(
            state = NotesScreenUiState(
                listState = UiListState.Error("Unable to load notes data")
            ), onNavigateToNote = {}, onNavigateToNewNote = {})
    }
}

@Preview(showBackground = true, name = "NotesScreen - With Notes")
@Composable
private fun NotesScreenWithDataPreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val sampleNotes = listOf(
        NoteDomain(
            id = "1",
            createdAt = now,
            updatedAt = now,
            title = "Faith and Trust",
            content = "Today I learned about having faith even when things seem uncertain. God's plan is always perfect, even when we cannot see the full picture.",
            tags = listOf(
                TagDomain(1, "Faith", "#4CAF50"), TagDomain(2, "Trust", "#2196F3")
            ),
            type = NoteType.NOTE,
            start = NoteBook(Book(1, "Genesis", 50), 1, 1),
            end = NoteBook(Book(1, "Genesis", 50), 1, 10)
        ), NoteDomain(
            id = "2",
            createdAt = now,
            updatedAt = now,
            title = "Prayer Request",
            content = "Praying for healing for my family member who is going through a difficult time.",
            tags = listOf(
                TagDomain(3, "Prayer", "#FF9800"), TagDomain(4, "Healing", "#9C27B0")
            ),
            type = NoteType.TASK,
            start = NoteBook(Book(19, "Psalms", 150), 23, 1),
            end = NoteBook(Book(19, "Psalms", 150), 23, 6)
        ), NoteDomain(
            id = "3",
            createdAt = now,
            updatedAt = now,
            title = "Love One Another",
            content = "The importance of showing love to others, especially those who are difficult to love. This is a core teaching of Christ.",
            tags = listOf(
                TagDomain(5, "Love", "#E91E63"), TagDomain(6, "Teaching", "#607D8B")
            ),
            type = NoteType.NOTE,
            start = NoteBook(Book(43, "John", 21), 13, 34),
            end = NoteBook(Book(43, "John", 21), 13, 35)
        )
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NotesScreenContent(
            state = NotesScreenUiState(
                listState = UiListState.Success(UiSuccessState.Data(sampleNotes))
            ), onNavigateToNote = {}, onNavigateToNewNote = {})
    }
}

@Preview(showBackground = true, name = "NotesScreen - Single Note")
@Composable
private fun NotesScreenSingleNotePreview() {
    val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
    val singleNote = listOf(
        NoteDomain(
            id = "1",
            createdAt = now,
            updatedAt = now,
            title = "God's Grace",
            content = "Reflecting on the amazing grace that God shows us daily. Even when we fall short, His love remains constant and unchanging.",
            tags = listOf(
                TagDomain(1, "Grace", "#4CAF50"), TagDomain(2, "Love", "#E91E63")
            ),
            type = NoteType.NOTE,
            start = NoteBook(Book(49, "Ephesians", 6), 2, 8),
            end = NoteBook(Book(49, "Ephesians", 6), 2, 9)
        )
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NotesScreenContent(
            state = NotesScreenUiState(
                listState = UiListState.Success(UiSuccessState.Data(singleNote))
            ), onNavigateToNote = {}, onNavigateToNewNote = {})
    }
}

@Preview(showBackground = true, name = "NotesScreen - Idle State")
@Composable
private fun NotesScreenIdlePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NotesScreenContent(
            state = NotesScreenUiState(
                listState = UiListState.Idle
            ), onNavigateToNote = {}, onNavigateToNewNote = {})
    }
}
