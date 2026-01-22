package com.sacrament.ui.foundation.color

/**
 * Tag color palette for ViewModel and non-Composable contexts.
 * This references the same colors defined in the design system.
 */
object TagColorPalette {
    // Reuse the canonical tag color definitions from the design system
    private val tagColors = SacramentTagColors(
        red = ErrorRed,
        orange = WarningOrange,
        amber = Amber,
        yellow = Yellow,
        lime = Lime,
        green = SuccessGreen,
        teal = Teal,
        cyan = Cyan,
        blue = InfoBlue,
        indigo = Indigo,
        purple = Purple,
        deepPurple = DeepPurple,
        pink = Pink,
        brown = Brown,
        gray = Gray,
    )

    /**
     * All available tag colors as hex strings.
     * Generated dynamically from the palette colors to ensure consistency.
     */
    val colors: List<String>
        get() = tagColors.hexValues
}
