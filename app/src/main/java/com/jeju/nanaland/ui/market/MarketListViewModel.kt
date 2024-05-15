package com.jeju.nanaland.ui.market

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.market.MarketThumbnailData
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.market.GetMarketListRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.market.GetMarketListUseCase
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.constant.getLocationSelectionList
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MarketListViewModel @Inject constructor(
    private val getMarketListUseCase: GetMarketListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val locationList = getLocationList()
    val selectedLocationList = getLocationSelectionList()
    private val _marketThumbnailCount = MutableStateFlow<UiState<Long>>(UiState.Loading)
    val marketThumbnailCount = _marketThumbnailCount.asStateFlow()
    private val _marketThumbnailList = MutableStateFlow<UiState<List<MarketThumbnailData>>>(UiState.Loading)
    val marketThumbnailList = _marketThumbnailList.asStateFlow()
    private var page = 0L

    fun getMarketList() {
        var prevList: List<MarketThumbnailData>? = null
        if (_marketThumbnailList.value is UiState.Success) {
            page++
            prevList = (_marketThumbnailList.value as UiState.Success).data
        }
        val requestData = GetMarketListRequest(
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            page = page,
            size = 12
        )
        getMarketListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _marketThumbnailCount.update {
                            UiState.Success(data.data.totalElements)
                        }
                        _marketThumbnailList.update {
                            if (prevList.isNullOrEmpty()) {
                                UiState.Success(data.data.data)
                            } else {
                                UiState.Success(prevList + data.data.data)
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.e("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.e("onException", "${it.message}")
                }
            }
            .catch { LogUtil.e("flow Error", "GetMarketListUseCase") }
            .launchIn(viewModelScope)
    }

    fun clearMarketList() {
        _marketThumbnailList.update { UiState.Loading }
        page = 0
    }

    fun toggleFavorite(contentId: Long) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "MARKET"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _marketThumbnailList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.map {  item ->
                                    if (item.id == contentId) item.copy(favorite = data.data.favorite)
                                    else item
                                }
                                UiState.Success(newList)
                            } else {
                                uiState
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.e("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.e("onException", "${it.message}")
                }
            }
            .catch { LogUtil.e("flow Error", "ToggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavoriteWithNoApi(contentId: Long, isFavorite: Boolean) {
        _marketThumbnailList.update { uiState ->
            if (uiState is UiState.Success) {
                val newList = uiState.data.map {  item ->
                    if (item.id == contentId) item.copy(favorite = isFavorite)
                    else item
                }
                UiState.Success(newList)
            } else {
                uiState
            }
        }
    }

    init {
        getMarketList()
    }
}