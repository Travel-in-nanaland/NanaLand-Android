package com.jeju.nanaland.ui.nature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.nature.NatureThumbnail
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.nature.GetNatureListRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.nature.GetNatureListUseCase
import com.jeju.nanaland.globalvalue.constant.PAGING_SIZE
import com.jeju.nanaland.globalvalue.constant.getLocationFilterList
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
class NatureListViewModel @Inject constructor(
    private val getNatureListUseCase: GetNatureListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val locationList = getLocationFilterList()
    val selectedLocationList = getLocationSelectionList()
    private val _natureThumbnailCount = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val natureThumbnailCount = _natureThumbnailCount.asStateFlow()
    private val _natureThumbnailList = MutableStateFlow<UiState<List<NatureThumbnail>>>(UiState.Loading)
    val natureThumbnailList = _natureThumbnailList.asStateFlow()
    private var page = 0

    fun getNatureList() {
        var prevList: List<NatureThumbnail>? = null
        if (_natureThumbnailList.value is UiState.Success) {
            page++
            prevList = (_natureThumbnailList.value as UiState.Success).data
        }
        val requestData = GetNatureListRequest(
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            page = page,
            size = PAGING_SIZE
        )
        getNatureListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _natureThumbnailCount.update {
                            UiState.Success(data.totalElements)
                        }
                        _natureThumbnailList.update {
                            if (prevList.isNullOrEmpty()) {
                                UiState.Success(data.data)
                            } else {
                                UiState.Success(prevList + data.data)
                            }
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getNatureListUseCase") }
            .launchIn(viewModelScope)
    }

    fun clearNatureList() {
        _natureThumbnailList.update { UiState.Loading }
        page = 0
    }

    fun toggleFavorite(contentId: Int) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "NATURE"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _natureThumbnailList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.map {  item ->
                                    if (item.id == contentId) item.copy(favorite = data.favorite)
                                    else item
                                }
                                UiState.Success(newList)
                            } else {
                                uiState
                            }
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "toggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavoriteWithNoApi(contentId: Int, isFavorite: Boolean) {
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