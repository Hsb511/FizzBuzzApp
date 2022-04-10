package com.example.fizzbuzzapp.domain.usecases

import javax.inject.Inject

/**
 * Compute dynamic fizz buzz list
 *
 * See [Fizz buzz game](https://en.wikipedia.org/wiki/Fizz_buzz).
 */
class ComputeFizzBuzzListUseCase @Inject constructor() {
    fun execute(int1: Int, int2: Int, limit: Long, str1: String, str2: String) =
        (1..limit).map {
            when {
                it % int1.toLong() == 0L && it % int2.toLong() == 0L -> "$str1$str2"
                it % int1.toLong() == 0L -> str1
                it % int2.toLong() == 0L -> str2
                else -> it.toString()
            }
        }
}