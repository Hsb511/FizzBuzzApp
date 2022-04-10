package com.example.fizzbuzzapp.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
inline fun FizzBuzzFormTextField(
    textFieldValue: MutableState<TextFieldValue>,
    label: String,
    isNumber: Boolean,
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
        keyboardOptions = if (isNumber) {
            KeyboardOptions(keyboardType = KeyboardType.Number)
        } else {
            KeyboardOptions(keyboardType = KeyboardType.Text)
        },
        label = { Text(label) },
        isError = if (isNumber) {
            textFieldValue.value.text.isBlank()
        } else {
            false
        },
        colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colors.onSurface),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    )
}