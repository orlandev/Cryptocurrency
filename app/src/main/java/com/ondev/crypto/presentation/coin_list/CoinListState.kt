package com.ondev.crypto.presentation.coin_list

import com.ondev.crypto.common.ResourcesError
import com.ondev.crypto.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: ResourcesError? = null
)
