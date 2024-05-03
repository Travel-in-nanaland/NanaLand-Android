package com.nanaland.ui.nature

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.nature.NatureThumbnailData
import com.nanaland.domain.request.nature.GetNatureListRequest
import com.nanaland.domain.usecase.nature.GetNatureListUseCase
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
    private val getNatureListUseCase: GetNatureListUseCase
) : ViewModel() {

    private val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    val selectedLocationList = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)
    private val _natureThumbnailCount = MutableStateFlow<UiState<Long>>(UiState.Loading)
    val natureThumbnailCount = _natureThumbnailCount.asStateFlow()
    private val _natureThumbnailList = MutableStateFlow<UiState<List<NatureThumbnailData>>>(UiState.Loading)
    val natureThumbnailList = _natureThumbnailList.asStateFlow()
    private var page = MutableStateFlow(0L)

    fun getThumbnailList() {
        var prevList: List<NatureThumbnailData>? = null
        if (_natureThumbnailList.value is UiState.Success) {
            page.update { it + 1 }
            prevList = (_natureThumbnailList.value as UiState.Success).data
        }
        val requestData = GetNatureListRequest(
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            page = page.value,
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
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "GetNatureListUseCase") }
            .launchIn(viewModelScope)
    }

    fun clearThumbnailList() {
        _natureThumbnailList.update { UiState.Loading }
        page.update { 0 }
    }

    init {
        getThumbnailList()
    }
}