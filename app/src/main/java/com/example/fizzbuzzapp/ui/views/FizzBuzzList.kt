package com.example.fizzbuzzapp.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fizzbuzzapp.ui.viewmodels.FizzBuzzVM

@Composable
fun FizzBuzzList(fizzBuzzVM: FizzBuzzVM = viewModel ()) {}

@Preview(showSystemUi = true)
@Composable
fun FizzBuzzListPreview() {
    FizzBuzzList()
}