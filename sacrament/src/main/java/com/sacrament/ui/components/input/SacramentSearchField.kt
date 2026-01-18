package com.sacrament.ui.components.input

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction

/**
 * Search input built on SacramentTextField.
 */
@Composable
fun SacramentSearchField(
    modifier: Modifier = Modifier,
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: ((String) -> Unit)? = null,
    size: SacramentTextFieldSize = SacramentTextFieldSize.Medium,
    enabled: Boolean = true,
    placeholder: String = "Search",
) {
    SacramentTextField(
        value = query,
        onValueChange = onQueryChange,
        size = size,
        enabled = enabled,
        leadingIcon = Icons.Rounded.Search,
        placeholder = placeholder,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch?.invoke(query) }),
        modifier = modifier,
    )
}
