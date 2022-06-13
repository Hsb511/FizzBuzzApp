package com.example.fizzbuzzapp.domain.usecases

import javax.inject.Inject

class CheckFormValidityUseCase @Inject constructor() {
    operator fun invoke(firstIntText: String?, secondIntText: String?, limitText: String?) =
        firstIntText?.toIntOrNull() != null &&
                secondIntText?.toIntOrNull() != null &&
                limitText?.toLongOrNull() != null
}