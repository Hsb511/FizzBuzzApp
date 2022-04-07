package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fizzbuzzapp.ui.viewmodels.FizzBuzzVM
import androidx.hilt.navigation.compose.hiltViewModel



@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = "fizzBuzzForm") {
            composable(route = "fizzBuzzForm") {
                val fizzBuzzVM = hiltViewModel<FizzBuzzVM>()
                FizzBuzzForm(fizzBuzzVM)
            }
            composable(route = "fizzBuzzList") {
                val fizzBuzzVM = hiltViewModel<FizzBuzzVM>()
                FizzBuzzList(fizzBuzzVM)
            }
        }
    }
}