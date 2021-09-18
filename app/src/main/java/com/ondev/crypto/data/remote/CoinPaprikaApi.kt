package com.ondev.crypto.data.remote

import com.ondev.crypto.data.remote.dto.CoinDTO
import com.ondev.crypto.data.remote.dto.CoinDetailDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDTO>

    @GET("/v1/coins/{coinID}")
    suspend fun getCoinById(@Path("coinID") coinID: String): CoinDetailDTO
}