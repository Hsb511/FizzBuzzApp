package com.example.fizzbuzzapp.domain.usecases

import javax.inject.Inject
import kotlin.math.roundToInt

class FilterDividerValuesUseCase @Inject constructor() {
    fun execute(int1: String) = int1.toFloatOrNull()?.roundToInt()?.toString()?.let {
        if (it == "0") {
            ""
        } else {
            it
        }
    } ?: ""
}