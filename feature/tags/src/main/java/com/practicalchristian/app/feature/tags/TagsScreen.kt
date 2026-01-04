package com.practicalchristian.app.feature.tags

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material.icons.rounded.Tag
import androidx.compose.material.icons.rounded.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.ui.helpers.ItemAction
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.navigation.AppNavigator
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * Tags screen.

 */
@Composable
fun TagsScreen(
    navigator: AppNavigator,
    viewModel: TagsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    TagsScreenContent(
        state = state,
        onClickBack = { navigator.back() },
        onClickToggleBottomSheetState = viewModel::toggleBottomSheet,
        onClickTagAction = viewModel::onClickTagAction,
        onChangeTagName = viewModel::updateTagName,
        onChangeTagColor = viewModel::updateTagColor,
        onClickGenerateColors = viewModel::onClickGenerateColors,
        onClickTag = viewModel::onClickTag
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TagsScreenContent(
    state: TagsScreenUiState,
    onClickBack: () -> Unit,
    onClickToggleBottomSheetState: (Boolean) -> Unit,
    onClickTagAction: (ItemAction) -> Unit,
    onChangeTagName: (String) -> Unit,
    onChangeTagColor: (String) -> Unit,
    onClickGenerateColors: () -> Unit,
    onClickTag: (TagDomain) -> Unit,
) {
    val sheetState = rememberModalBottomSheetState(true)
    val scope = rememberCoroutineScope()
    val spacing = SacramentTheme.spacing

    LaunchedEffect(state.isBottomSheetOpen) {
        if (state.isBottomSheetOpen) {
            sheetState.show()
        } else {
            onClickToggleBottomSheetState.invoke(false)
            sheetState.hide()
        }
    }

    AnimatedVisibility(visible = state.isBottomSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    onClickToggleBottomSheetState.invoke(false)
                    sheetState.hide()
                }
            }, sheetState = sheetState
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.padding32),
                color = SacramentTheme.colors.surfaces.surface
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (state.tagCanBeUpdated) "Update" else "Create",
                            fontSize = SacramentTheme.typography.titleMedium.fontSize
                        )
                        IconButton(onClick = { onClickToggleBottomSheetState.invoke(false) }) {
                            Icon(imageVector = Icons.Rounded.Close, contentDescription = "close")
                        }
                    }
                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = spacing.padding32),
                        value = state.tag?.name ?: "",
                        onValueChange = onChangeTagName,
                        placeholder = { Text(text = "Name") },
                        maxLines = 1,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Rounded.Tag, contentDescription = ""
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
                        items(state.colors.size) {
                            val value = state.colors[it]
                            Card(
                                modifier = Modifier.padding(spacing.padding8),
                                onClick = { onChangeTagColor.invoke(value) },
                                colors = CardDefaults.cardColors(
                                    containerColor = Color(
                                        value.toColorInt()
                                    )
                                ),
                                border = if (state.tag?.color == value) BorderStroke(
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
                                    onClick = onClickGenerateColors,
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.Refresh, contentDescription = ""
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
                        AnimatedVisibility(
                            modifier = Modifier.weight(1f), visible = state.tagCanBeUpdated
                        ) {
                            Row {
                                OutlinedButton(
                                    border = BorderStroke(1.dp, SacramentTheme.colors.brand.primary),
                                    modifier = Modifier.weight(1f),
                                    onClick = { onClickTagAction.invoke(ItemAction.DELETE) },
                                ) {
                                    Text(text = "Delete")
                                }
                                Spacer(modifier = Modifier.width(spacing.padding32))
                            }
                        }
                        Button(
                            modifier = Modifier.weight(1f),
                            onClick = { onClickTagAction.invoke(if (state.tagCanBeUpdated) ItemAction.UPDATE else ItemAction.CREATE) }) {
                            Text(text = if (state.tagCanBeUpdated) "Update" else "Create")
                        }
                    }
                }
            }
        }
    }

    Scaffold(modifier = Modifier, topBar = {
        TopAppBar(title = { Text(text = "Tags") }, navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = ""
                )
            }
        })
    }, floatingActionButton = {
        AnimatedVisibility(visible = state.listState.hasData) {
            FloatingActionButton(onClick = { onClickToggleBottomSheetState.invoke(true) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = "")
            }
        }
    },
        containerColor = SacramentTheme.colors.surfaces.background,
    ) { values ->
        Column(
            modifier = Modifier
                .padding(values)
                .fillMaxSize()
        ) {
            when (val result = state.listState) {
                is UiListState.Error -> {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
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
                    when (val success = result.data) {
                        UiSuccessState.Empty -> {
                            SacramentEmptyState(
                                icon = Icons.Rounded.Tag,
                                title = "Empty",
                                contentDescription = "empty icon",
                                description = "You don't have any tags.\nClick on the button below to create",
                                action = {
                                    Button(
                                        modifier = Modifier.padding(top = spacing.padding16),
                                        onClick = { onClickToggleBottomSheetState.invoke(true) }) {
                                        Text(text = "create")
                                    }
                                }
                            )
                        }

                        is UiSuccessState.Data -> {
                            val list = success.data
                            Timber.d("COLORS -> \n$list")
                            AnimatedVisibility(visible = state.isLoading) {
                                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                            }
                            LazyVerticalStaggeredGrid(
                                columns = StaggeredGridCells.Adaptive(95.dp),
                                horizontalArrangement = Arrangement.spacedBy(spacing.padding8),
                                verticalItemSpacing = spacing.padding8,
                                contentPadding = PaddingValues(spacing.padding8)
                            ) {
                                items(list) { item ->
                                    Card(
                                        onClick = { onClickTag.invoke(item) },
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color(
                                                item.color.toColorInt()
                                            )
                                        )
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(
                                                horizontal = spacing.padding16,
                                                vertical = spacing.padding8
                                            ), text = item.name, color = Color.Black
                                        )
                                    }
                                }
                            }
                        }

                        is UiSuccessState.Data<*> -> TODO()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TagsScreenPreview(@PreviewParameter(TagsScreenStateProvider::class) state: TagsScreenUiState) {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        TagsScreenContent(
            state = state,
            onClickBack = {},
            onClickToggleBottomSheetState = {},
            onClickTagAction = {},
            onChangeTagName = {},
            onChangeTagColor = {},
            onClickGenerateColors = {},
            onClickTag = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TagsScreenLoadingPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        TagsScreenContent(
            state = TagsScreenUiState(
            listState = UiListState.Loading, tag = null, colors = emptyList(), isLoading = false
        ),
            onClickBack = {},
            onClickToggleBottomSheetState = {},
            onClickTagAction = {},
            onChangeTagName = {},
            onChangeTagColor = {},
            onClickGenerateColors = {},
            onClickTag = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TagsScreenEmptyPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        TagsScreenContent(
            state = TagsScreenUiState(
            listState = UiListState.Success(UiSuccessState.Empty),
            tag = null,
            colors = emptyList(),
            isLoading = false
        ),
            onClickBack = {},
            onClickToggleBottomSheetState = {},
            onClickTagAction = {},
            onChangeTagName = {},
            onChangeTagColor = {},
            onClickGenerateColors = {},
            onClickTag = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TagsScreenSuccessPreview() {
    val sampleTags = listOf(
        TagDomain(id = 1, name = "Work", color = "#FF5722"),
        TagDomain(id = 2, name = "Personal", color = "#2196F3"),
        TagDomain(id = 3, name = "Health", color = "#4CAF50"),
        TagDomain(id = 4, name = "Learning", color = "#FF9800"),
        TagDomain(id = 5, name = "Shopping", color = "#9C27B0")
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        TagsScreenContent(
            state = TagsScreenUiState(
            listState = UiListState.Success(UiSuccessState.Data(sampleTags)),
            tag = null,
            colors = listOf("#FF5722", "#2196F3", "#4CAF50", "#FF9800", "#9C27B0"),
            isLoading = false
        ),
            onClickBack = {},
            onClickToggleBottomSheetState = {},
            onClickTagAction = {},
            onChangeTagName = {},
            onChangeTagColor = {},
            onClickGenerateColors = {},
            onClickTag = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TagsScreenBottomSheetPreview() {
    val sampleColors = listOf(
        "#FF5722",
        "#2196F3",
        "#4CAF50",
        "#FF9800",
        "#9C27B0",
        "#F44336",
        "#3F51B5",
        "#009688",
        "#CDDC39",
        "#E91E63"
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        TagsScreenContent(
            state = TagsScreenUiState(
            listState = UiListState.Success(UiSuccessState.Data(emptyList())),
            tag = TagDomain(id = -1, name = "Sample Tag", color = "#FF5722"),
            colors = sampleColors,
            isLoading = false
        ),
            onClickBack = {},
            onClickToggleBottomSheetState = {},
            onClickTagAction = {},
            onChangeTagName = {},
            onChangeTagColor = {},
            onClickGenerateColors = {},
            onClickTag = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TagsScreenUpdateModePreview() {
    val sampleColors = listOf(
        "#FF5722",
        "#2196F3",
        "#4CAF50",
        "#FF9800",
        "#9C27B0",
        "#F44336",
        "#3F51B5",
        "#009688",
        "#CDDC39",
        "#E91E63"
    )

    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        TagsScreenContent(
            state = TagsScreenUiState(
            listState = UiListState.Success(UiSuccessState.Data(emptyList())),
            tag = TagDomain(id = 1, name = "Existing Tag", color = "#2196F3"),
            colors = sampleColors,
            isLoading = false
        ),
            onClickBack = {},
            onClickToggleBottomSheetState = {},
            onClickTagAction = {},
            onChangeTagName = {},
            onChangeTagColor = {},
            onClickGenerateColors = {},
            onClickTag = {})
    }
}

class TagsScreenStateProvider : PreviewParameterProvider<TagsScreenUiState> {
    override val values = sequenceOf(
        TagsScreenUiState(
            listState = UiListState.Loading, tag = null, colors = emptyList(), isLoading = false
        ), TagsScreenUiState(
            listState = UiListState.Success(UiSuccessState.Empty),
            tag = null,
            colors = emptyList(),
            isLoading = false
        ), TagsScreenUiState(
            listState = UiListState.Error("Failed to load tags"),
            tag = null,
            colors = emptyList(),
            isLoading = false
        )
    )
}
