package com.sacrament.ui.foundation.typography

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sacrament.ui.R

val ProductSansFamily = FontFamily(
    Font(R.font.product_sans_thin, FontWeight.Thin),
    Font(R.font.product_sans_thin_italic, FontWeight.Thin, FontStyle.Italic),
    Font(R.font.product_sans_light, FontWeight.Light),
    Font(R.font.product_sans_light_italic, FontWeight.Light, FontStyle.Italic),
    Font(R.font.product_sans, FontWeight.Normal),
    Font(R.font.product_sans_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.product_sans_medium, FontWeight.Medium),
    Font(R.font.product_sans_medium_italic, FontWeight.Medium, FontStyle.Italic),
    Font(R.font.product_sans_bold, FontWeight.Bold),
    Font(R.font.product_sans_bold, FontWeight.Bold, FontStyle.Italic),
    Font(R.font.product_sans_black, FontWeight.Black),
    Font(R.font.product_sans_black_italic, FontWeight.Black, FontStyle.Italic),
)

val SacramentLogoFamily = FontFamily(
    Font(R.font.poppins_regular, FontWeight.Normal)
)

/**
 * Brand wordmark style. Use sparingly for the app logo or splash branding only.
 */
val SacramentLogoStyle = TextStyle(
    fontFamily = SacramentLogoFamily,
    fontSize = 48.sp,
    fontWeight = FontWeight.Normal
)

/**
 * Sacrament typography scale.
 *
 * Usage:
 * `Text("Title", style = SacramentTheme.typography.titleLarge)`
 */
@Immutable
data class SacramentTypography(
    val displayLarge: TextStyle,
    val displayMedium: TextStyle,
    val displaySmall: TextStyle,
    val headlineLarge: TextStyle,
    val headlineMedium: TextStyle,
    val headlineSmall: TextStyle,
    val titleLarge: TextStyle,
    val titleMedium: TextStyle,
    val titleSmall: TextStyle,
    val bodyLarge: TextStyle,
    val bodyMedium: TextStyle,
    val bodySmall: TextStyle,
    val labelLarge: TextStyle,
    val labelMedium: TextStyle,
    val labelSmall: TextStyle,
)

val DefaultSacramentTypography = SacramentTypography(
    displayLarge = TextStyle(
        fontSize = 48.sp,
        lineHeight = 56.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    ),
    displayMedium = TextStyle(
        fontSize = 40.sp,
        lineHeight = 48.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    ),
    displaySmall = TextStyle(
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = ProductSansFamily,
    ),
    headlineLarge = TextStyle(
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = ProductSansFamily,
    ),
    headlineMedium = TextStyle(
        fontSize = 24.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = ProductSansFamily,
    ),
    headlineSmall = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = ProductSansFamily,
    ),
    titleLarge = TextStyle(
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    ),
    titleMedium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    ),
    titleSmall = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = ProductSansFamily,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = ProductSansFamily,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = ProductSansFamily,
    ),
    labelLarge = TextStyle(
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    ),
    labelMedium = TextStyle(
        fontSize = 12.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    ),
    labelSmall = TextStyle(
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = ProductSansFamily,
    ),
)
