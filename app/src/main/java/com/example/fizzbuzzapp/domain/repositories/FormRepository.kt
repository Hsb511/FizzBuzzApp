package com.example.fizzbuzzapp.domain.repositories

import com.example.fizzbuzzapp.domain.models.FormModel

interface FormRepository {
    suspend fun saveFormData(formModel: FormModel)
    suspend fun retrieveFormData(): FormModel?
}