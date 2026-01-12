package com.practicalchristian.app.feature.notes.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.practicalchristian.app.core.domain.models.Book
import com.practicalchristian.app.core.domain.models.NoteBook
import com.practicalchristian.app.core.domain.models.NoteDomain
import com.practicalchristian.app.core.domain.models.NoteType
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentText
import kotlinx.datetime.LocalDateTime

/**
 * Note details screen showing note content.

 */
@Composable
fun NoteScreen(
    noteId: String,
    navigator: AppNavigator,
    viewModel: NoteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = noteId) {
        viewModel.updateNoteId(id = noteId)
    }

    NoteScreenContent(
        state = state,
        onPressedBack = { navigator.back() }
    )
}

@Composable
fun NoteScreenContent(
    state: NoteScreenUiState,
    onPressedBack: () -> Unit,
) {
    val spacing = SacramentTheme.spacing
    SacramentScreenScaffold(
        topBar = {
            SacramentTopAppBar(
                title = { },
                navigationIcon = {
                    SacramentIconButton(
                        imageVector = SacramentIcons.ArrowBack,
                        contentDescription = "",
                        onClick = onPressedBack
                    )
                }
            )
        }
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
        ) {
            AnimatedContent(targetState = state.note, label = "") { note ->
                when (note) {
                    null -> {
                        SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                            SacramentProgressIndicator(variant = SacramentProgressVariant.Circular)
                        }
                    }

                    else -> {
                        val richState = rememberRichTextState()
                        LaunchedEffect(note.content) {
                            richState.setText(note.content)
                        }
                        SacramentText(
                            text = note.title,
                            style = SacramentTheme.typography.labelLarge,
                            modifier = Modifier.padding(top = spacing.padding16)
                        )
                        RichTextEditor(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f),
                            state = richState,
                            readOnly = true
                        )
                    }
                }
            }
        }
    }
}

@Preview(name = "Loading State")
@Composable
fun NoteScreenLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NoteScreenContent(
            state = NoteScreenUiState(
                id = "1", note = null
            ), onPressedBack = {})
    }
}

@Preview(name = "Note with Content")
@Composable
fun NoteScreenWithContentPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NoteScreenContent(
                state = NoteScreenUiState(
                    id = "1", note = NoteDomain(
                        id = "1",
                        title = "My Daily Reflection",
                        content = "This is a sample note content with some rich text formatting. It demonstrates how the note appears in the screen.",
                        tags = listOf(
                            TagDomain(1, "Personal", "#FF5722"),
                            TagDomain(2, "Reflection", "#2196F3")
                        ),
                        type = NoteType.NOTE,
                        start = NoteBook(
                            book = Book(1, "Genesis", 50), chapter = 1, verse = 1
                        ),
                        end = NoteBook(
                            book = Book(1, "Genesis", 50), chapter = 1, verse = 5
                        ),
                        createdAt = LocalDateTime(2024, 1, 15, 10, 30),
                        updatedAt = LocalDateTime(2024, 1, 15, 14, 45)
                    )
                ), onPressedBack = {})
    }
}

@Preview(name = "Long Note Content")
@Composable
fun NoteScreenLongContentPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NoteScreenContent(
                state = NoteScreenUiState(
                    id = "2", note = NoteDomain(
                        id = "2",
                        title = "Extended Study Notes on Biblical Teachings",
                        content = """
                            This is a longer note that demonstrates how the screen handles extensive content.
                            
                            Key Points:
                            • First important observation about the passage
                            • Second reflection on the spiritual meaning
                            • Third application to daily life
                            
                            Personal Reflection:
                            The passage speaks to me in multiple ways. It challenges my understanding of faith and provides guidance for my daily walk.
                            
                            Questions for Further Study:
                            1. How does this relate to other similar passages?
                            2. What is the historical context?
                            3. How can I apply this practically?
                        """.trimIndent(),
                        tags = listOf(
                            TagDomain(3, "Study", "#4CAF50"),
                            TagDomain(4, "Biblical", "#9C27B0"),
                            TagDomain(5, "Reflection", "#FF9800")
                        ),
                        type = NoteType.NOTE,
                        start = NoteBook(
                            book = Book(40, "Matthew", 28), chapter = 5, verse = 1
                        ),
                        end = NoteBook(
                            book = Book(40, "Matthew", 28), chapter = 5, verse = 12
                        ),
                        createdAt = LocalDateTime(2024, 1, 10, 9, 0),
                        updatedAt = LocalDateTime(2024, 1, 12, 16, 30)
                    )
                ), onPressedBack = {})
    }
}

@Preview(name = "Short Note")
@Composable
fun NoteScreenShortNotePreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        NoteScreenContent(
                state = NoteScreenUiState(
                    id = "3", note = NoteDomain(
                        id = "3",
                        title = "Quick Note",
                        content = "Brief insight from today's reading.",
                        tags = listOf(TagDomain(6, "Quick", "#607D8B")),
                        type = NoteType.NOTE,
                        start = NoteBook(
                            book = Book(19, "Psalms", 150), chapter = 23, verse = 1
                        ),
                        end = NoteBook(
                            book = Book(19, "Psalms", 150), chapter = 23, verse = 1
                        ),
                        createdAt = LocalDateTime(2024, 1, 16, 7, 15),
                        updatedAt = LocalDateTime(2024, 1, 16, 7, 20)
                    )
                ), onPressedBack = {})
    }
}
