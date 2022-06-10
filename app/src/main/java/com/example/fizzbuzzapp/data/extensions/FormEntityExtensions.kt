package com.example.fizzbuzzapp.data.extensions

import com.example.fizzbuzzapp.data.entities.FormEntity
import com.example.fizzbuzzapp.domain.models.FormModel

fun FormEntity.toModel() = FormModel(
    int1 = this.int1,
    int2 = this.int2,
    limit = this.limit,
    str1 = this.str1,
    str2 = this.str2
)