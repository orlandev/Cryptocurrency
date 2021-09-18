package com.ondev.crypto.domain.use_case.get_coin

import com.ondev.crypto.common.Resources
import com.ondev.crypto.common.ResourcesError
import com.ondev.crypto.data.remote.dto.toCoinDetail
import com.ondev.crypto.domain.model.CoinDetail
import com.ondev.crypto.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository,
) {
    operator fun invoke(coinID: String): Flow<Resources<CoinDetail>> = flow {
        try {
            emit(Resources.Loading())
            val coin = repository.getCoinById(coinID = coinID).toCoinDetail()
            emit(Resources.Success(coin))
        } catch (e: HttpException) {
            emit(Resources.Error(ResourcesError.HTTP_ERROR))
        } catch (e: IOException) {
            emit(Resources.Error(ResourcesError.IO_ERROR))
        } catch (e: Exception) {
            emit(Resources.Error(ResourcesError.UNKNOW))
        }
    }

}