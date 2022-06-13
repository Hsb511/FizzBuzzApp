package com.example.fizzbuzzapp

import com.example.fizzbuzzapp.domain.usecases.CheckFormValidityUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class CheckFormValidityTest {
    val checkFormValidityUseCase = CheckFormValidityUseCase()

    @Test
    fun `form is invalid when all values are null`() {
        assertEquals(false, checkFormValidityUseCase(null, null, null))
    }

    @Test
    fun `form is invalid when all values are empty`() {
        assertEquals(false, checkFormValidityUseCase("", "", ""))
    }

    @Test
    fun `form is invalid when all values are blank`() {
        assertEquals(false, checkFormValidityUseCase(" ", "    ", " "))
    }

    @Test
    fun `form is invalid when one value is blank`() {
        assertEquals(false, checkFormValidityUseCase("2", "3", " "))
    }

    @Test
    fun `form is valid when all the values are string number`() {
        assertEquals(true, checkFormValidityUseCase("2", "3", "100"))
    }

    @Test
    fun `form is invalid when one value is string letter`() {
        assertEquals(false, checkFormValidityUseCase("2", "a", "100"))
    }

    @Test
    fun `form is invalid when one value is string point`() {
        assertEquals(false, checkFormValidityUseCase(".", "3", "100"))
    }

    @Test
    fun `form is valid with long value for limit`() {
        assertEquals(false, checkFormValidityUseCase(".", "3", "100000000000000"))
    }
}