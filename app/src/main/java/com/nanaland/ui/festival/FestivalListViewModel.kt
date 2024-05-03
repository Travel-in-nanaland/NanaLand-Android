package com.nanaland.ui.festival

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.festival.FestivalThumbnailData
import com.nanaland.domain.request.festival.GetEndedFestivalListRequest
import com.nanaland.domain.request.festival.GetMonthlyFestivalListRequest
import com.nanaland.domain.request.festival.GetSeasonalFestivalListRequest
import com.nanaland.domain.usecase.festival.GetEndedFestivalListUseCase
import com.nanaland.domain.usecase.festival.GetMonthlyFestivalListUseCase
import com.nanaland.domain.usecase.festival.GetSeasonalFestivalListUseCase
import com.nanaland.globalvalue.type.FestivalCategoryType
import com.nanaland.util.log.LogUtil
import com.nanaland.util.network.onError
import com.nanaland.util.network.onException
import com.nanaland.util.network.onSuccess
import com.nanaland.util.string.getYearMonthDate
import com.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class FestivalListViewModel @Inject constructor(
    private val getMonthlyFestivalListUseCase: GetMonthlyFestivalListUseCase,
    private val getSeasonalFestivalListUseCase: GetSeasonalFestivalListUseCase,
    private val getEndedFestivalListUseCase: GetEndedFestivalListUseCase
) : ViewModel() {

    private val _selectedCategoryType = MutableStateFlow(FestivalCategoryType.Monthly)
    val selectedCategoryType = _selectedCategoryType.asStateFlow()
    private val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    val selectedLocationList = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)
    private val _startCalendar = MutableStateFlow<Calendar?>(null)
    val startCalendar = _startCalendar.asStateFlow()
    private val _endCalendar = MutableStateFlow<Calendar?>(null)
    val endCalendar = _endCalendar.asStateFlow()
    private val _festivalThumbnailCount = MutableStateFlow<UiState<Long>>(UiState.Loading)
    val festivalThumbnailCount = _festivalThumbnailCount.asStateFlow()
    private val _festivalThumbnailList = MutableStateFlow<UiState<List<FestivalThumbnailData>>>(UiState.Loading)
    val festivalThumbnailList = _festivalThumbnailList.asStateFlow()

    fun updateSelectedCategoryType(type: FestivalCategoryType) {
        _selectedCategoryType.update { type }
    }

    fun updateStartCalendar(calendar: Calendar?) {
        _startCalendar.update { calendar }
    }

    fun updateEndCalendar(calendar: Calendar?) {
        _endCalendar.update { calendar }
    }

    fun getMonthlyFestivalList() {
        _festivalThumbnailCount.update { UiState.Loading }
        _festivalThumbnailList.update { UiState.Loading }
        val requestData = GetMonthlyFestivalListRequest(
            page = 0,
            size = 12,
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            startDate = _startCalendar.value?.let { getYearMonthDate(it) },
            endDate = _startCalendar.value?.let { getYearMonthDate(it) }
        )
        getMonthlyFestivalListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _festivalThumbnailCount.update {
                            UiState.Success(data.data.totalElements)
                        }
                        _festivalThumbnailList.update {
                            UiState.Success(data.data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "GetMonthlyFestivalListUseCase") }
            .launchIn(viewModelScope)
    }

    fun getSeasonalFestivalList() {
        _festivalThumbnailCount.update { UiState.Loading }
        _festivalThumbnailList.update { UiState.Loading }
        val requestData = GetSeasonalFestivalListRequest(
            page = 0,
            size = 12,
            season = "spring"
        )
        getSeasonalFestivalListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _festivalThumbnailCount.update {
                            UiState.Success(data.data.totalElements)
                        }
                        _festivalThumbnailList.update {
                            UiState.Success(data.data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "GetSeasonalFestivalListUseCase") }
            .launchIn(viewModelScope)
    }

    fun getEndedFestivalList() {
        _festivalThumbnailCount.update { UiState.Loading }
        _festivalThumbnailList.update { UiState.Loading }
        val requestData = GetEndedFestivalListRequest(
            page = 0,
            size = 12
        )
        getEndedFestivalListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _festivalThumbnailCount.update {
                            UiState.Success(data.data.totalElements)
                        }
                        _festivalThumbnailList.update {
                            UiState.Success(data.data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "GetEndedFestivalListUseCase") }
            .launchIn(viewModelScope)
    }

    init {
        getMonthlyFestivalList()
    }
}