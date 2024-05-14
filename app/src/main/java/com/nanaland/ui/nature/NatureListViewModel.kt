package com.nanaland.ui.nature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.nature.NatureThumbnailData
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.request.nature.GetNatureListRequest
import com.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.nanaland.domain.usecase.nature.GetNatureListUseCase
import com.nanaland.globalvalue.constant.getLocationList
import com.nanaland.globalvalue.constant.getLocationSelectionList
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
class NatureListViewModel @Inject constructor(
    private val getNatureListUseCase: GetNatureListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val locationList = getLocationList()
    val selectedLocationList = getLocationSelectionList()
    private val _natureThumbnailCount = MutableStateFlow<UiState<Long>>(UiState.Loading)
    val natureThumbnailCount = _natureThumbnailCount.asStateFlow()
    private val _natureThumbnailList = MutableStateFlow<UiState<List<NatureThumbnailData>>>(UiState.Loading)
    val natureThumbnailList = _natureThumbnailList.asStateFlow()
    private var page = 0L

    fun getNatureList() {
        var prevList: List<NatureThumbnailData>? = null
        if (_natureThumbnailList.value is UiState.Success) {
            page++
            prevList = (_natureThumbnailList.value as UiState.Success).data
        }
        val requestData = GetNatureListRequest(
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            page = page,
            size = 12
        )
        getNatureListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _natureThumbnailCount.update {
                            UiState.Success(data.data.totalElements)
                        }
                        _natureThumbnailList.update {
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
            .catch { LogUtil.e("flow Error", "GetNatureListUseCase") }
            .launchIn(viewModelScope)
    }

    fun clearNatureList() {
        _natureThumbnailList.update { UiState.Loading }
        page = 0
    }

    fun toggleFavorite(contentId: Long) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "NATURE"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _natureThumbnailList.update { uiState ->
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
        _natureThumbnailList.update { uiState ->
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
        getNatureList()
    }
}