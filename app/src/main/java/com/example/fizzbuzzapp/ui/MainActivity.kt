package com.example.fizzbuzzapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.fizzbuzzapp.ui.themes.FizzBuzzTheme
import com.example.fizzbuzzapp.ui.viewmodels.FizzBuzzVM
import com.example.fizzbuzzapp.ui.views.MainScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val fizzBuzzVM: FizzBuzzVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { FizzBuzzTheme {  MainScreen(fizzBuzzVM) } }
    }
}