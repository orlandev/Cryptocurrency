package com.ondev.crypto.presentation.coin_detail

import com.ondev.crypto.common.ResourcesError
import com.ondev.crypto.domain.model.Coin
import com.ondev.crypto.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail? = null,
    val error: ResourcesError? = null
)
