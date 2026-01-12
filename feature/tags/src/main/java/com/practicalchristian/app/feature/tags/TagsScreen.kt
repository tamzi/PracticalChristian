package com.practicalchristian.app.feature.tags

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.sacrament.ui.components.action.SacramentButton
import com.sacrament.ui.components.action.SacramentButtonVariant
import com.sacrament.ui.components.action.SacramentFab
import com.sacrament.ui.components.action.SacramentIconButton
import com.sacrament.ui.components.feedback.SacramentProgressIndicator
import com.sacrament.ui.components.feedback.SacramentProgressVariant
import com.sacrament.ui.components.input.SacramentTextField
import com.sacrament.ui.components.navigation.SacramentTopAppBar
import com.sacrament.ui.components.surface.SacramentCard
import com.sacrament.ui.components.surface.SacramentCardColors
import com.sacrament.ui.components.surface.SacramentCardDefaults
import com.sacrament.ui.components.surface.SacramentModalBottomSheet
import com.sacrament.ui.components.surface.rememberSacramentModalBottomSheetState
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.foundation.icon.SacramentIcons
import com.sacrament.ui.patterns.SacramentEmptyState
import com.sacrament.ui.primitives.SacramentCenteredColumn
import com.sacrament.ui.primitives.SacramentIcon
import com.sacrament.ui.primitives.SacramentText
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
    val sheetState = rememberSacramentModalBottomSheetState(skipPartiallyExpanded = true)
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
        SacramentModalBottomSheet(
            onDismissRequest = {
                scope.launch {
                    onClickToggleBottomSheetState.invoke(false)
                    sheetState.hide()
                }
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.padding32)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SacramentText(
                        text = if (state.tagCanBeUpdated) "Update" else "Create",
                        style = SacramentTheme.typography.titleMedium
                    )
                    SacramentIconButton(
                        imageVector = SacramentIcons.Close,
                        contentDescription = "close",
                        onClick = { onClickToggleBottomSheetState.invoke(false) }
                    )
                }
                SacramentTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = spacing.padding32),
                    value = state.tag?.name ?: "",
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
                        items(state.colors.size) {
                            val value = state.colors[it]
                            SacramentCard(
                                modifier = Modifier.padding(spacing.padding8),
                                onClick = { onChangeTagColor.invoke(value) },
                                colors = if (state.tag?.color == value) {
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
                                    modifier = Modifier.padding(top = spacing.padding8),
                                    imageVector = SacramentIcons.Refresh,
                                    contentDescription = "",
                                    onClick = onClickGenerateColors
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
                        AnimatedVisibility(
                            modifier = Modifier.weight(1f), visible = state.tagCanBeUpdated
                        ) {
                            Row {
                                SacramentButton(
                                    text = "Delete",
                                    onClick = { onClickTagAction.invoke(ItemAction.DELETE) },
                                    variant = SacramentButtonVariant.Outlined,
                                    modifier = Modifier.weight(1f)
                                )
                                Spacer(modifier = Modifier.width(spacing.padding32))
                            }
                        }
                        SacramentButton(
                            text = if (state.tagCanBeUpdated) "Update" else "Create",
                            onClick = { onClickTagAction.invoke(if (state.tagCanBeUpdated) ItemAction.UPDATE else ItemAction.CREATE) },
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }

    SacramentScreenScaffold(
        topBar = {
            SacramentTopAppBar(
                title = { SacramentText(text = "Tags", style = SacramentTheme.typography.titleSmall) },
                navigationIcon = {
                    SacramentIconButton(
                        imageVector = SacramentIcons.ArrowBack,
                        contentDescription = "",
                        onClick = onClickBack
                    )
                }
            )
        },
        floatingActionButton = {
            AnimatedVisibility(visible = state.listState.hasData) {
                SacramentFab(
                    imageVector = SacramentIcons.Add,
                    contentDescription = "",
                    onClick = { onClickToggleBottomSheetState.invoke(true) }
                )
            }
        }
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
                    when (val success = result.data) {
                        UiSuccessState.Empty -> {
                            SacramentEmptyState(
                                icon = SacramentIcons.Tag,
                                title = "Empty",
                                contentDescription = "empty icon",
                                description = "You don't have any tags.\nClick on the button below to create",
                                action = {
                                    SacramentButton(
                                        text = "create",
                                        onClick = { onClickToggleBottomSheetState.invoke(true) },
                                        modifier = Modifier.padding(top = spacing.padding16)
                                    )
                                }
                            )
                        }

                        is UiSuccessState.Data -> {
                            val list = success.data
                            Timber.d("COLORS -> \n$list")
                            AnimatedVisibility(visible = state.isLoading) {
                                SacramentProgressIndicator(
                                    variant = SacramentProgressVariant.Linear,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                            LazyVerticalStaggeredGrid(
                                columns = StaggeredGridCells.Adaptive(95.dp),
                                horizontalArrangement = Arrangement.spacedBy(spacing.padding8),
                                verticalItemSpacing = spacing.padding8,
                                contentPadding = PaddingValues(spacing.padding8)
                            ) {
                                items(list) { item ->
                                    SacramentCard(
                                        onClick = { onClickTag.invoke(item) },
                                        colors = SacramentCardDefaults.colors(
                                            container = Color(item.color.toColorInt())
                                        ),
                                        contentPadding = PaddingValues(
                                            horizontal = spacing.padding16,
                                            vertical = spacing.padding8
                                        )
                                    ) {
                                        SacramentText(
                                            text = item.name,
                                            color = SacramentTheme.colors.text.strong
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
