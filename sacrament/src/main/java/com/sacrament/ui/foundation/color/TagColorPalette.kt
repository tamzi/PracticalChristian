package com.sacrament.ui.foundation.color

/**
 * Tag color palette for ViewModel and non-Composable contexts.
 * This references the same colors defined in the design system.
 */
object TagColorPalette {
    private val tagColors = listOf(
        ErrorRed, WarningOrange, Amber, Yellow, Lime,
        SuccessGreen, Teal, Cyan, InfoBlue, Indigo,
        Purple, DeepPurple, Pink, Brown, Gray
    )

    /**
     * All available tag colors as hex strings.
     * Generated dynamically from the palette colors to ensure consistency.
     */
    val colors: List<String>
        get() = tagColors.map { color ->
            // Compose Color stores RGB as floats (0-1), convert to hex
            val red = (color.red * 255).toInt()
            val green = (color.green * 255).toInt()
            val blue = (color.blue * 255).toInt()
            "#%02X%02X%02X".format(red, green, blue)
        }
}
