package com.example.fizzbuzzapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.fizzbuzzapp.domain.usecases.ComputeFizzBuzzListUseCase
import com.example.fizzbuzzapp.domain.usecases.FilterDividerValuesUseCase
import com.example.fizzbuzzapp.domain.usecases.FilterLimitValuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FizzBuzzVM @Inject constructor(
    private val filterDividerValuesUseCase: FilterDividerValuesUseCase,
    private val filterLimitValuesUseCase: FilterLimitValuesUseCase,
    private val computeFizzBuzzListUseCase: ComputeFizzBuzzListUseCase
) : ViewModel() {
    private val step = 1000000L
    val int1 = mutableStateOf(TextFieldValue("3"))
    val int2 = mutableStateOf(TextFieldValue("5"))
    val limit = mutableStateOf(TextFieldValue("23"))
    val str1 = mutableStateOf(TextFieldValue("fizz"))
    val str2 = mutableStateOf(TextFieldValue("buzz"))
    val computedList: MutableState<List<String>> = mutableStateOf(emptyList())
    private val pageNumber = mutableStateOf(0L)

    fun onDividerChanged(divider: TextFieldValue): String {
        val filteredValue = filterDividerValuesUseCase.execute(divider.text)
        Log.d("FizzBuzzVM", "the value ${divider.text} has been filtered to : $filteredValue")
        return filteredValue
    }

    fun onLimitChanged(limit: TextFieldValue): String {
        val filteredValue = filterLimitValuesUseCase.execute(limit.text)
        Log.d("FizzBuzzVM", "the value ${limit.text} has been filtered to : $filteredValue")
        return filteredValue
    }

    fun isFormValid() =
        int1.value.text.isNotBlank() && int2.value.text.isNotBlank() && limit.value.text.isNotBlank()

    private fun computeCurrentLimit(lastComputedIndex: Long = 1): Long {
        val currentLimit = limit.value.text.toLong()
        return if (currentLimit < step) {
            currentLimit
        } else {
            var amountBeforeLimit = currentLimit - lastComputedIndex
            if (amountBeforeLimit >= step) {
                amountBeforeLimit = step - 1
            }
            lastComputedIndex + amountBeforeLimit
        }
    }

    suspend fun computeList() {
        computedList.value = emptyList()
        withContext(Dispatchers.Default) {
            val currentListStart = (pageNumber.value) * step + 1
            computedList.value = computeFizzBuzzListUseCase.execute(
                int1.value.text.toInt(),
                int2.value.text.toInt(),
                computeCurrentLimit(currentListStart),
                str1.value.text,
                str2.value.text,
                currentListStart
            )
        }
    }

    suspend fun onPageUp() {
        pageNumber.value--
        Log.d("FizzBuzzVM", "Scrolling to page ${pageNumber.value}")
        computeList()
    }

    suspend fun onPageDown() {
        pageNumber.value++
        Log.d("FizzBuzzVM", "Scrolling to page ${pageNumber.value}")
        computeList()
    }

    suspend fun onLastPage() {
        withContext(Dispatchers.Default) {
            val limit = limit.value.text.toLong()
            if (limit % step == 0L) {
                pageNumber.value = limit / step - 1
            } else {
                pageNumber.value = limit / step
            }

            Log.d("FizzBuzzVM", "Scrolling to the last page ${pageNumber.value}")
            computeList()
        }
    }

    fun onListReset() {
        pageNumber.value = 0
        computedList.value = mutableListOf()
        Log.d("FizzBuzzVM", "The list has been reset")
    }

    fun isListStartNotDisplayed() = pageNumber.value != 0L

    fun isListEndNotDisplayed() = pageNumber.value != 0L &&
            (pageNumber.value + 1) * step < limit.value.text.toLong()
}