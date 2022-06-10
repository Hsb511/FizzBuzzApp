package com.example.fizzbuzzapp.domain.usecases

import com.example.fizzbuzzapp.domain.models.FormModel
import com.example.fizzbuzzapp.domain.repositories.FormRepository
import javax.inject.Inject

class PersistFormUseCase @Inject constructor(
    private val formRepository: FormRepository
) {
    suspend fun execute(formModel: FormModel) {
        formRepository.saveFormData(formModel)
    }
}