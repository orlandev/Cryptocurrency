package com.ondev.crypto.presentation.coin_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ondev.crypto.R
import com.ondev.crypto.Screen
import com.ondev.crypto.common.ResourcesError
import com.ondev.crypto.presentation.coin_list.components.CoinListItem

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: CoinListViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.coins) { coin ->
                CoinListItem(coin = coin, onItemClick = {
                    navController.navigate(Screen.CoinDetailScreen.route + "/${it.id}")
                })
            }
        }
        if (state.error != null) {
            val errorMessage = when (state.error) {
                ResourcesError.HTTP_ERROR -> {
                    stringResource(id = R.string.http_error_message)
                }
                ResourcesError.IO_ERROR -> {
                    stringResource(id = R.string.io_error_message)
                }
                ResourcesError.UNKNOW -> {
                    stringResource(id = R.string.unknow_error_message)
                }
            }
            Text(
                text = errorMessage,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}