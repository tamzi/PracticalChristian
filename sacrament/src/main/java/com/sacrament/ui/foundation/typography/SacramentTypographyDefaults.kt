package com.sacrament.ui.foundation.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Default implementation of SacramentTypography.
 * 
 * Semantic styles are defined here, while size-based styles are referenced
 * from SacramentTypographyScale.
 */
val DefaultSacramentTypography = SacramentTypography(
    // Semantic styles (preferred)
    sacramentScreenHeader = TextStyle(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    ),
    sacramentHeader = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    ),
    sacramentTitle = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    ),
    sacramentParagraph = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = ProductSansFamily,
    ),
    sacramentLabel = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    ),
    sacramentCaption = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = ProductSansFamily,
    ),
    // Size-based styles from SacramentTypographyScale
    sacramentDisplayLarge = SacramentTypographyScale.sacramentDisplayLarge,
    sacramentDisplayMedium = SacramentTypographyScale.sacramentDisplayMedium,
    sacramentDisplaySmall = SacramentTypographyScale.sacramentDisplaySmall,
    sacramentHeadlineLarge = SacramentTypographyScale.sacramentHeadlineLarge,
    sacramentHeadlineMedium = SacramentTypographyScale.sacramentHeadlineMedium,
    sacramentHeadlineSmall = SacramentTypographyScale.sacramentHeadlineSmall,
    sacramentTitleLarge = SacramentTypographyScale.sacramentTitleLarge,
    sacramentTitleMedium = SacramentTypographyScale.sacramentTitleMedium,
    sacramentTitleSmall = SacramentTypographyScale.sacramentTitleSmall,
    sacramentBodyLarge = SacramentTypographyScale.sacramentBodyLarge,
    sacramentBodyMedium = SacramentTypographyScale.sacramentBodyMedium,
    sacramentBodySmall = SacramentTypographyScale.sacramentBodySmall,
    sacramentLabelLarge = SacramentTypographyScale.sacramentLabelLarge,
    sacramentLabelMedium = SacramentTypographyScale.sacramentLabelMedium,
    sacramentLabelSmall = SacramentTypographyScale.sacramentLabelSmall,
)

/**
 * Top-level typography properties for direct use.
 * 
 * Usage:
 * ```kotlin
 * import com.sacrament.ui.foundation.typography.sacramentHeader
 * 
 * SacramentText(
 *     text = "Title",
 *     style = sacramentHeader
 * )
 * ```
 */

// Semantic styles (preferred)
val sacramentScreenHeader: TextStyle
    get() = DefaultSacramentTypography.sacramentScreenHeader

val sacramentHeader: TextStyle
    get() = DefaultSacramentTypography.sacramentHeader

val sacramentTitle: TextStyle
    get() = DefaultSacramentTypography.sacramentTitle

val sacramentParagraph: TextStyle
    get() = DefaultSacramentTypography.sacramentParagraph

val sacramentLabel: TextStyle
    get() = DefaultSacramentTypography.sacramentLabel

val sacramentCaption: TextStyle
    get() = DefaultSacramentTypography.sacramentCaption

// Size-based styles
val sacramentDisplayLarge: TextStyle
    get() = DefaultSacramentTypography.sacramentDisplayLarge

val sacramentDisplayMedium: TextStyle
    get() = DefaultSacramentTypography.sacramentDisplayMedium

val sacramentDisplaySmall: TextStyle
    get() = DefaultSacramentTypography.sacramentDisplaySmall

val sacramentHeadlineLarge: TextStyle
    get() = DefaultSacramentTypography.sacramentHeadlineLarge

val sacramentHeadlineMedium: TextStyle
    get() = DefaultSacramentTypography.sacramentHeadlineMedium

val sacramentHeadlineSmall: TextStyle
    get() = DefaultSacramentTypography.sacramentHeadlineSmall

val sacramentTitleLarge: TextStyle
    get() = DefaultSacramentTypography.sacramentTitleLarge

val sacramentTitleMedium: TextStyle
    get() = DefaultSacramentTypography.sacramentTitleMedium

val sacramentTitleSmall: TextStyle
    get() = DefaultSacramentTypography.sacramentTitleSmall

val sacramentBodyLarge: TextStyle
    get() = DefaultSacramentTypography.sacramentBodyLarge

val sacramentBodyMedium: TextStyle
    get() = DefaultSacramentTypography.sacramentBodyMedium

val sacramentBodySmall: TextStyle
    get() = DefaultSacramentTypography.sacramentBodySmall

val sacramentLabelLarge: TextStyle
    get() = DefaultSacramentTypography.sacramentLabelLarge

val sacramentLabelMedium: TextStyle
    get() = DefaultSacramentTypography.sacramentLabelMedium

val sacramentLabelSmall: TextStyle
    get() = DefaultSacramentTypography.sacramentLabelSmall
