package com.example.fizzbuzzapp.domain.usecases

import androidx.compose.ui.text.input.TextFieldValue
import javax.inject.Inject

class FilterDividerValuesUseCase @Inject constructor() {
    fun execute(int1: TextFieldValue) = int1.text.toIntOrNull()?.let {
        if (it <= 0) {
            ""
        } else {
            it.toString()
        }
    } ?: ""
}