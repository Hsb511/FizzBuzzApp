package com.example.fizzbuzzapp.data.repositories

import com.example.fizzbuzzapp.data.daos.FormDao
import com.example.fizzbuzzapp.domain.models.FormModel
import com.example.fizzbuzzapp.domain.repositories.FormRepository
import javax.inject.Inject

class FormRoomRepository @Inject constructor(
    private val formDao: FormDao
): FormRepository {
    override suspend fun saveFormData(formModel: FormModel) {
        TODO("Not yet implemented")
    }

    override suspend fun retrieveFormData(): FormModel {
        TODO("Not yet implemented")
    }
}