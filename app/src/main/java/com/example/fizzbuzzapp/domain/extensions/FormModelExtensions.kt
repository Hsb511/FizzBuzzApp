package com.example.fizzbuzzapp.domain.extensions

import com.example.fizzbuzzapp.data.entities.FormEntity
import com.example.fizzbuzzapp.domain.models.FormModel

fun FormModel.toEntity() {
    FormEntity(
        int1 = this.int1,
        int2 = this.int2,
        limit = this.limit,
        str1 = this.str1,
        str2 = this.str2
    )
}