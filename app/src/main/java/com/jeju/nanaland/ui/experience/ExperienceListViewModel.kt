package com.jeju.nanaland.ui.experience

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailData
import com.jeju.nanaland.domain.request.experience.GetExperienceListRequest
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.usecase.experience.GetExperienceContentUseCase
import com.jeju.nanaland.domain.usecase.experience.GetExperienceListUseCase
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.globalvalue.constant.getActivityKeywordSelectionList
import com.jeju.nanaland.globalvalue.constant.getCultureArtKeywordSelectionList
import com.jeju.nanaland.globalvalue.constant.getLocationSelectionList
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
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
class ExperienceListViewModel @Inject constructor(
    private val getExperienceListUseCase: GetExperienceListUseCase,
    private val getExperienceContentUseCase: GetExperienceContentUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _selectedCategoryType = MutableStateFlow(ExperienceCategoryType.Activity)
    val selectedCategoryType = _selectedCategoryType.asStateFlow()
    private val locationList = listOf("제주시", "애월", "조천", "한경", "구좌", "한림", "우도", "추자", "서귀포시", "대정", "안덕", "남원", "표선", "성산")
    val selectedLocationList = getLocationSelectionList()
    private val activityKeywordList = listOf("LAND_LEISURE", "WATER_LEISURE", "AIR_LEISURE", "MARINE_EXPERIENCE", "RURAL_EXPERIENCE", "HEALING_THERAPY")
    val selectedActivityKeywordList = getActivityKeywordSelectionList()
    private val cultureArtKeywordList = listOf("HISTORY", "EXHIBITION", "WORKSHOP", "ART_MUSEUM", "MUSEUM", "PARK", "PERFORMANCE", "RELIGIOUS_FACILITY", "THEME_PARK")
    val selectedCultureArtKeywordList = getCultureArtKeywordSelectionList()
    private val _experienceThumbnailCount = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val experienceThumbnailCount = _experienceThumbnailCount.asStateFlow()
    private val _experienceThumbnailDataList = MutableStateFlow<UiState<List<ExperienceThumbnailData>>>(UiState.Loading)
    val experienceThumbnailList = _experienceThumbnailDataList.asStateFlow()
    private var page = 0

    fun updateSelectedCategoryType(type: ExperienceCategoryType) {
        clearExperienceList()
        _selectedCategoryType.update { type }
        repeat(selectedActivityKeywordList.size) { selectedActivityKeywordList[it] = false }
        repeat(selectedCultureArtKeywordList.size) { selectedCultureArtKeywordList[it] = false }
        repeat(selectedLocationList.size) { selectedLocationList[it] = false }
        getExperienceList()
    }

    fun clearExperienceList() {
        _experienceThumbnailDataList.update { UiState.Loading }
        page = 0
    }

    fun getExperienceList() {
        var prevList: List<ExperienceThumbnailData>? = null
        if (_experienceThumbnailDataList.value is UiState.Success) {
            page++
            prevList = (_experienceThumbnailDataList.value as UiState.Success).data
        }
        val requestData = GetExperienceListRequest(
            experienceType = if (_selectedCategoryType.value == ExperienceCategoryType.Activity) "ACTIVITY"
                else "CULTURE_AND_ARTS",
            keywordFilterList = if (_selectedCategoryType.value == ExperienceCategoryType.Activity) {
                    selectedActivityKeywordList.mapIndexedNotNull { idx, value ->
                        if (value) activityKeywordList[idx] else null
                    }
                }
                else {
                    selectedCultureArtKeywordList.mapIndexedNotNull { idx, value ->
                        if (value) cultureArtKeywordList[idx] else null
                    }
                },
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            page = page,
            size = 12
            )
        getExperienceListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _experienceThumbnailCount.update {
                            UiState.Success(data.totalElements)
                        }
                        _experienceThumbnailDataList.update {
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
            .catch { LogUtil.e("flow Error", "getExperienceListUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleFavorite(contentId: Int) {
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = "EXPERIENCE"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _experienceThumbnailDataList.update { uiState ->
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
        _experienceThumbnailDataList.update { uiState ->
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