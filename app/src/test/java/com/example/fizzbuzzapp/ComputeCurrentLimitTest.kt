package com.example.fizzbuzzapp

import com.example.fizzbuzzapp.domain.usecases.ComputeCurrentLimitUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ComputeCurrentLimitTest {
    val computeCurrentLimitUC = ComputeCurrentLimitUseCase()

    @Test
    fun `current limit with default values`() {
        val actual = computeCurrentLimitUC("23",  1000000L, 0)
        assertEquals(23, actual)
    }

    @Test
    fun  `current limit with empty limit`() {
        val actual = computeCurrentLimitUC("",  1000000L, 0)
        assertEquals(0, actual)
    }

    @Test
    fun  `current limit with limit not long`() {
        val actual = computeCurrentLimitUC("a",  1000000L, 0)
        assertEquals(0, actual)
    }

    @Test
    fun  `current limit with step smaller than input limit`() {
        val actual = computeCurrentLimitUC("100",  10, 0)
        assertEquals(9, actual)
    }

    @Test
    fun  `current limit with step smaller than input limit and list already scrolled`() {
        val actual = computeCurrentLimitUC("100",  10, 10)
        assertEquals(19, actual)
    }


    @Test
    fun  `current limit with step smaller than input limit and list almost at the bottom`() {
        val actual = computeCurrentLimitUC("100",  10, 90)
        assertEquals(99, actual)
    }

    @Test
    fun  `current limit with step smaller than input limit and list at the bottom`() {
        val actual = computeCurrentLimitUC("100",  10, 99)
        assertEquals(100, actual)
    }
}