package com.jeju.nanaland.ui.restaurant

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.restaurant.RestaurantThumbnailData
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.restaurant.GetRestaurantListRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.restaurant.GetRestaurantListUseCase
import com.jeju.nanaland.globalvalue.constant.getLocationSelectionList
import com.jeju.nanaland.globalvalue.constant.getRestaurantKeywordSelectionList
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
class RestaurantListViewModel @Inject constructor(
    private val getRestaurantListUseCase: GetRestaurantListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val keywordList = listOf("KOREAN", "CHINESE", "JAPANESE", "WESTERN", "SNACK", "SOUTH_AMERICAN", "SOUTHEAST_ASIAN", "VEGAN", "HALAL", "MEAT_BLACK_PORK", "SEAFOOD", "CHICKEN_BURGER", "CAFE_DESSERT", "PUB_FOOD_PUB")
    val selectedRestaurantKeywordList = getRestaurantKeywordSelectionList()
    private val _restaurantThumbnailCount = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val restaurantThumbnailCount = _restaurantThumbnailCount.asStateFlow()
    private val _restaurantThumbnailDataList = MutableStateFlow<UiState<List<RestaurantThumbnailData>>>(UiState.Loading)
    val restaurantThumbnailDataList = _restaurantThumbnailDataList.asStateFlow()
    private val locationList = listOf("제주시", "애월", "조천", "한경", "구좌", "한림", "우도", "추자", "서귀포시", "대정", "안덕", "남원", "표선", "성산")
    val selectedLocationList = getLocationSelectionList()
    private var page = 0

    fun clearExperienceList() {
        _restaurantThumbnailDataList.update { UiState.Loading }
        page = 0
    }

    fun getRestaurantList() {
        var prevList: List<RestaurantThumbnailData>? = null
        if (_restaurantThumbnailDataList.value is UiState.Success) {
            page++
            prevList = (_restaurantThumbnailDataList.value as UiState.Success).data
        }
        val requestData = GetRestaurantListRequest(
            keywordFilterList = selectedRestaurantKeywordList.mapIndexedNotNull { idx, value ->
                    if (value) keywordList[idx] else null
                },
            addressFilterList = selectedLocationList.mapIndexedNotNull { idx, value ->
                if (value) locationList[idx] else null
            },
            page = page,
            size = 12
        )
        getRestaurantListUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _restaurantThumbnailCount.update {
                            UiState.Success(data.totalElements)
                        }
                        _restaurantThumbnailDataList.update {
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
            category = "RESTAURANT"
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _restaurantThumbnailDataList.update { uiState ->
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
        _restaurantThumbnailDataList.update { uiState ->
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