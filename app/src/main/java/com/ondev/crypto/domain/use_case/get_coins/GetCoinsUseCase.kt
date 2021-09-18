package com.ondev.crypto.domain.use_case.get_coins

import com.ondev.crypto.common.Resources
import com.ondev.crypto.common.ResourcesError
import com.ondev.crypto.data.remote.dto.toCoin
import com.ondev.crypto.domain.model.Coin
import com.ondev.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resources<List<Coin>>> = flow {
        try {
            emit(Resources.Loading())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resources.Success(coins))
        } catch (e: HttpException) {
            emit(Resources.Error(ResourcesError.HTTP_ERROR))
        } catch (e: IOException) {
            emit(Resources.Error(ResourcesError.IO_ERROR))
        } catch (e: Exception) {
            emit(Resources.Error(ResourcesError.UNKNOW))
        }
    }

}