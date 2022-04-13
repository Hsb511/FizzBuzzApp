package com.example.fizzbuzzapp.domain.extensions

fun String.reduceUnderMaxValue(maxValue: Number): String {
    val maxSize = maxValue.toString().length
    return if (this.length == maxSize) {
        this.substring(0, maxSize - 1)
    } else {
        this.substring(0, maxSize)
    }
}