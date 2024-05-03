package com.nanaland.ui.market

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.market.MarketThumbnailData
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.request.market.GetMarketListRequest
import com.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.nanaland.domain.usecase.market.GetMarketListUseCase
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
class MarketListViewModel @Inject constructor(
    private val getMarketListUseCase: GetMarketListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    val selectedLocationList = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)
    private val _marketThumbnailCount = MutableStateFlow<UiState<Long>>(UiState.Loading)
    val marketThumbnailCount = _marketThumbnailCount.asStateFlow()
    private val _marketThumbnailList = MutableStateFlow<UiState<List<MarketThumbnailData>>>(UiState.Loading)
    val marketThumbnailList = _marketThumbnailList.asStateFlow()

    fun getMarketList() {
        _marketThumbnailCount.update { UiState.Loading }
        _marketThumbnailList.update { UiState.Loading }
        val requestData = GetMarketListRequest(
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            page = 0,
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
                            UiState.Success(data.data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "GetMarketListUseCase") }
            .launchIn(viewModelScope)
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
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "ToggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavoriteWithNoApi(contentId: Long, isLiked: Boolean) {
        _marketThumbnailList.update { uiState ->
            if (uiState is UiState.Success) {
                val newList = uiState.data.map {  item ->
                    if (item.id == contentId) item.copy(favorite = isLiked)
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