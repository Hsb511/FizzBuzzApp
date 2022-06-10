package com.example.fizzbuzzapp.data.repositories

import com.example.fizzbuzzapp.data.daos.FormDao
import com.example.fizzbuzzapp.data.extensions.toModel
import com.example.fizzbuzzapp.domain.extensions.toEntity
import com.example.fizzbuzzapp.domain.models.FormModel
import com.example.fizzbuzzapp.domain.repositories.FormRepository
import javax.inject.Inject

class FormRoomRepository @Inject constructor(
    private val formDao: FormDao
): FormRepository {
    override suspend fun saveFormData(formModel: FormModel) {
        formDao.insert(formModel.toEntity())
    }

    override suspend fun retrieveFormData() = formDao.findByMostRecentId()?.toModel()
}