package com.sacrament.ui.foundation.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

/**
 * Size-based typography scale styles.
 * All styles are prefixed with "sacrament" for consistency.
 */
object SacramentTypographyScale {
    val sacramentDisplayLarge = TextStyle(
        fontSize = 48.sp,
        lineHeight = 56.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    )
    val sacramentDisplayMedium = TextStyle(
        fontSize = 40.sp,
        lineHeight = 48.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    )
    val sacramentDisplaySmall = TextStyle(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    )
    val sacramentHeadlineLarge = TextStyle(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = ProductSansFamily,
    )
    val sacramentHeadlineMedium = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = ProductSansFamily,
    )
    val sacramentHeadlineSmall = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = ProductSansFamily,
    )
    val sacramentTitleLarge = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    )
    val sacramentTitleMedium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    )
    val sacramentTitleSmall = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    )
    val sacramentBodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = ProductSansFamily,
    )
    val sacramentBodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = ProductSansFamily,
    )
    val sacramentBodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = ProductSansFamily,
    )
    val sacramentLabelLarge = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    )
    val sacramentLabelMedium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    )
    val sacramentLabelSmall = TextStyle(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    )
}
