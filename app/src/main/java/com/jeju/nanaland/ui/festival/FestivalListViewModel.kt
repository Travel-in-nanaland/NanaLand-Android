package com.jeju.nanaland.ui.festival

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.festival.FestivalThumbnailData
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.festival.GetEndedFestivalListRequest
import com.jeju.nanaland.domain.request.festival.GetMonthlyFestivalListRequest
import com.jeju.nanaland.domain.request.festival.GetSeasonalFestivalListRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.festival.GetEndedFestivalListUseCase
import com.jeju.nanaland.domain.usecase.festival.GetMonthlyFestivalListUseCase
import com.jeju.nanaland.domain.usecase.festival.GetSeasonalFestivalListUseCase
import com.jeju.nanaland.globalvalue.constant.getLocationList
import com.jeju.nanaland.globalvalue.constant.getLocationSelectionList
import com.jeju.nanaland.globalvalue.type.FestivalCategoryType
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.string.getYearMonthDate
import com.jeju.nanaland.util.ui.UiState
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
    private val getEndedFestivalListUseCase: GetEndedFestivalListUseCase,
    private val getSeasonalFestivalListUseCase: GetSeasonalFestivalListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _selectedCategoryType = MutableStateFlow(FestivalCategoryType.Monthly)
    val selectedCategoryType = _selectedCategoryType.asStateFlow()
    private val locationList = listOf("제주시", "애월", "조천", "한경", "구좌", "한림", "우도", "추자", "서귀포시", "대정", "안덕", "남원", "표선", "성산")
    val selectedLocationList = getLocationSelectionList()
    private val seasonList = listOf("spring", "summer", "autumn", "winter")
    val selectedSeasonList = mutableStateListOf(true, false, false, false)
    private val _startCalendar = MutableStateFlow<Calendar>(Calendar.getInstance())
    val startCalendar = _startCalendar.asStateFlow()
    private val _endCalendar = MutableStateFlow<Calendar>(Calendar.getInstance())
    val endCalendar = _endCalendar.asStateFlow()
    private val _festivalThumbnailCount = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val festivalThumbnailCount = _festivalThumbnailCount.asStateFlow()
    private val _festivalThumbnailList = MutableStateFlow<UiState<List<FestivalThumbnailData>>>(UiState.Loading)
    val festivalThumbnailList = _festivalThumbnailList.asStateFlow()
    private var page = 0

    fun updateSelectedCategoryType(type: FestivalCategoryType) {
        clearFestivalList()
        _selectedCategoryType.update { type }
        when (type) {
            FestivalCategoryType.Monthly -> {
                repeat(selectedLocationList.size) { selectedLocationList[it] = false }
                _startCalendar.update { Calendar.getInstance() }
                _endCalendar.update { Calendar.getInstance() }
                getMonthlyFestivalList()
            }
            FestivalCategoryType.Ended -> {
                repeat(selectedLocationList.size) { selectedLocationList[it] = false }
                getEndedFestivalList()
            }
            FestivalCategoryType.Seasonal -> {
                // 현재 월에 따라 자동으로 봄/여름/가을/겨울이 미리 선택된다.
                repeat(4) { selectedSeasonList[it] = false }
                var currMonth = Calendar.getInstance().get(Calendar.MONTH)
                currMonth -= 2
                if (currMonth < 0) currMonth += 12
                selectedSeasonList[currMonth / 3] = true
                getSeasonalFestivalList()
            }
        }
    }

    fun updateStartCalendar(calendar: Calendar) {
        _startCalendar.update { calendar }
    }

    fun updateEndCalendar(calendar: Calendar) {
        _endCalendar.update { calendar }
    }

    fun getMonthlyFestivalList() {
        var prevList: List<FestivalThumbnailData>? = null
        if (_festivalThumbnailList.value is UiState.Success) {
            page++
            prevList = (_festivalThumbnailList.value as UiState.Success).data
        }
        val requestData = GetMonthlyFestivalListRequest(
            page = page,
            size = 12,
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            startDate = getYearMonthDate(_startCalendar.value),
            endDate = getYearMonthDate(_endCalendar.value)
        )
        getMonthlyFestivalListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _festivalThumbnailCount.update {
                            UiState.Success(data.totalElements)
                        }
                        _festivalThumbnailList.update {
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
            .catch { LogUtil.e("flow Error", "getMonthlyFestivalListUseCase") }
            .launchIn(viewModelScope)
    }

    fun getEndedFestivalList() {
        var prevList: List<FestivalThumbnailData>? = null
        if (_festivalThumbnailList.value is UiState.Success) {
            page++
            prevList = (_festivalThumbnailList.value as UiState.Success).data
        }
        val requestData = GetEndedFestivalListRequest(
            page = page,
            size = 12,
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            }
        )
        getEndedFestivalListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _festivalThumbnailCount.update {
                            UiState.Success(data.totalElements)
                        }
                        _festivalThumbnailList.update {
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
            .catch { LogUtil.e("flow Error", "getEndedFestivalListUseCase") }
            .launchIn(viewModelScope)
    }

    fun getSeasonalFestivalList() {
        var prevList: List<FestivalThumbnailData>? = null
        if (_festivalThumbnailList.value is UiState.Success) {
            page++
            prevList = (_festivalThumbnailList.value as UiState.Success).data
        }
        val requestData = GetSeasonalFestivalListRequest(
            page = page,
            size = 12,
            season = seasonList[selectedSeasonList.withIndex()
                .filter { selectedSeasonList[it.index] }[0].index]
        )
        getSeasonalFestivalListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _festivalThumbnailCount.update {
                            UiState.Success(data.totalElements)
                        }
                        _festivalThumbnailList.update {
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
            .catch { LogUtil.e("flow Error", "getSeasonalFestivalListUseCase") }
            .launchIn(viewModelScope)
    }

    fun clearFestivalList() {
        _festivalThumbnailList.update { UiState.Loading }
        page = 0
    }

    fun toggleFavorite(contentId: Int) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "FESTIVAL"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _festivalThumbnailList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.map { item ->
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
        _festivalThumbnailList.update { uiState ->
            if (uiState is UiState.Success) {
                val newList = uiState.data.map { item ->
                    if (item.id == contentId) item.copy(favorite = isFavorite)
                    else item
                }
                UiState.Success(newList)
            } else {
                uiState
            }
        }
    }
}