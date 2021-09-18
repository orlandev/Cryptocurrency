package com.ondev.crypto.data.repository

import com.ondev.crypto.data.remote.CoinPaprikaApi
import com.ondev.crypto.data.remote.dto.CoinDTO
import com.ondev.crypto.data.remote.dto.CoinDetailDTO
import com.ondev.crypto.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : CoinRepository {
    override suspend fun getCoins(): List<CoinDTO> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinID: String): CoinDetailDTO {
        return api.getCoinById(coinID = coinID)
    }

}