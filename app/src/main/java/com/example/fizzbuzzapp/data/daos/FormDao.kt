package com.example.fizzbuzzapp.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.fizzbuzzapp.data.entities.FormEntity

@Dao
interface FormDao {
    @Insert
    fun insert(formEntity: FormEntity)

    @Query("SELECT * FROM T_FORM WHERE formId = (SELECT max(formId) FROM T_FORM)")
    fun findByMostRecentId(): FormEntity?
}