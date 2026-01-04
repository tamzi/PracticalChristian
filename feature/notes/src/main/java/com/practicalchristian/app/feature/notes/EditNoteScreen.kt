package com.practicalchristian.app.feature.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Save
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorDefaults
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme

/**
 * Edit note screen for creating/editing notes.

 */
@Composable
fun EditNoteScreen(
    id: String? = null,
    navigator: AppNavigator,
    viewModel: EditNoteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    // Load existing note if ID is provided
    LaunchedEffect(id) {
        id?.let { viewModel.loadNote(it) }
    }

    EditNoteScreenContent(
        state = state,
        onClickNavigateBack = { navigator.back() },
        onValueChange = viewModel::onValueChange,
        onClickTag = viewModel::onClickTag,
        onTagCreateToggle = viewModel::onTagCreateToggle,
        onToggleBottomSheetTags = viewModel::onToggleBottomSheetTag,
        onChangeTagName = viewModel::onValueChangeTagName,
        onClickTagColor = viewModel::onValueChangeTagColor,
        onClickTagGenerateColors = viewModel::onClickTagRegenerateColors,
        onClickTagInsert = viewModel::onClickTagInsert,
        onClickNoteSave = viewModel::onClickNoteSave
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNoteScreenContent(
    state: EditNoteScreenUiState,
    onClickNavigateBack: () -> Unit,
    onValueChange: (String, EditNoteValue) -> Unit,
    onClickTag: (TagDomain) -> Unit,
    onTagCreateToggle: () -> Unit,
    onToggleBottomSheetTags: () -> Unit,
    onChangeTagName: (String) -> Unit,
    onClickTagColor: (String) -> Unit,
    onClickTagGenerateColors: () -> Unit,
    onClickTagInsert: () -> Unit,
    onClickNoteSave: (String) -> Unit,
) {
    LaunchedEffect(state.navigateBack) {
        if (state.navigateBack) onClickNavigateBack.invoke()
    }

    val richTextState = rememberRichTextState()
    val spacing = SacramentTheme.spacing

    // Populate rich text editor only once when loading an existing note
    // Keyed on selectedNote.id to prevent overwriting user edits when content changes
    LaunchedEffect(state.selectedNote?.id) {
        if (state.noteDetail.content.isNotBlank()) {
            richTextState.setMarkdown(state.noteDetail.content)
        }
    }

    TagsBottomSheet(
        name = state.tagDetail.name,
        color = state.tagDetail.color,
        isEnabled = state.tagDetail.isValidTagData,
        isCreatingTag = state.tagDetail.isCreating,
        isBottomSheetVisible = state.tagDetail.isShowing,
        tagsState = state.tagsState,
        tags = state.tags,
        onTagClicked = onClickTag,
        onTagCreateToggle = onTagCreateToggle,
        onDismissBottomSheet = onToggleBottomSheetTags,
        colors = state.tagDetail.colors,
        onChangeTagName = onChangeTagName,
        onClickTagColor = onClickTagColor,
        onClickTagGenerateColors = onClickTagGenerateColors,
        onClickTagInsert = onClickTagInsert,
    )

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(text = "")
        }, navigationIcon = {
            IconButton(onClick = onClickNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "back"
                )
            }
        })
    }, containerColor = SacramentTheme.colors.surfaces.surface, snackbarHost = {
        AnimatedVisibility(visible = state.error != null) {
            state.error?.let {
                Snackbar(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Text(text = it)
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "dismiss")
                        }
                    }
                }
            }
        }
    }) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                LazyRow {
                    items(state.tags) { tag ->
                        Card(
                            modifier = Modifier.padding(start = spacing.padding8),
                            onClick = { onClickTag.invoke(tag) },
                            colors = CardDefaults.cardColors(
                                containerColor = SacramentTheme.colors.surfaces.background
                            )
                        ) {
                            Text(
                                modifier = Modifier.padding(
                                    vertical = spacing.padding4,
                                    horizontal = spacing.padding16
                                ),
                                text = "# ${tag.name}"
                            )
                        }
                    }
                }
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(0.dp, Color.Transparent)),
                value = state.noteDetail.title,
                onValueChange = { value -> onValueChange.invoke(value, EditNoteValue.TITLE) },
                placeholder = { Text(text = "Title") },
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = SacramentTheme.colors.surfaces.surface,
                    focusedContainerColor = SacramentTheme.colors.surfaces.surface,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent
                ),
                singleLine = true
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                RichTextEditor(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .border(BorderStroke(0.dp, Color.Transparent))
                        .background(Color.Transparent),
                    state = richTextState,
                    placeholder = { Text(text = "Description") },
                    colors = RichTextEditorDefaults.richTextEditorColors(
                        containerColor = SacramentTheme.colors.surfaces.surface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Row {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Rounded.Book, contentDescription = "book")
                    }
                    IconButton(onClick = onToggleBottomSheetTags) {
                        Icon(imageVector = Icons.Rounded.Tag, contentDescription = "tag")
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
/*                    IconButton(onClick = { *//*TODO*//* }) {
                        Icon(imageVector = Icons.Rounded.PushPin, contentDescription = "pin")
                    }
                    IconButton(onClick = { *//*TODO*//* }) {
                        Icon(imageVector = Icons.Rounded.Delete, contentDescription = "delete")
                    }*/
                    IconButton(onClick = { onClickNoteSave.invoke(richTextState.toMarkdown()) }) {
                        Icon(imageVector = Icons.Rounded.Save, contentDescription = "save")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun EditNoteScreenContentPreview() {
    val state = EditNoteScreenUiState(
        tags = listOf(TagDomain(1, "thoughts", "#FFB6C1")),
        tagsState = UiListState.Success(
            UiSuccessState.Data(
                listOf(
                    TagDomain(
                        1,
                        "thoughts",
                        "#FFB6C1"
                    )
                )
            )
        ),
        noteDetail = NoteDetail(
            title = "My awesome note",
            content = "This is the content of my note."
        )
    )
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        EditNoteScreenContent(
            state = state,
            onClickNavigateBack = {},
            onValueChange = { _, _ -> },
            onClickTag = {},
            onTagCreateToggle = {},
            onToggleBottomSheetTags = {},
            onChangeTagName = {},
            onClickTagColor = {},
            onClickTagGenerateColors = {},
            onClickTagInsert = {},
            onClickNoteSave = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsBottomSheet(
    name: String = "",
    color: String = "",
    isEnabled: Boolean,
    isBottomSheetVisible: Boolean,
    isCreatingTag: Boolean,
    tagsState: UiListState<List<TagDomain>>,
    tags: List<TagDomain>,
    colors: List<String>,
    onTagCreateToggle: () -> Unit,
    onTagClicked: (TagDomain) -> Unit,
    onDismissBottomSheet: () -> Unit,
    onChangeTagName: (String) -> Unit,
    onClickTagColor: (String) -> Unit,
    onClickTagGenerateColors: () -> Unit,
    onClickTagInsert: () -> Unit,
) {
    val spacing = SacramentTheme.spacing
    AnimatedVisibility(visible = isBottomSheetVisible) {
        ModalBottomSheet(onDismissRequest = onDismissBottomSheet) {
            Scaffold(
                containerColor = SacramentTheme.colors.surfaces.surface, topBar = {
                    TopAppBar(
                        title = { Text(text = if (isCreatingTag) "Create" else "Tags") },
                        actions = {
                            IconButton(onClick = onTagCreateToggle) {
                                Icon(
                                    imageVector = if (isCreatingTag) Icons.Rounded.Close else Icons.Rounded.Add,
                                    contentDescription = ""
                                )
                            }
                        })
                }) { values ->
                Column(modifier = Modifier.padding(values)) {
                    when (isCreatingTag) {
                        true -> {
                            Surface(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = spacing.padding32),
                                color = SacramentTheme.colors.surfaces.surface
                            ) {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    TextField(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = spacing.padding32),
                                        value = name,
                                        onValueChange = onChangeTagName,
                                        placeholder = { Text(text = "Name") },
                                        maxLines = 1,
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Rounded.Tag,
                                                contentDescription = ""
                                            )
                                        },
                                        singleLine = true,
                                    )
                                    LazyVerticalGrid(
                                        modifier = Modifier.padding(
                                            top = spacing.padding16,
                                            bottom = spacing.padding32
                                        ),
                                        columns = GridCells.Fixed(5),
                                    ) {
                                        items(colors.size) {
                                            val value = colors[it]
                                            Card(
                                                modifier = Modifier.padding(spacing.padding8),
                                                onClick = { onClickTagColor.invoke(value) },
                                                colors = CardDefaults.cardColors(
                                                    containerColor = Color(
                                                        value.toColorInt()
                                                    )
                                                ),
                                                border = if (color == value) BorderStroke(
                                                    2.dp, SacramentTheme.colors.text.strong
                                                )
                                                else null
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .width(50.dp)
                                                        .height(50.dp)
                                                )
                                            }
                                        }
                                        item {
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.Center
                                            ) {
                                                IconButton(
                                                    modifier = Modifier.padding(top = spacing.padding8),
                                                    onClick = onClickTagGenerateColors,
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Rounded.Refresh,
                                                        contentDescription = ""
                                                    )
                                                }
                                            }
                                        }
                                    }
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(bottom = spacing.padding32),
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Button(
                                            modifier = Modifier.weight(1f),
                                            enabled = isEnabled,
                                            onClick = onClickTagInsert
                                        ) {
                                            Text(text = "Create")
                                        }
                                    }
                                }
                            }
                        }

                        false -> {
                            when (tagsState) {
                                is UiListState.Error -> {
                                    SacramentEmptyState(
                                        icon = Icons.AutoMirrored.Rounded.List,
                                        title = "Error",
                                        contentDescription = "error fetching results",
                                        description = tagsState.message
                                    )
                                }

                                UiListState.Idle -> {
                                    SacramentEmptyState(
                                        icon = Icons.AutoMirrored.Rounded.List,
                                        title = "Welcome",
                                        contentDescription = "idle fetching results",
                                        description = "Please wait while we're fetching your tags"
                                    )
                                }

                                UiListState.Loading -> {
                                    SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                                        CircularProgressIndicator()
                                    }
                                }

                                is UiListState.Success -> {
                                    when (val result = tagsState.data) {
                                        UiSuccessState.Empty -> {
                                            SacramentEmptyState(
                                                icon = Icons.Rounded.Tag,
                                                title = "Empty",
                                                contentDescription = "empty icon",
                                                description = "You don't have any tags.\nClick on the button below to create",
                                                action = {
                                                    Button(
                                                        modifier = Modifier.padding(top = spacing.padding16),
                                                        onClick = { }) {
                                                        Text(text = "create")
                                                    }
                                                }
                                            )
                                        }

                                        is UiSuccessState.Data -> {
                                            val list = result.data
                                            LazyVerticalStaggeredGrid(
                                                columns = StaggeredGridCells.Adaptive(95.dp),
                                                horizontalArrangement = Arrangement.spacedBy(spacing.padding8),
                                                verticalItemSpacing = spacing.padding8,
                                                contentPadding = PaddingValues(spacing.padding8)
                                            ) {
                                                items(list) { item ->
                                                    val (background, onBackground) = if (tags.contains(
                                                            item
                                                        )
                                                    ) Pair(
                                                        Color(item.color.toColorInt()), Color.Black
                                                    )
                                                    else Pair(Color.Gray, Color.White)
                                                    Card(
                                                        onClick = { onTagClicked.invoke(item) },
                                                        colors = CardDefaults.cardColors(
                                                            containerColor = background,
                                                            contentColor = onBackground
                                                        )
                                                    ) {
                                                        Text(
                                                            modifier = Modifier.padding(
                                                                horizontal = spacing.padding16,
                                                                vertical = spacing.padding8
                                                            ),
                                                            text = item.name,
                                                            color = onBackground
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
    }
}

@Preview
@Composable
fun TagsBottomSheetPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        TagsBottomSheet(
            name = "New Tag",
            color = "#FFFFFF",
            isEnabled = true,
            isBottomSheetVisible = true,
            isCreatingTag = true,
            tagsState = UiListState.Success(
                UiSuccessState.Data(
                    listOf(
                        TagDomain(
                            1,
                            "thoughts",
                            "#FFB6C1"
                        )
                    )
                )
            ),
            tags = listOf(),
            colors = listOf("#FF0000", "#00FF00", "#0000FF"),
            onTagCreateToggle = {},
            onTagClicked = {},
            onDismissBottomSheet = {},
            onChangeTagName = {},
            onClickTagColor = {},
            onClickTagGenerateColors = {},
            onClickTagInsert = {}
        )
    }
}
