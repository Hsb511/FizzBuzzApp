package com.example.fizzbuzzapp.domain.usecases

import javax.inject.Inject
import kotlin.math.abs
import kotlin.math.roundToLong

class FilterLimitValuesUseCase @Inject constructor() {
    fun execute(limit: String) =
        limit.toDoubleOrNull()?.let {
            abs(it.roundToLong()).toString()
        } ?: ""
}