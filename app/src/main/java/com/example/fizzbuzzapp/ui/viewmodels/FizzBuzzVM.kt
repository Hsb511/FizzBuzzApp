package com.example.fizzbuzzapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.fizzbuzzapp.domain.usecases.ComputeFizzBuzzListUseCase
import com.example.fizzbuzzapp.domain.usecases.FilterDividerValuesUseCase
import com.example.fizzbuzzapp.domain.usecases.FilterLimitValuesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FizzBuzzVM @Inject constructor(
    private val filterDividerValuesUseCase: FilterDividerValuesUseCase,
    private val filterLimitValuesUseCase: FilterLimitValuesUseCase,
    private val computeFizzBuzzListUseCase: ComputeFizzBuzzListUseCase
) : ViewModel() {
    private val step = 100000L
    val int1 = mutableStateOf(TextFieldValue("3"))
    val int2 = mutableStateOf(TextFieldValue("5"))
    val limit = mutableStateOf(TextFieldValue("23"))
    val str1 = mutableStateOf(TextFieldValue("fizz"))
    val str2 = mutableStateOf(TextFieldValue("buzz"))
    private var computedList: MutableList<List<String>> = mutableListOf()
    private val pageNumber = mutableStateOf(0)

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
            if (amountBeforeLimit > step ) {
                amountBeforeLimit = step
            }
            lastComputedIndex + amountBeforeLimit
        }
    }

    fun onListDisplayed() {
        computedList.add(
            computeFizzBuzzListUseCase.execute(
                int1.value.text.toInt(),
                int2.value.text.toInt(),
                computeCurrentLimit(),
                str1.value.text,
                str2.value.text
            )
        )
    }

    private fun recomputeList() {
        val currentListStart = (pageNumber.value) * step + 1
        val currentList = computeFizzBuzzListUseCase.execute(
            int1.value.text.toInt(),
            int2.value.text.toInt(),
            computeCurrentLimit(currentListStart),
            str1.value.text,
            str2.value.text,
            currentListStart
        )
        if (pageNumber.value >= computedList.size) {
            computedList.add(currentList)
        } else {
            computedList[pageNumber.value] = currentList
        }
    }

    fun onPageUp() {
        computedList[pageNumber.value] = emptyList()
        pageNumber.value --
        recomputeList()
    }

    fun onPageDown() {
        computedList[pageNumber.value] = emptyList()
        pageNumber.value ++
        recomputeList()
    }

    fun getCurrentList() = computedList[pageNumber.value]

    fun onListReset() {
        computedList = mutableListOf()
        Log.d("FizzBuzzVM", "The list has been reset")
    }

    fun isListStartNotDisplayed() = pageNumber.value != 0

    fun isListEndNotDisplayed() = pageNumber.value < limit.value.text.toLong() / step
}