package com.example.fizzbuzzapp.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable

@Composable
fun FizzBuzzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        FizzBuzzDarkColors
    } else {
        FizzBuzzLightColors
    }

    MaterialTheme(
        colors = colors,
        typography = FizzBuzzTypography,
        shapes = FizzBuzzShapes,
        content = content
    )
}