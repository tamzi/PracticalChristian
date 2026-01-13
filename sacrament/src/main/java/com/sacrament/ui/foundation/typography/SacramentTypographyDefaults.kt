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
