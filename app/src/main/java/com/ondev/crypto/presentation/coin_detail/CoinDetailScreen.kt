package com.ondev.crypto.presentation.coin_detail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.flowlayout.FlowRow
import com.ondev.crypto.R
import com.ondev.crypto.Screen
import com.ondev.crypto.common.ResourcesError
import com.ondev.crypto.data.remote.dto.TeamMember
import com.ondev.crypto.presentation.coin_detail.components.CoinTag
import com.ondev.crypto.presentation.coin_detail.components.TeamListItem
import com.ondev.crypto.presentation.coin_list.components.CoinListItem

@Composable
fun CoinDetailScreen(
    viewModel: CoinDetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        state.coin?.let { coinDetail ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp)
            ) {
                item {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        )
                    {
                        Text(
                            text = "${coinDetail.rank}. ${coinDetail.name} ( ${coinDetail.symbol} ) ",
                            style = MaterialTheme.typography.h2,
                            modifier = Modifier.weight(8f)
                        )
                        Text(
                            text = if (coinDetail.isActive) stringResource(id = R.string.active) else stringResource(
                                id = R.string.inactive
                            ),
                            color = if (coinDetail.isActive) Color.Green else Color.Red,
                            style = MaterialTheme.typography.body2,
                            textAlign = TextAlign.End,
                            fontStyle = FontStyle.Italic,
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .weight(2f)
                        )
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = coinDetail.description,
                        style = MaterialTheme.typography.body2,

                        )
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = stringResource(id = R.string.tags),
                        style = MaterialTheme.typography.h3,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    FlowRow(
                        mainAxisSpacing = 10.dp,
                        crossAxisSpacing = 10.dp,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        coinDetail.tags.forEach { tag ->
                            CoinTag(tag = tag)
                        }
                    }
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = stringResource(id = R.string.team_members),
                        style = MaterialTheme.typography.h3,
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                }
                items(coinDetail.team) { teamMember ->
                    TeamListItem(
                        teamMember = teamMember,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    Divider()
                }
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