package com.sacrament.ui.components.surface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sacrament.ui.foundation.Bar
import com.sacrament.ui.foundation.SacramentTheme
import com.sacrament.ui.primitives.SacramentText

/**
 * Modal bottom sheet using Compose foundation with Sacrament styling.
 *
 * Note: This currently uses Material3's ModalBottomSheet as a bridge until
 * we can implement a fully Material-free version using Compose foundation.
 * The visual styling follows Sacrament design tokens.
 */
@Composable
fun SacramentModalBottomSheet(
    onDismissRequest: () -> Unit,
    sheetState: androidx.compose.material3.SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val colors = SacramentSheetDefaults.colors()
    val shape = SacramentSheetDefaults.shape()

    ModalBottomSheet(
        onDismissRequest = onDismissRequest,
        sheetState = sheetState,
        containerColor = colors.container,
        dragHandle = null,
        shape = shape,
        modifier = modifier,
    ) {
        content()
    }
}

/**
 * Helper to create a modal bottom sheet state.
 */
@Composable
fun rememberSacramentModalBottomSheetState(
    skipPartiallyExpanded: Boolean = true,
): androidx.compose.material3.SheetState {
    return rememberModalBottomSheetState(skipPartiallyExpanded = skipPartiallyExpanded)
}

@Preview
@Composable
private fun SacramentModalBottomSheetPreview() {
    SacramentTheme(navigationBar = Bar.SURFACE, statusBar = Bar.BACKGROUND) {
        // Preview is simplified - actual usage requires state management
        SacramentText(text = "ModalBottomSheet preview")
    }
}
