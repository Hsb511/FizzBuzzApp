package com.example.fizzbuzzapp

import com.example.fizzbuzzapp.domain.usecases.FilterDividerValuesUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class FilterDividerValuesTest {

    @Test
    fun `when divider is 0 output is empty`() {
        assertEquals("", FilterDividerValuesUseCase().execute("0"))
    }

    @Test
    fun `when divider is -1 output is -1`() {
        assertEquals("-1", FilterDividerValuesUseCase().execute("-1"))
    }

    @Test
    fun `when divider is 23 output is 23`() {
        assertEquals("23", FilterDividerValuesUseCase().execute("23"))
    }

    @Test
    fun `when divider is 2,3 output is 2`() {
        assertEquals("2", FilterDividerValuesUseCase().execute("2.3"))
    }

}