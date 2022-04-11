package com.example.fizzbuzzapp.ui.viewmodels

import android.util.Log
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
    private val step = 10000L
    val int1 = mutableStateOf(TextFieldValue("3"))
    val int2 = mutableStateOf(TextFieldValue("5"))
    val limit = mutableStateOf(TextFieldValue("23"))
    val str1 = mutableStateOf(TextFieldValue("fizz"))
    val str2 = mutableStateOf(TextFieldValue("buzz"))
    val computedList = mutableStateOf(emptyList<String>())

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

    fun onListScrolled(firstVisibleIndex: Int): List<String> {
        val lastComputedIndex = computedList.value.size.toLong()

        if (lastComputedIndex - firstVisibleIndex < step || lastComputedIndex < limit.value.text.toLong()) {
            computedList.value += computeFizzBuzzListUseCase.execute(
                int1.value.text.toInt(),
                int2.value.text.toInt(),
                computeCurrentLimit(lastComputedIndex),
                str1.value.text,
                str2.value.text,
                lastComputedIndex
            )
        }

        return computedList.value
    }

    fun onListReset() {
        computedList.value = emptyList()
        Log.d("FizzBuzzVM", "The list has been reset")
    }

    fun isFormValid() =
        int1.value.text.isNotBlank() && int2.value.text.isNotBlank() && limit.value.text.isNotBlank()
}