package com.example.fizzbuzzapp.ui.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
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
    val fizzBuzzListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val computedList = fizzBuzzVM.computedList.value

    FizzBuzzList(
        computedList = computedList,
        fizzBuzzListState = fizzBuzzListState,
        onReturn = { navController.navigate("fizzBuzzForm") },
        isListStartNotDisplayed = fizzBuzzVM.isListStartNotDisplayed(),
        onPageUp = {
            coroutineScope.launch {
                fizzBuzzVM.onPageUp()
                fizzBuzzListState.scrollToItem(computedList.size)
            }
        },
        isListEndNotDisplayed = fizzBuzzVM.isListEndNotDisplayed(),
        onPageDown = {
            coroutineScope.launch {
                fizzBuzzVM.onPageDown()
                fizzBuzzListState.scrollToItem(0)
            }
        },
        onLastPage = {
            coroutineScope.launch {
                fizzBuzzVM.onLastPage()
                fizzBuzzListState.scrollToItem(computedList.size)
            }
        },
        isListComputing = fizzBuzzVM.isListComputing()
    )
}

@Composable
fun FizzBuzzList(
    computedList: List<String>,
    fizzBuzzListState: LazyListState,
    isListStartNotDisplayed: Boolean,
    onPageUp: () -> Unit,
    isListEndNotDisplayed: Boolean,
    onPageDown: () -> Unit,
    onReturn: () -> Unit,
    onLastPage: () -> Unit,
    isListComputing: Boolean
) {

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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f, false)
        ) {
            if (isListComputing) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    state = fizzBuzzListState,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.TopCenter)
                ) {
                    if (isListStartNotDisplayed) {
                        item {
                            Button(
                                onClick = { onPageUp() },
                                shape = RoundedCornerShape(32.dp)
                            ) {
                                Text(text = "↑", style = MaterialTheme.typography.h5)
                            }
                        }
                    }
                    items(computedList) {
                        Text(
                            text = it,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colors.onSurface,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                    if (isListEndNotDisplayed) {
                        item {
                            Button(
                                onClick = { onPageDown() },
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
                onClick = { onLastPage() },
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
    FizzBuzzList(
        computedList = (1..50).map { it.toString() },
        fizzBuzzListState = rememberLazyListState(),
        isListStartNotDisplayed = true,
        onPageUp = {},
        isListEndNotDisplayed = true,
        onPageDown = {},
        onReturn = {},
        onLastPage = {},
        isListComputing = false,
    )
}