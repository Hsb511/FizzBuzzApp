package com.example.fizzbuzzapp.domain.usecases

import com.example.fizzbuzzapp.domain.extensions.reduceUnderMaxValue
import javax.inject.Inject
import kotlin.math.roundToInt

class FilterDividerValuesUseCase @Inject constructor() {
    fun execute(divider: String) = divider.toFloatOrNull()?.roundToInt()?.let {
        when (it) {
            0 -> ""
            Int.MAX_VALUE -> divider.reduceUnderMaxValue(Int.MAX_VALUE)
            else -> it.toString()
        }
    } ?: ""
}