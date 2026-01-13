package com.sacrament.ui.foundation.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sacrament.ui.R

/**
 * Product Sans font family with all weight variants.
 */
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

/**
 * Sacrament logo font family.
 */
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
