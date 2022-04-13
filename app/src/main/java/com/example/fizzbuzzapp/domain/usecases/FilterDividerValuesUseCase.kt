package com.example.fizzbuzzapp.domain.usecases

import com.example.fizzbuzzapp.domain.extensions.reduceUnderMaxValue
import javax.inject.Inject
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class FilterDividerValuesUseCase @Inject constructor() {
    fun execute(int1: String) = int1.toFloatOrNull()?.roundToInt()?.let {
        when (it) {
            0 -> ""
            Int.MAX_VALUE -> int1.reduceUnderMaxValue(Int.MAX_VALUE)
            else -> it.toString()
        }
    } ?: ""
}