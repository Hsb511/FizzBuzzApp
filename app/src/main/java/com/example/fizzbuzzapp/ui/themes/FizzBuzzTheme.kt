package com.example.fizzbuzzapp.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors()
private val LightColorPalette = lightColors()

@Composable
fun FizzBuzzTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    // TODO CHANGE THE DEFAULT THEME VALUES
    val colors = if (darkTheme) { DarkColorPalette } else { LightColorPalette }

    MaterialTheme(colors = colors, typography = Typography(), shapes = Shapes(), content = content)
}