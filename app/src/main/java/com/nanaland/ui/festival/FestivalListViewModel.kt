package com.nanaland.ui.festival

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.festival.FestivalThumbnailData
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.request.festival.GetEndedFestivalListRequest
import com.nanaland.domain.request.festival.GetMonthlyFestivalListRequest
import com.nanaland.domain.request.festival.GetSeasonalFestivalListRequest
import com.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
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
    private val getEndedFestivalListUseCase: GetEndedFestivalListUseCase,
    private val getSeasonalFestivalListUseCase: GetSeasonalFestivalListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _selectedCategoryType = MutableStateFlow(FestivalCategoryType.Monthly)
    val selectedCategoryType = _selectedCategoryType.asStateFlow()
    private val locationList = listOf("전체", "제주시", "애월", "서귀포시", "성산", "한림", "조천", "구좌", "한경", "대정", "안덕", "남원", "표선", "우도")
    val selectedLocationList = mutableStateListOf(false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false)
    private val seasonList = listOf("spring", "summer", "autumn", "winter")
    val selectedSeasonList = mutableStateListOf(true, false, false, false)
    private val _startCalendar = MutableStateFlow<Calendar>(Calendar.getInstance())
    val startCalendar = _startCalendar.asStateFlow()
    private val _endCalendar = MutableStateFlow<Calendar>(Calendar.getInstance())
    val endCalendar = _endCalendar.asStateFlow()
    private val _festivalThumbnailCount = MutableStateFlow<UiState<Long>>(UiState.Loading)
    val festivalThumbnailCount = _festivalThumbnailCount.asStateFlow()
    private val _festivalThumbnailList = MutableStateFlow<UiState<List<FestivalThumbnailData>>>(UiState.Loading)
    val festivalThumbnailList = _festivalThumbnailList.asStateFlow()
    private var page = 0L

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
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _festivalThumbnailCount.update {
                            UiState.Success(data.data.totalElements)
                        }
                        _festivalThumbnailList.update {
                            if (prevList.isNullOrEmpty()) {
                                UiState.Success(data.data.data)
                            } else {
                                UiState.Success(prevList + data.data.data)
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "GetMonthlyFestivalListUseCase") }
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
                            if (prevList.isNullOrEmpty()) {
                                UiState.Success(data.data.data)
                            } else {
                                UiState.Success(prevList + data.data.data)
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "GetEndedFestivalListUseCase") }
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
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _festivalThumbnailCount.update {
                            UiState.Success(data.data.totalElements)
                        }
                        _festivalThumbnailList.update {
                            if (prevList.isNullOrEmpty()) {
                                UiState.Success(data.data.data)
                            } else {
                                UiState.Success(prevList + data.data.data)
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "GetSeasonalFestivalListUseCase") }
            .launchIn(viewModelScope)
    }

    fun clearFestivalList() {
        _festivalThumbnailList.update { UiState.Loading }
        page = 0
    }

    fun toggleFavorite(contentId: Long) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "FESTIVAL"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _festivalThumbnailList.update { uiState ->
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
                    LogUtil.log("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.log("onException", "${it.message}")
                }
            }
            .catch { LogUtil.log("flow Error", "ToggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavoriteWithNoApi(contentId: Long, isFavorite: Boolean) {
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

    init {
        getMonthlyFestivalList()
    }
}