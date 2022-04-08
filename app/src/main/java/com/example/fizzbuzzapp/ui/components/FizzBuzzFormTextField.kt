package com.example.fizzbuzzapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
inline fun FizzBuzzFormTextField(
    textFieldValue: MutableState<TextFieldValue>,
    label: String,
    crossinline onValueChange: (textFieldValue: TextFieldValue) -> String
) {
    OutlinedTextField(
        value = textFieldValue.value,
        onValueChange = {
            textFieldValue.value = TextFieldValue(
                onValueChange(it),
                it.selection,
                it.composition
            )
        },
        label = { Text(label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    )
}