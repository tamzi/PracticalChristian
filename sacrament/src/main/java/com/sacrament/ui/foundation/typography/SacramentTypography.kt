package com.sacrament.ui.foundation.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle

/**
 * Sacrament typography scale with semantic naming.
 *
 * Usage (direct import - preferred):
 * ```kotlin
 * import com.sacrament.ui.foundation.typography.sacramentHeader
 * 
 * SacramentText(text = "Title", style = sacramentHeader)
 * ```
 * 
 * Usage (via theme):
 * ```kotlin
 * SacramentText(text = "Title", style = SacramentTheme.typography.sacramentHeader)
 * ```
 * 
 * Semantic typography styles (preferred):
 * - `sacramentScreenHeader` - Main screen headers
 * - `sacramentHeader` - Section headers
 * - `sacramentTitle` - Card titles and large numbers/metrics
 * - `sacramentParagraph` - Body text
 * - `sacramentLabel` - Labels and metadata
 * - `sacramentCaption` - Small supporting text
 */
@Immutable
data class SacramentTypography(
    // Semantic styles (preferred)
    val sacramentScreenHeader: TextStyle,
    val sacramentHeader: TextStyle,
    val sacramentTitle: TextStyle,
    val sacramentParagraph: TextStyle,
    val sacramentLabel: TextStyle,
    val sacramentCaption: TextStyle,
    // Size-based styles (all prefixed with "sacrament")
    val sacramentDisplayLarge: TextStyle,
    val sacramentDisplayMedium: TextStyle,
    val sacramentDisplaySmall: TextStyle,
    val sacramentHeadlineLarge: TextStyle,
    val sacramentHeadlineMedium: TextStyle,
    val sacramentHeadlineSmall: TextStyle,
    val sacramentTitleLarge: TextStyle,
    val sacramentTitleMedium: TextStyle,
    val sacramentTitleSmall: TextStyle,
    val sacramentBodyLarge: TextStyle,
    val sacramentBodyMedium: TextStyle,
    val sacramentBodySmall: TextStyle,
    val sacramentLabelLarge: TextStyle,
    val sacramentLabelMedium: TextStyle,
    val sacramentLabelSmall: TextStyle,
) {
    // Non-prefixed aliases for Material Design 3 compatibility
    val displayLarge: TextStyle get() = sacramentDisplayLarge
    val displayMedium: TextStyle get() = sacramentDisplayMedium
    val displaySmall: TextStyle get() = sacramentDisplaySmall
    val headlineLarge: TextStyle get() = sacramentHeadlineLarge
    val headlineMedium: TextStyle get() = sacramentHeadlineMedium
    val headlineSmall: TextStyle get() = sacramentHeadlineSmall
    val titleLarge: TextStyle get() = sacramentTitleLarge
    val titleMedium: TextStyle get() = sacramentTitleMedium
    val titleSmall: TextStyle get() = sacramentTitleSmall
    val bodyLarge: TextStyle get() = sacramentBodyLarge
    val bodyMedium: TextStyle get() = sacramentBodyMedium
    val bodySmall: TextStyle get() = sacramentBodySmall
    val labelLarge: TextStyle get() = sacramentLabelLarge
    val labelMedium: TextStyle get() = sacramentLabelMedium
    val labelSmall: TextStyle get() = sacramentLabelSmall
}
