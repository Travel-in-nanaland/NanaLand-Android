package com.nanaland.ui.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.market.MarketContentData
import com.nanaland.domain.request.market.GetMarketContentRequest
import com.nanaland.domain.usecase.market.GetMarketContentUseCase
import com.nanaland.util.log.LogUtil
import com.nanaland.util.network.onError
import com.nanaland.util.network.onException
import com.nanaland.util.network.onSuccess
import com.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MarketContentViewModel @Inject constructor(
    private val getMarketContentUseCase: GetMarketContentUseCase
) : ViewModel() {

    private val _marketContent = MutableStateFlow<UiState<MarketContentData>>(UiState.Loading)
    val marketContent = _marketContent.asStateFlow()

    fun getMarketContent(contentId: Long?) {
        if (contentId == null) return
        _marketContent.update { UiState.Loading }
        val requestData = GetMarketContentRequest(
            id = contentId,
            isSearch = false
        )
        getMarketContentUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _marketContent.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow error", "GetMarketContentUseCase") }
            .launchIn(viewModelScope)
    }
}