package com.example.fizzbuzzapp.domain.usecases

import javax.inject.Inject

/**
 * Compute dynamic fizz buzz list
 *
 * See [Fizz buzz game](https://en.wikipedia.org/wiki/Fizz_buzz).
 */
class ComputeFizzBuzzListUseCase @Inject constructor() {
    fun execute(int1: Int, int2: Int, limit: Int, str1: String, str2: String) =
        (1..limit).toMutableList().map {
            when {
                it % int1 == 0 && it % int2 == 0 -> "$str1$str2"
                it % int1 == 0 -> str1
                it % int2 == 0 -> str2
                else -> it.toString()
            }
        }
}