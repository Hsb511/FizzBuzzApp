package com.example.fizzbuzzapp.domain.usecases

import com.example.fizzbuzzapp.domain.repositories.FormRepository
import javax.inject.Inject

class RetrieveLastFormUseCase @Inject constructor(
    private val formRepository: FormRepository
) {
    suspend fun execute() = formRepository.retrieveFormData()
}