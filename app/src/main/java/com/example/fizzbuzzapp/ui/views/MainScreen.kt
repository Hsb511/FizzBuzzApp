package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fizzbuzzapp.ui.viewmodels.FizzBuzzVM


@Composable
fun MainScreen(fizzBuzzVM: FizzBuzzVM) {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = "fizzBuzzForm") {
            composable(route = "fizzBuzzForm") {
                FizzBuzzForm(fizzBuzzVM, navController)
            }
            composable(route = "fizzBuzzList") {
                FizzBuzzList(fizzBuzzVM,  navController)
            }
        }
    }
}