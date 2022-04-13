package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
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
        Box(modifier = Modifier
            .fillMaxSize()
            .weight(1f, false)) {
            if (fizzBuzzVM.computedList.value.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    state = fizzBuzzListState,
                    modifier = Modifier.fillMaxSize().align(Alignment.TopCenter)
                ) {
                    if (fizzBuzzVM.isListStartNotDisplayed()) {
                        item {
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        fizzBuzzVM.onPageUp()
                                        fizzBuzzListState.scrollToItem(fizzBuzzVM.computedList.value.size)
                                    }
                                },
                                shape = RoundedCornerShape(32.dp)
                            ) {
                                Text(text = "↑", style = MaterialTheme.typography.h5)
                            }
                        }
                    }
                    items(fizzBuzzVM.computedList.value) {
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
                                    coroutineScope.launch {
                                        fizzBuzzVM.onPageDown()
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
                    coroutineScope.launch {
                        fizzBuzzVM.onLastPage()
                        fizzBuzzListState.scrollToItem(fizzBuzzVM.computedList.value.size)
                    }
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
    FizzBuzzList {}
}