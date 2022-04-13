package com.example.fizzbuzzapp

import com.example.fizzbuzzapp.domain.usecases.FilterLimitValuesUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class FilterLimitValuesTest {

    @Test
    fun `when limit is 0 output is 0`() {
        assertEquals("0", FilterLimitValuesUseCase().execute("0"))
    }

    @Test
    fun `when limit is -1 output is 1`() {
        assertEquals("1", FilterLimitValuesUseCase().execute("-1"))
    }

    @Test
    fun `when limit is 23 output is 23`() {
        assertEquals("23", FilterLimitValuesUseCase().execute("23"))
    }

    @Test
    fun `when limit is 2,3 output is 2`() {
        assertEquals("2", FilterLimitValuesUseCase().execute("2.3"))
    }

    @Test
    fun `when limit is 10 000 000 000 output is 10 000 000 000`() {
        assertEquals("10000000000", FilterLimitValuesUseCase().execute("10000000000"))
    }

    @Test
    fun `when limit is 1 000 000 000 000 000 000 output is 1 000 000 000 000 000 000 000`() {
        assertEquals("1000000000000000000", FilterLimitValuesUseCase().execute("1000000000000000000000"))
    }
}