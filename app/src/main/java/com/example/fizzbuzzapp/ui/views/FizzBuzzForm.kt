package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

@Composable
fun FizzBuzzForm(fizzBuzzVM: FizzBuzzVM = viewModel(), navController: NavHostController) {
    FizzBuzzForm(fizzBuzzVM) { navController.navigate("fizzBuzzList") }
}

@Composable
fun FizzBuzzForm(fizzBuzzVM: FizzBuzzVM = viewModel(), onValidate: () -> Unit) {
    val int1 = remember { mutableStateOf(TextFieldValue("")) }
    val int2 = remember { mutableStateOf(TextFieldValue("")) }
    val limit = remember { mutableStateOf(TextFieldValue("")) }
    val str1 = remember { mutableStateOf(TextFieldValue("")) }
    val str2 = remember { mutableStateOf(TextFieldValue("")) }


    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp, 24.dp, 8.dp, 8.dp)) {
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
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = int1,
                    label = "int1",
                    onValueChange = { "" /*TODO*/ }
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = int2,
                    label = "int2",
                    onValueChange = { "" /*TODO*/ }
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            FizzBuzzFormTextField(
                textFieldValue = limit,
                label = "limit",
                onValueChange = { "" /*TODO*/ }
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(8.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = str1,
                    label = "str1",
                    onValueChange = { "" /*TODO*/ }
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                FizzBuzzFormTextField(
                    textFieldValue = str2,
                    label = "str2",
                    onValueChange = { "" /*TODO*/ }
                )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = { onValidate() },
                shape = RoundedCornerShape(32.dp)) {
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