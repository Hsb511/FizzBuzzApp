package com.example.fizzbuzzapp.domain.usecases

import javax.inject.Inject

class CheckFormValidityUseCase @Inject constructor() {
    operator fun invoke(firstIntText: String?, secondIntText: String?, limitText: String?) =
        !firstIntText.isNullOrBlank() && !secondIntText.isNullOrBlank() && !limitText.isNullOrBlank()
}