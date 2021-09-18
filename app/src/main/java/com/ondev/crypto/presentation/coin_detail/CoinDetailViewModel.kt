package com.ondev.crypto.presentation.coin_detail


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ondev.crypto.common.Constants
import com.ondev.crypto.common.Resources
import com.ondev.crypto.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinID ->
            getCoin(coinID = coinID)
        }
    }

    private fun getCoin(coinID: String) {
        getCoinUseCase(coinID = coinID).onEach { result ->
            when (result) {
                is Resources.Success -> {
                    _state.value = CoinDetailState(coin = result.data)
                }
                is Resources.Error -> {
                    _state.value = CoinDetailState(error = result.error)
                }
                is Resources.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}