package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fizzbuzzapp.R
import com.example.fizzbuzzapp.ui.viewmodels.FizzBuzzVM

@Composable
fun FizzBuzzList(fizzBuzzVM: FizzBuzzVM = viewModel(), navController: NavHostController) {
    FizzBuzzList(fizzBuzzVM) { navController.navigate("fizzBuzzForm") }
}

@Composable
fun FizzBuzzList(fizzBuzzVM: FizzBuzzVM = viewModel(), onReturn: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp, 32.dp, 8.dp, 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.fizz_buzz_list_title),
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .fillMaxWidth()
            )
        }
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp).fillMaxWidth().weight(1f, false)
        ) {
            items(fizzBuzzVM.onListDisplayed()) {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        Row(modifier = Modifier.padding(16.dp)) {
            Button(
                onClick = { onReturn() },
                shape = RoundedCornerShape(32.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.fizz_buzz_list_return),
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
fun FizzBuzzListPreview() {
    FizzBuzzList() {}
}