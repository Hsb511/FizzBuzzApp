package com.example.fizzbuzzapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_FORM")
data class FormEntity(
    @PrimaryKey(autoGenerate = true)
    val formId: Long = 0,
    val int1: Int,
    val int2: Int,
    val limit: Long,
    val str1: String,
    val str2: String
)