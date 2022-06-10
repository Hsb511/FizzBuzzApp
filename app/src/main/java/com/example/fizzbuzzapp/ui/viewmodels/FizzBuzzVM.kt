package com.example.fizzbuzzapp.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fizzbuzzapp.domain.models.FormModel
import com.example.fizzbuzzapp.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FizzBuzzVM @Inject constructor(
    private val filterDividerValuesUseCase: FilterDividerValuesUseCase,
    private val filterLimitValuesUseCase: FilterLimitValuesUseCase,
    private val computeFizzBuzzListUseCase: ComputeFizzBuzzListUseCase,
    private val checkFormValidityUseCase: CheckFormValidityUseCase,
    private val persistFormUseCase: PersistFormUseCase,
    private val retrieveLastFormUseCase: RetrieveLastFormUseCase
) : ViewModel() {
    private val step = 1000000L
    lateinit var firstIntInput: MutableState<TextFieldValue>
    lateinit var secondIntInput: MutableState<TextFieldValue>
    lateinit var limitInput: MutableState<TextFieldValue>
    lateinit var firstStringInput: MutableState<TextFieldValue>
    lateinit var secondStringInput: MutableState<TextFieldValue>
    val computedList: MutableState<List<String>> = mutableStateOf(emptyList())
    private val pageNumber = mutableStateOf(0L)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val lastForm = retrieveLastFormUseCase.execute()
                firstIntInput = mutableStateOf(TextFieldValue(lastForm?.int1?.toString()?: "3"))
                secondIntInput = mutableStateOf(TextFieldValue(lastForm?.int2?.toString()?: "5"))
                limitInput = mutableStateOf(TextFieldValue(lastForm?.limit?.toString()?: "23"))
                firstStringInput = mutableStateOf(TextFieldValue(lastForm?.str1?: "fizz"))
                secondStringInput = mutableStateOf(TextFieldValue(lastForm?.str2?: "buzz"))
        }
    }

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

    fun isFormValid() = checkFormValidityUseCase(
        firstIntText = firstIntInput.value.text,
        secondIntText = secondIntInput.value.text,
        limitText = limitInput.value.text
    )

    private fun computeCurrentLimit(lastComputedIndex: Long = 1): Long {
        val currentLimit = limitInput.value.text.toLong()
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
                firstIntInput.value.text.toInt(),
                secondIntInput.value.text.toInt(),
                computeCurrentLimit(currentListStart),
                firstStringInput.value.text,
                secondStringInput.value.text,
                currentListStart
            )
        }
    }

    fun persistData() {
        viewModelScope.launch(Dispatchers.IO) {
            persistFormUseCase.execute(FormModel(
                firstIntInput.value.text.toInt(),
                secondIntInput.value.text.toInt(),
                limitInput.value.text.toLong(),
                firstStringInput.value.text,
                secondStringInput.value.text,
            ))
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
            val limit = limitInput.value.text.toLong()
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
            (pageNumber.value + 1) * step < limitInput.value.text.toLong()

    fun isListComputing() = computedList.value.isEmpty() == limitInput.value.text.toLong() > 0
}