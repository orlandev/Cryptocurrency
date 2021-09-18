package com.ondev.crypto.domain.repository

import com.ondev.crypto.data.remote.dto.CoinDTO
import com.ondev.crypto.data.remote.dto.CoinDetailDTO

interface CoinRepository {
    suspend fun getCoins(): List<CoinDTO>
    suspend fun getCoinById(coinID: String): CoinDetailDTO
}