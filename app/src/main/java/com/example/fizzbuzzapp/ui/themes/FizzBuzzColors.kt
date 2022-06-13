package com.example.fizzbuzzapp.ui.themes

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color


val Turquoise = Color(0xFF1EDDCD)
val Olivine = Color(0xFFA9D18E)
val IndianRed = Color(0xFFE15A58)
val MiddleRedPurple = Color(0xFF994B47)
val CharCoal = Color(0xFF033334)

val FizzBuzzLightColors = lightColors(
    primary = Olivine,
    primaryVariant = Turquoise,
    secondary = Olivine,
    secondaryVariant = MiddleRedPurple,
    onPrimary = CharCoal,
    onSecondary = CharCoal,
)

val FizzBuzzDarkColors = darkColors(
    primary = Olivine,
    primaryVariant = Turquoise,
    secondary = Olivine,
    secondaryVariant = IndianRed,
    background = CharCoal,
    surface = CharCoal,
    onPrimary = CharCoal,
    onSecondary = CharCoal,
)