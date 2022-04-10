package com.example.fizzbuzzapp

import com.example.fizzbuzzapp.domain.usecases.ComputeFizzBuzzListUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ComputeFizzBuzzListTest {

    @Test
    fun `when limit is 0 output is empty`() {
        val outputString = ComputeFizzBuzzListUseCase().execute(3, 5, 0L, "fizz", "buzz")
        val expected: List<String> = emptyList()
        assertEquals(expected, outputString)
    }

    @Test
    fun `when ints are 1 output is a list of fizzbuzz`() {
        val outputString = ComputeFizzBuzzListUseCase().execute(1, 1, 23L, "fizz", "buzz")
        assertEquals("fizzbuzz,".repeat(23).dropLast(1).split(","), outputString)
    }

    @Test
    fun `when ints are greater than limit output is a list of number`() {
        val outputString = ComputeFizzBuzzListUseCase().execute(46, 69, 23L, "fizz", "buzz")
        assertEquals((1..23).map { it.toString() }, outputString)
    }

    @Test
    fun `classic fizz buzz list`() {
        val outputString = ComputeFizzBuzzListUseCase().execute(3, 5, 23L, "fizz", "buzz")
        assertEquals("1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16,17,fizz,19,buzz,fizz,22,23".split(","), outputString)
    }

    @Test
    fun `classic fizz buzz list but with negative dividers`() {
        val outputString = ComputeFizzBuzzListUseCase().execute(-3, -5, 23L, "fizz", "buzz")
        assertEquals("1,2,fizz,4,buzz,fizz,7,8,fizz,buzz,11,fizz,13,14,fizzbuzz,16,17,fizz,19,buzz,fizz,22,23".split(","), outputString)
    }

    @Test
    fun `fizz buzz list with empty string`() {
        val outputString = ComputeFizzBuzzListUseCase().execute(3, 5, 23L, "", "")
        assertEquals("1,2,,4,,,7,8,,,11,,13,14,,16,17,,19,,,22,23".split(","), outputString)
    }

    @Test
    fun `fizz buzz list with 1000000 values`() {
        val outputString = ComputeFizzBuzzListUseCase().execute(10000001, 10000001, 1000000L, "fizz", "buzz")
        assertEquals((1..1000000).map { it.toString() }, outputString)
    }
}