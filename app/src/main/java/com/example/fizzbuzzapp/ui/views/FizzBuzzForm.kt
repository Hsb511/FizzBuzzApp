package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fizzbuzzapp.R
import com.example.fizzbuzzapp.ui.components.FizzBuzzFormTextField
import com.example.fizzbuzzapp.ui.viewmodels.FizzBuzzVM
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FizzBuzzForm(fizzBuzzVM: FizzBuzzVM = viewModel(), navController: NavHostController) {
    val coroutineScope = rememberCoroutineScope()
    val keyboardController = LocalSoftwareKeyboardController.current

    FizzBuzzForm(
        int1 = fizzBuzzVM.firstIntInput,
        int2 = fizzBuzzVM.secondIntInput,
        limit = fizzBuzzVM.limitInput,
        str1 = fizzBuzzVM.firstStringInput,
        str2 = fizzBuzzVM.secondStringInput,
        onDividerChanged = { divider -> fizzBuzzVM.onDividerChanged(divider) },
        onLimitChanged = { limit -> fizzBuzzVM.onLimitChanged(limit) },
        onValidate = {
            keyboardController?.hide()
            if (fizzBuzzVM.isFormValid()) {
                fizzBuzzVM.onListReset()
                navController.navigate("fizzBuzzList")
                coroutineScope.launch {
                    fizzBuzzVM.computeList()
                }
            }
        },
        isFormValid = fizzBuzzVM.isFormValid()
    )

}

@Composable
fun FizzBuzzForm(
    int1: MutableState<TextFieldValue>,
    int2: MutableState<TextFieldValue>,
    limit: MutableState<TextFieldValue>,
    str1: MutableState<TextFieldValue>,
    str2: MutableState<TextFieldValue>,
    onDividerChanged: (value: TextFieldValue) -> String,
    onLimitChanged: (value: TextFieldValue) -> String,
    onValidate: () -> Unit,
    isFormValid: Boolean
) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        // Fizzbuzz form's title
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp, 32.dp, 8.dp, 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.fizz_buzz_form_title),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .fillMaxWidth()
            )
        }
        // Form's first row with the two inputs for the dividers
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = int1,
                    label = "int1: 1 -> 2 147 483 647",
                    isNumber = true,
                    onValueChange = { value -> onDividerChanged(value) }
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = int2,
                    label = "int2: 1 -> 2 147 483 647",
                    isNumber = true,
                    onValueChange = { value -> onDividerChanged(value) })
            }
        }
        // Form's second row with the input for the limit
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            FizzBuzzFormTextField(
                textFieldValue = limit,
                label = "limit: 0 -> 9 223 372 036 854 775 807",
                isNumber = true,
                onValueChange = { value -> onLimitChanged(value) }
            )
        }
        // Form's third row with the input for the replacing strings
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = str1,
                    label = "str1",
                    isNumber = false,
                    onValueChange = { value -> value.text }
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = str2,
                    label = "str2",
                    isNumber = false,
                    onValueChange = { value -> value.text }
                )
            }
        }
        // Form's validation button that navigates to the second screen
        Row(modifier = Modifier
            .padding(16.dp)
            .weight(1f, true)) {
            Button(
                onClick = {
                    onValidate()
                },
                shape = RoundedCornerShape(32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.fizz_buzz_form_validate),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
        if (!isFormValid) {
            Snackbar {
                Text(text = stringResource(id = R.string.fizz_buzz_form_invalid_message))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FizzBuzzFormPreview() {
    FizzBuzzForm (
        int1 = remember {mutableStateOf(TextFieldValue("3"))},
        int2 = remember { mutableStateOf(TextFieldValue("5")) },
        limit = remember { mutableStateOf(TextFieldValue("23")) },
        str1 = remember { mutableStateOf(TextFieldValue("fizz")) },
        str2 = remember { mutableStateOf(TextFieldValue("buzz")) },
        onDividerChanged = { "" },
        onLimitChanged = { "" },
        onValidate = {},
        isFormValid = true
    )
}