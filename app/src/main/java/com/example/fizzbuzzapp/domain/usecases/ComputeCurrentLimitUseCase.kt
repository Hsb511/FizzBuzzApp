package com.example.fizzbuzzapp.domain.usecases

import javax.inject.Inject

class ComputeCurrentLimitUseCase @Inject constructor() {
    operator fun invoke(limitInput: String, step: Long, lastComputedIndex: Long) =
        limitInput.toLongOrNull()?.let {
            if (it < step) {
                it
            } else {
                var amountBeforeLimit = it - lastComputedIndex
                if (amountBeforeLimit >= step) {
                    amountBeforeLimit = step - 1
                }
                lastComputedIndex + amountBeforeLimit
            }
        }?: 0
}