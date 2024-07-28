package com.jeju.nanaland.ui.experience

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailData
import com.jeju.nanaland.domain.request.experience.GetExperienceListRequest
import com.jeju.nanaland.domain.usecase.experience.GetExperienceContentUseCase
import com.jeju.nanaland.domain.usecase.experience.GetExperienceListUseCase
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
    private val getExperienceContentUseCase: GetExperienceContentUseCase
) : ViewModel() {

    private val _selectedCategoryType = MutableStateFlow(ExperienceCategoryType.Activity)
    val selectedCategoryType = _selectedCategoryType.asStateFlow()
    private val locationList = listOf("제주시", "애월", "조천", "한경", "구좌", "한림", "우도", "추자", "서귀포시", "대정", "안덕", "남원", "표선", "성산")
    val selectedLocationList = getLocationSelectionList()
    private val keywordList = listOf("지상레저", "수상레저", "항공레저", "해양체험", "농촌체험", "힐링테라피")
    val selectedKeywordList = mutableStateListOf(false, false, false, false, false, false)
    private val _experienceThumbnailCount = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val experienceThumbnailCount = _experienceThumbnailCount.asStateFlow()
    private val _experienceThumbnailDataList = MutableStateFlow<UiState<List<ExperienceThumbnailData>>>(UiState.Loading)
    val experienceThumbnailList = _experienceThumbnailDataList.asStateFlow()
    private var page = 0

    fun updateSelectedCategoryType(type: ExperienceCategoryType) {
        clearExperienceList()
        _selectedCategoryType.update { type }
        repeat(selectedKeywordList.size) { selectedKeywordList[it] = false }
        repeat(selectedLocationList.size) { selectedLocationList[it] = false }
        getExperienceList(type = when (type) {
            ExperienceCategoryType.Activity -> {
                "ACTIVITY"
            }
            ExperienceCategoryType.CultureArt -> {
                "CULTURE_AND_ARTS"
            }
        })
    }

    fun clearExperienceList() {
        _experienceThumbnailDataList.update { UiState.Loading }
        page = 0
    }

    fun getExperienceList(type: String) {
        var prevList: List<ExperienceThumbnailData>? = null
        if (_experienceThumbnailDataList.value is UiState.Success) {
            page++
            prevList = (_experienceThumbnailDataList.value as UiState.Success).data
        }
        val requestData = GetExperienceListRequest(
            experienceType = type,
            keywordFilterList = selectedKeywordList.mapIndexedNotNull { idx, value ->
                if (value) keywordList[idx] else null
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

    }
}