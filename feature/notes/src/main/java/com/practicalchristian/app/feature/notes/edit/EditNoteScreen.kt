package com.practicalchristian.app.feature.notes.edit

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
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditor
import com.mohamedrejeb.richeditor.ui.material3.RichTextEditorDefaults
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.components.input.SacramentTextField
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.components.surface.SacramentCardColors
import com.sacrament.ui.components.surface.SacramentCardDefaults
import com.sacrament.ui.components.surface.SacramentModalBottomSheet
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.patterns.SacramentScreenScaffold
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentText

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

    SacramentScreenScaffold(topBar = {
        SacramentTopAppBar(
            title = { SacramentText(text = "", style = SacramentTheme.typography.titleSmall) },
            navigationIcon = {
                SacramentIconButton(
                    imageVector = SacramentIcons.ArrowBack,
                    contentDescription = "back",
                    onClick = onClickNavigateBack
                )
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                LazyRow {
                    items(state.tags) { tag ->
                        SacramentCard(
                            modifier = Modifier.padding(start = spacing.padding8),
                            onClick = { onClickTag.invoke(tag) },
                            colors = SacramentCardDefaults.colors(),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                                vertical = spacing.padding4,
                                horizontal = spacing.padding16
                            )
                        ) {
                            SacramentText(
                                text = "# ${tag.name}",
                                style = SacramentTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
            SacramentTextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.noteDetail.title,
                onValueChange = { value -> onValueChange.invoke(value, EditNoteValue.TITLE) },
                placeholder = "Title",
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
                    placeholder = { SacramentText("Description") },
                    colors = RichTextEditorDefaults.richTextEditorColors(
                        containerColor = SacramentTheme.colors.surfaces.surface,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Row {
                    SacramentIconButton(
                        imageVector = SacramentIcons.Book,
                        contentDescription = "book",
                        onClick = { /*TODO*/ }
                    )
                    SacramentIconButton(
                        imageVector = SacramentIcons.Tag,
                        contentDescription = "tag",
                        onClick = onToggleBottomSheetTags
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    SacramentIconButton(
                        imageVector = SacramentIcons.Save,
                        contentDescription = "save",
                        onClick = { onClickNoteSave.invoke(richTextState.toMarkdown()) }
                    )
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
        SacramentModalBottomSheet(
            onDismissRequest = onDismissBottomSheet
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SacramentText(
                        text = if (isCreatingTag) "Create" else "Tags",
                        style = SacramentTheme.typography.titleSmall
                    )
                    SacramentIconButton(
                        imageVector = if (isCreatingTag) SacramentIcons.Close else SacramentIcons.Add,
                        contentDescription = "",
                        onClick = onTagCreateToggle
                    )
                }
                Column(modifier = Modifier.padding(horizontal = spacing.padding32)) {
                    if (isCreatingTag) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = spacing.padding32)
                        ) {
                            SacramentTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = spacing.padding32),
                                value = name,
                                onValueChange = onChangeTagName,
                                placeholder = "Name",
                                leadingIcon = SacramentIcons.Tag,
                                singleLine = true
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
                                    SacramentCard(
                                        modifier = Modifier.padding(spacing.padding8),
                                        onClick = { onClickTagColor.invoke(value) },
                                        colors = if (color == value) {
                                            SacramentCardColors(
                                                container = Color(value.toColorInt()),
                                                border = SacramentTheme.colors.text.strong
                                            )
                                        } else {
                                            SacramentCardColors(
                                                container = Color(value.toColorInt()),
                                                border = SacramentCardDefaults.colors().border
                                            )
                                        },
                                        contentPadding = PaddingValues(0.dp)
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
                                        SacramentIconButton(
                                            imageVector = SacramentIcons.Refresh,
                                            contentDescription = "",
                                            onClick = onClickTagGenerateColors,
                                            modifier = Modifier.padding(top = spacing.padding8)
                                        )
                                    }
                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = spacing.padding32),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                SacramentButton(
                                    text = "Create",
                                    onClick = onClickTagInsert,
                                    enabled = isEnabled,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    } else {
                        when (tagsState) {
                            is UiListState.Error -> {
                                SacramentEmptyState(
                                    icon = SacramentIcons.List,
                                    title = "Error",
                                    contentDescription = "error fetching results",
                                    description = tagsState.message
                                )
                            }

                            UiListState.Idle -> {
                                SacramentEmptyState(
                                    icon = SacramentIcons.List,
                                    title = "Welcome",
                                    contentDescription = "idle fetching results",
                                    description = "Please wait while we're fetching your tags"
                                )
                            }

                            UiListState.Loading -> {
                                SacramentCenteredColumn(modifier = Modifier.fillMaxSize()) {
                                    SacramentProgressIndicator(variant = SacramentProgressVariant.Circular)
                                }
                            }

                            is UiListState.Success -> {
                                when (val result = tagsState.data) {
                                    UiSuccessState.Empty -> {
                                        SacramentEmptyState(
                                            icon = SacramentIcons.Tag,
                                            title = "Empty",
                                            contentDescription = "empty icon",
                                            description = "You don't have any tags.\nClick on the button below to create",
                                            action = {
                                                SacramentButton(
                                                    text = "create",
                                                    onClick = { },
                                                    modifier = Modifier.padding(top = spacing.padding16)
                                                )
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
                                                    Color(item.color.toColorInt()), SacramentTheme.colors.text.strong
                                                )
                                                else Pair(SacramentTheme.colors.surfaces.surface, SacramentTheme.colors.text.muted)
                                                SacramentCard(
                                                    onClick = { onTagClicked.invoke(item) },
                                                    colors = SacramentCardColors(
                                                        container = background,
                                                        border = SacramentCardDefaults.colors().border
                                                    ),
                                                    contentPadding = PaddingValues(
                                                        horizontal = spacing.padding16,
                                                        vertical = spacing.padding8
                                                    )
                                                ) {
                                                    SacramentText(
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
