package com.example.fizzbuzzapp.ui.themes

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color


val Turquoise = Color(0xFF1EDDCD)
val Olivine = Color(0xFFA9D18E)
val IndianRed = Color(0xFFE15A58)
val MiddleRedPurple = Color(0xFF994B47)
val SandyBrown = Color(0xFFF4A259)
val GoldCrayola = Color(0xFFEDC298)
val CharCoal = Color(0xFF033334)

val FizzBuzzLightColors = lightColors(
    primary = Olivine,
    primaryVariant = Turquoise,
    secondary = SandyBrown,
    secondaryVariant = MiddleRedPurple,
    onPrimary = Color.White
)

val FizzBuzzDarkColors = darkColors(
    primary = Olivine,
    primaryVariant = Turquoise,
    secondary = GoldCrayola,
    secondaryVariant = IndianRed,
    background = CharCoal,
    surface = CharCoal,
    onPrimary = Color.Black
)