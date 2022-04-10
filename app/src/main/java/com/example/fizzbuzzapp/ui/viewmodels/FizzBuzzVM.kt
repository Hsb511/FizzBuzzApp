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
): ViewModel() {
    val int1 = mutableStateOf(TextFieldValue("3"))
    val int2 = mutableStateOf(TextFieldValue("5"))
    val limit = mutableStateOf(TextFieldValue("23"))
    val str1 = mutableStateOf(TextFieldValue("fizz"))
    val str2 = mutableStateOf(TextFieldValue("buzz"))
    private var computedList: List<String> = emptyList()

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

    fun onListDisplayed(): List<String> {
        if (computedList.isEmpty()) {
            computedList = computeFizzBuzzListUseCase.execute(
                int1.value.text.toInt(),
                int2.value.text.toInt(),
                limit.value.text.toLong(),
                str1.value.text,
                str2.value.text
            )
        }
        return computedList
    }

    fun onListReset() {
        computedList = emptyList()
    }
}