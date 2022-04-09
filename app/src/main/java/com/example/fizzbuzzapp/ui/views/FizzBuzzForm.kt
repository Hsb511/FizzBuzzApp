package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fizzbuzzapp.R
import com.example.fizzbuzzapp.ui.components.FizzBuzzFormTextField
import com.example.fizzbuzzapp.ui.viewmodels.FizzBuzzVM

@Composable
fun FizzBuzzForm(fizzBuzzVM: FizzBuzzVM = viewModel(), navController: NavHostController) {
    FizzBuzzForm(fizzBuzzVM) { navController.navigate("fizzBuzzList") }
}

@Composable
fun FizzBuzzForm(fizzBuzzVM: FizzBuzzVM = viewModel(), onValidate: () -> Unit) {

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
                    textFieldValue = fizzBuzzVM.int1,
                    label = "int1",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { value -> fizzBuzzVM.onDividerChanged(value) }
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = fizzBuzzVM.int2,
                    label = "int2",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    onValueChange = { value -> fizzBuzzVM.onDividerChanged(value) })
            }
        }
        // Form's second row with the input for the limit
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            FizzBuzzFormTextField(
                textFieldValue = fizzBuzzVM.limit,
                label = "limit",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { value -> fizzBuzzVM.onLimitChanged(value) }
            )
        }
        // Form's third row with the input for the replacing strings
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = fizzBuzzVM.str1,
                    label = "str1",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { value -> value.text }
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = fizzBuzzVM.str2,
                    label = "str2",
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    onValueChange = { value -> value.text }
                )
            }
        }
        // Form's validation button that navigates to the second screen
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = { onValidate() },
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
    }
}

@Preview(showSystemUi = true)
@Composable
fun FizzBuzzFormPreview() {
    FizzBuzzForm {}
}