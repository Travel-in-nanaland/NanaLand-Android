package com.jeju.nanaland.ui.main.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.favorite.FavoriteThumbnailData
import com.jeju.nanaland.domain.request.favorite.GetFavoriteListRequest
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.usecase.favorite.GetAllFavoriteListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteExperienceListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteFestivalListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteMarketListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteNanaPickListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteNatureListUseCase
import com.jeju.nanaland.domain.usecase.favorite.GetFavoriteRestaurantListUseCase
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.globalvalue.constant.PAGING_SIZE
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
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
class FavoriteViewModel @Inject constructor(
    private val getAllFavoriteListUseCase: GetAllFavoriteListUseCase,
    private val getFavoriteNatureListUseCase: GetFavoriteNatureListUseCase,
    private val getFavoriteFestivalListUseCase: GetFavoriteFestivalListUseCase,
    private val getFavoriteMarketListUseCase: GetFavoriteMarketListUseCase,
    private val getFavoriteExperienceListUseCase: GetFavoriteExperienceListUseCase,
    private val getFavoriteNanaPickListUseCase: GetFavoriteNanaPickListUseCase,
    private val getFavoriteRestaurantListUseCase: GetFavoriteRestaurantListUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase
) : ViewModel() {

    private val _selectedCategory = MutableStateFlow(SearchCategoryType.All)
    val selectedCategory = _selectedCategory.asStateFlow()
    private val _favoriteThumbnailCount = MutableStateFlow<UiState<Int>>(UiState.Loading)
    val favoriteThumbnailCount = _favoriteThumbnailCount.asStateFlow()
    private val _favoriteThumbnailList = MutableStateFlow<UiState<List<FavoriteThumbnailData>>>(UiState.Loading)
    val favoriteThumbnailList = _favoriteThumbnailList.asStateFlow()
    private var page = 0

    fun updateSelectedCategoryType(category: SearchCategoryType) {
        clearFavoriteList()
        _selectedCategory.update { category }
    }

    fun getFavoriteList() {
        var prevList: List<FavoriteThumbnailData>? = null
        if (_favoriteThumbnailList.value is UiState.Success) {
            page++
            prevList = (_favoriteThumbnailList.value as UiState.Success).data
        }
        val requestData = GetFavoriteListRequest(
            page = page,
            size = PAGING_SIZE
        )
        when (_selectedCategory.value) {
            SearchCategoryType.All -> getAllFavoriteListUseCase(requestData)
            SearchCategoryType.Nature -> getFavoriteNatureListUseCase(requestData)
            SearchCategoryType.Festival -> getFavoriteFestivalListUseCase(requestData)
            SearchCategoryType.Market -> getFavoriteMarketListUseCase(requestData)
            SearchCategoryType.Activity -> getFavoriteExperienceListUseCase(requestData, SearchCategoryType.Activity)
            SearchCategoryType.Art -> getFavoriteExperienceListUseCase(requestData, SearchCategoryType.Art)
            SearchCategoryType.NanaPick -> getFavoriteNanaPickListUseCase(requestData)
            SearchCategoryType.Restaurant -> getFavoriteRestaurantListUseCase(requestData)
        }.onEach {  networkResult ->
            networkResult.onSuccess { code, message, data ->
                data?.let {
                    _favoriteThumbnailCount.update {
                        UiState.Success(data.totalElements)
                    }
                    _favoriteThumbnailList.update {
                        val newData = data.data.map { it.copy(favorite = true) }
                        if (prevList.isNullOrEmpty()) {
                            UiState.Success(newData)
                        } else {
                            UiState.Success(prevList + newData)
                        }
                    }
                }
            }.onError { code, message ->

            }.onException {

            }
        }
            .catch { LogUtil.e("flow Error", "getFavoriteList") }
        .launchIn(viewModelScope)
    }

    fun clearFavoriteList() {
        _favoriteThumbnailList.update { UiState.Loading }
        page = 0
    }

    fun toggleFavorite(contentId: Int, category: String?) {
        if (category == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = category
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _favoriteThumbnailList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.toMutableList()
                                val changeItemIndex = newList.indexOfFirst {
                                    it.id == contentId
                                }
                                newList[changeItemIndex] = newList[changeItemIndex].copy(
                                    favorite = !newList[changeItemIndex].favorite
                                )
                                _favoriteThumbnailCount.update {
                                    if (it is UiState.Success) {
                                        if(newList[changeItemIndex].favorite)
                                            UiState.Success(it.data + 1)
                                        else
                                            UiState.Success(it.data - 1)
                                    } else
                                        it
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

    fun toggleFavoriteWithNoApi(contentId: Int) {
        _favoriteThumbnailList.update { uiState ->
            if (uiState is UiState.Success) {
                val newList = uiState.data.filter {  item ->
                    item.id != contentId
                }
                _favoriteThumbnailCount.update {
                    if (it is UiState.Success) {
                        UiState.Success(it.data - 1)
                    } else it
                }
                UiState.Success(newList)
            } else {
                uiState
            }
        }
    }
}