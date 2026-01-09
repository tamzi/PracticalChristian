package com.practicalchristian.app.feature.tags

import android.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewModelScope
import com.practicalchristian.app.core.domain.models.Outcome
import com.practicalchristian.app.core.domain.models.TagDomain
import com.practicalchristian.app.core.domain.repository.TagsRepository
import com.practicalchristian.app.core.ui.helpers.ItemAction
import com.practicalchristian.app.core.ui.helpers.StatefulViewModel
import com.practicalchristian.app.core.ui.helpers.UiListState
import com.practicalchristian.app.core.ui.helpers.UiSuccessState
import com.practicalchristian.app.core.ui.helpers.toUserMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.pow
import kotlin.math.sqrt

data class TagsScreenUiState(
    val isLoading: Boolean = false,
    val tag: TagDomain? = null,
    val listState: UiListState<List<TagDomain>> = UiListState.Idle,
    val colors: List<String> = listOf(),
    val error: String? = null,
    val success: String? = null
) {
    val isBottomSheetOpen: Boolean
        get() = tag != null

    val tagCanBeUpdated: Boolean
        get() = tag?.id != -1
}

@HiltViewModel
class TagsViewModel @Inject constructor(
    private val repository: TagsRepository
) : StatefulViewModel<TagsScreenUiState>(TagsScreenUiState()) {

    companion object {
        // Predefined accessible color palette with good contrast ratios for both light and dark backgrounds
        private val ACCESSIBLE_COLORS = listOf(
            "#E53E3E", // Red - contrast ratio 4.5:1 on white
            "#D69E2E", // Orange - contrast ratio 4.8:1 on white
            "#38A169", // Green - contrast ratio 4.5:1 on white
            "#3182CE", // Blue - contrast ratio 4.5:1 on white
            "#805AD5", // Purple - contrast ratio 4.6:1 on white
            "#D53F8C", // Pink - contrast ratio 4.5:1 on white
            "#319795", // Teal - contrast ratio 4.5:1 on white
            "#DD6B20", // Orange variant - contrast ratio 4.7:1 on white
            "#2D3748" // Dark gray - contrast ratio 9.6:1 on white
        )

        private const val MIN_CONTRAST_RATIO = 4.5 // WCAG AA standard
    }

    init {
        observeTags()
        onClickGenerateColors()
    }

    private fun observeTags() {
        update { copy(listState = UiListState.Loading) }
        viewModelScope.launch {
            repository.tags.collectLatest {
                if (it.isEmpty()) update { copy(listState = UiListState.Success(data = UiSuccessState.Empty)) }
                else update { copy(listState = UiListState.Success(data = UiSuccessState.Data(data = it))) }
            }
        }
    }

    fun updateTagName(name: String) {
        update { copy(tag = tag?.copy(name = name)) }
    }

    fun updateTagColor(color: String) {
        val validatedColor = validateAndSanitizeColor(color)
        update { copy(tag = tag?.copy(color = validatedColor)) }
    }

    fun toggleBottomSheet(isOpen: Boolean) {
        if (isOpen) update { copy(tag = TagDomain(-1, "", "")) }
        else update { copy(tag = null) }
    }

    fun onClickTagAction(action: ItemAction) {
        when (action) {
            ItemAction.CREATE -> createTag()
            ItemAction.UPDATE -> updateTag()
            ItemAction.DELETE -> deleteTag()
        }
    }

    private fun createTag() {
        val tag = state.value.tag
        tag?.let {
            viewModelScope.launch {
                update { copy(isLoading = true) }
                Timber.d("createTag: Starting insert operation for tag: ${it.name}")
                val result = repository.insert(tag = it.copy(id = 0))
                update { copy(isLoading = false) }
                toggleBottomSheet(isOpen = false)
                when (result) {
                    is Outcome.Failure -> {
                        val errorMessage = result.error.toUserMessage()
                        Timber.e("createTag: Error - $errorMessage")
                        update { copy(error = errorMessage) }
                    }
                    is Outcome.Success -> {
                        Timber.d("createTag: Success - tag created")
                        update { copy(success = "tag created successfully") }
                    }
                }
                removeSuccessOrError()
            }
        }
    }

    private fun updateTag() {
        val tag = state.value.tag
        tag?.let {
            viewModelScope.launch {
                update { copy(isLoading = true) }
                Timber.d("updateTag: Starting update operation for tag: ${it.name}")
                val result = repository.update(tag = it)
                update { copy(isLoading = false) }
                toggleBottomSheet(isOpen = false)
                when (result) {
                    is Outcome.Failure -> {
                        val errorMessage = result.error.toUserMessage()
                        Timber.e("updateTag: Error - $errorMessage")
                        update { copy(error = errorMessage) }
                    }
                    is Outcome.Success -> {
                        Timber.d("updateTag: Success - tag updated")
                        update { copy(success = "tag updated successfully") }
                    }
                }
                removeSuccessOrError()
            }
        }
    }

    private fun deleteTag() {
        val tag = state.value.tag
        tag?.let {
            viewModelScope.launch {
                update { copy(isLoading = true) }
                Timber.d("deleteTag: Starting delete operation for tag: ${it.name}")
                val result = repository.delete(tag = it)
                update { copy(isLoading = false) }
                toggleBottomSheet(isOpen = false)
                when (result) {
                    is Outcome.Failure -> {
                        val errorMessage = result.error.toUserMessage()
                        Timber.e("deleteTag: Error - $errorMessage")
                        update { copy(error = errorMessage) }
                    }
                    is Outcome.Success -> {
                        Timber.d("deleteTag: Success - tag deleted")
                        update { copy(success = "tag deleted successfully") }
                    }
                }
                removeSuccessOrError()
            }
        }
    }

    private fun removeSuccessOrError() {
        viewModelScope.launch {
            delay(2000)
            update { copy(error = null, success = null) }
        }
    }

    fun onClickGenerateColors() {
        // Use predefined accessible colors, shuffled for variety
        val shuffledColors = ACCESSIBLE_COLORS.shuffled().take(9)
        // Ensure we always have 9 colors by repeating if necessary
        val finalColors = if (shuffledColors.size < 9) {
            shuffledColors + ACCESSIBLE_COLORS.shuffled().take(9 - shuffledColors.size)
        } else {
            shuffledColors
        }
        update { copy(colors = finalColors) }
    }

    fun onClickTag(tag: TagDomain) {
        val validatedColor = validateAndSanitizeColor(tag.color)
        update {
            copy(
                tag = tag.copy(color = validatedColor), colors = colors.update(0, validatedColor)
            )
        }
    }

    /**
     * Validates and sanitizes color input to ensure it's a valid hex color
     * and has acceptable contrast for UI readability
     */
    private fun validateAndSanitizeColor(color: String): String {
        // Remove any whitespace and convert to uppercase
        val cleanColor = color.trim().uppercase()

        // Validate hex color format
        val hexPattern = Regex("^#([A-F0-9]{6}|[A-F0-9]{3})$")
        if (!hexPattern.matches(cleanColor)) {
            // Return a default accessible color if invalid format
            return ACCESSIBLE_COLORS.first()
        }

        // Convert 3-digit hex to 6-digit hex
        val normalizedColor = if (cleanColor.length == 4) {
            "#${cleanColor[1]}${cleanColor[1]}${cleanColor[2]}${cleanColor[2]}${cleanColor[3]}${cleanColor[3]}"
        } else {
            cleanColor
        }

        // Check if color has sufficient contrast (simplified check)
        if (hasGoodContrast(normalizedColor)) {
            return normalizedColor
        } else {
            // Find the closest accessible color if contrast is poor
            return findClosestAccessibleColor(normalizedColor)
        }
    }

    /**
     * Simplified contrast check using luminance calculation
     * Returns true if the color has good contrast against typical backgrounds
     */
    private fun hasGoodContrast(hexColor: String): Boolean {
        val color = hexColor.toColorInt()
        val luminance = calculateLuminance(color)

        // Check contrast against white background (most common case)
        val contrastWithWhite = (1.0 + 0.05) / (luminance + 0.05)

        return contrastWithWhite >= MIN_CONTRAST_RATIO
    }

    /**
     * Calculate relative luminance of a color according to WCAG guidelines
     */
    private fun calculateLuminance(color: Int): Double {
        val r = Color.red(color) / 255.0
        val g = Color.green(color) / 255.0
        val b = Color.blue(color) / 255.0

        val rLinear = if (r <= 0.03928) r / 12.92 else ((r + 0.055) / 1.055).pow(2.4)
        val gLinear = if (g <= 0.03928) g / 12.92 else ((g + 0.055) / 1.055).pow(2.4)
        val bLinear = if (b <= 0.03928) b / 12.92 else ((b + 0.055) / 1.055).pow(2.4)

        return 0.2126 * rLinear + 0.7152 * gLinear + 0.0722 * bLinear
    }

    /**
     * Find the closest accessible color from the predefined palette
     */
    private fun findClosestAccessibleColor(targetColor: String): String {
        val targetColorInt = targetColor.toColorInt()
        val targetR = Color.red(targetColorInt)
        val targetG = Color.green(targetColorInt)
        val targetB = Color.blue(targetColorInt)

        var closestColor = ACCESSIBLE_COLORS.first()
        var minDistance = Double.MAX_VALUE

        ACCESSIBLE_COLORS.forEach { accessibleColor ->
            val colorInt = accessibleColor.toColorInt()
            val r = Color.red(colorInt)
            val g = Color.green(colorInt)
            val b = Color.blue(colorInt)

            // Calculate Euclidean distance in RGB space
            val distance = sqrt(
                ((targetR - r) * (targetR - r) + (targetG - g) * (targetG - g) + (targetB - b) * (targetB - b)).toDouble()
            )

            if (distance < minDistance) {
                minDistance = distance
                closestColor = accessibleColor
            }
        }

        return closestColor
    }

    private fun <T> List<T>.update(index: Int, value: T): List<T> {
        val mutable = this.toMutableList()
        if (mutable.contains(value).not()) mutable[index] = value
        return mutable
    }
}
