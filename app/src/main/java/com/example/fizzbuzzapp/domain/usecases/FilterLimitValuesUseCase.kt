package com.example.fizzbuzzapp.domain.usecases

import com.example.fizzbuzzapp.domain.extensions.reduceUnderMaxValue
import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.roundToLong

class FilterLimitValuesUseCase @Inject constructor() {
    fun execute(limit: String) =
        limit.toDoubleOrNull()?.roundToLong()?.let {
            if (it == Long.MAX_VALUE) {
                limit.reduceUnderMaxValue(Long.MAX_VALUE)
            } else {
                abs(it).toString()
            }
        } ?: ""
}