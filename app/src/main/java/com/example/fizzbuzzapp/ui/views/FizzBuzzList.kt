package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.fizzbuzzapp.R
import com.example.fizzbuzzapp.ui.viewmodels.FizzBuzzVM
import kotlinx.coroutines.launch

@Composable
fun FizzBuzzList(fizzBuzzVM: FizzBuzzVM = viewModel(), navController: NavHostController) {
    FizzBuzzList(fizzBuzzVM) { navController.navigate("fizzBuzzForm") }
}

@Composable
fun FizzBuzzList(fizzBuzzVM: FizzBuzzVM = viewModel(), onReturn: () -> Unit) {
    val fizzBuzzListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

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
            state = fizzBuzzListState,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f, true)
        ) {
            if (fizzBuzzVM.isListStartNotDisplayed()) {
                item {
                    Button(
                        onClick = {
                            fizzBuzzVM.onPageUp()
                            coroutineScope.launch {
                                fizzBuzzListState.scrollToItem(fizzBuzzVM.getCurrentList().size)
                            }
                        },
                        shape = RoundedCornerShape(32.dp)
                    ) {
                        Text(text = "↑", style = MaterialTheme.typography.h5)
                    }
                }
            }
            items(fizzBuzzVM.getCurrentList()) {
                Text(
                    text = it,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colors.onSurface,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (fizzBuzzVM.isListEndNotDisplayed()) {
                item {
                    Button(
                        onClick = {
                            fizzBuzzVM.onPageDown()
                            coroutineScope.launch {
                                fizzBuzzListState.scrollToItem(0)
                            }
                        },
                        shape = RoundedCornerShape(32.dp)
                    ) {
                        Text(text = "↓", style = MaterialTheme.typography.h5)
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            OutlinedButton(
                onClick = { onReturn() },
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .weight(1f, true)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.fizz_buzz_list_return),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(8.dp)
                )
            }
            Button(
                onClick = {
                    /* TODO */
                },
                shape = RoundedCornerShape(32.dp),
                modifier = Modifier
                    .weight(1f, true)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.fizz_buzz_list_scroll),
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