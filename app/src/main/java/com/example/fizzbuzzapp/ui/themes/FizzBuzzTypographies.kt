package com.example.fizzbuzzapp.ui.themes

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.fizzbuzzapp.R

private val fizzBuzzFontFamily = FontFamily(
    fonts = listOf(
        Font(
            resId = R.font.roboto_light,
            weight = FontWeight.W300,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_lightitalic,
            weight = FontWeight.W300,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.roboto_regular,
            weight = FontWeight.W400,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_italic,
            weight = FontWeight.W400,
            style = FontStyle.Italic
        ),
        Font(
            resId = R.font.roboto_bold,
            weight = FontWeight.W700,
            style = FontStyle.Normal
        ),
        Font(
            resId = R.font.roboto_bolditalic,
            weight = FontWeight.W700,
            style = FontStyle.Italic
        )
    )
)

val FizzBuzzTypography = Typography(
    h3 = TextStyle(
        fontFamily = fizzBuzzFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 34.sp,
        textAlign = TextAlign.Center
    ),
    h4 = TextStyle(
        fontFamily = fizzBuzzFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 34.sp
    ),
    body1 = TextStyle(
        fontFamily = fizzBuzzFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    button = TextStyle(
        fontFamily = fizzBuzzFontFamily,
        fontWeight = FontWeight.Light,
        fontSize = 24.sp
    ),
)