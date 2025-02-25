package com.jeju.nanaland.ui.main.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeju.nanaland.domain.entity.search.HotPostThumbnailData
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.domain.entity.search.SearchResultThumbnailData
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.search.GetAllSearchResultListRequest
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import com.jeju.nanaland.domain.response.search.AllSearchResultListData
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.AddRecentSearchUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.DeleteRecentSearchUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.GetAllRecentSearchUseCase
import com.jeju.nanaland.domain.usecase.search.GetAllSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetExperienceSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetFestivalSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetHotPostsUseCase
import com.jeju.nanaland.domain.usecase.search.GetMarketSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetNanaPickSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetNatureSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetRestaurantSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetTopKeywordsUseCase
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
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getTopKeywordsUseCase: GetTopKeywordsUseCase,
    private val getNatureSearchResultListUseCase: GetNatureSearchResultListUseCase,
    private val getMarketSearchResultListUseCase: GetMarketSearchResultListUseCase,
    private val getFestivalSearchResultListUseCase: GetFestivalSearchResultListUseCase,
    private val getExperienceSearchResultListUseCase: GetExperienceSearchResultListUseCase,
    private val getNanaPickSearchResultListUseCase: GetNanaPickSearchResultListUseCase,
    private val getRestaurantSearchResultListUseCase: GetRestaurantSearchResultListUseCase,
    private val getAllSearchResultListUseCase: GetAllSearchResultListUseCase,
    private val getAllRecentSearchUseCase: GetAllRecentSearchUseCase,
    private val addRecentSearchUseCase: AddRecentSearchUseCase,
    private val deleteRecentSearchUseCase: DeleteRecentSearchUseCase,
    private val getHotPostsUseCase: GetHotPostsUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
) : ViewModel() {
    private val _topKeywordList = MutableStateFlow<UiState<List<String>>>(UiState.Loading)
    val topKeywordList = _topKeywordList.asStateFlow()
    private val _hotPostList = MutableStateFlow<UiState<List<HotPostThumbnailData>>>(UiState.Loading)
    val hotPostList = _hotPostList.asStateFlow()
    private val _selectedCategory = MutableStateFlow(SearchCategoryType.All)
    val selectedCategory = _selectedCategory.asStateFlow()
    private val _allSearchResultList = MutableStateFlow<UiState<Map<String, SearchResultData>>>(UiState.Loading)
    val allSearchResultList = _allSearchResultList.asStateFlow()
    private val _categorizedSearchResultList = MutableStateFlow<UiState<SearchResultData>>(UiState.Loading)
    val categorizedSearchResultList = _categorizedSearchResultList.asStateFlow()
    private val _recentSearchList = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val recentSearchList = _recentSearchList.asStateFlow()
    private var page = 0

    fun updateSelectedCategoryType(category: SearchCategoryType) {
        page = 0
        _categorizedSearchResultList.update { UiState.Loading }
        _selectedCategory.update { category }
    }

    fun getTopKeywords() {
        _topKeywordList.update { UiState.Loading }
        getTopKeywordsUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _topKeywordList.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .catch { LogUtil.e("flow Error", "getTopKeywordsUseCase") }
            .launchIn(viewModelScope)
    }

    private var oldKeyword = ""
    fun getSearchResult(keyword: String) {
        var prevList: List<SearchResultThumbnailData>? = null
        if (_categorizedSearchResultList.value is UiState.Success) {
            if(oldKeyword != keyword) {
                page = 0
                oldKeyword = keyword
            } else {
                page++
                prevList = (_categorizedSearchResultList.value as UiState.Success).data.data
            }
        }
        val requestData = when (_selectedCategory.value) {
            SearchCategoryType.All -> GetAllSearchResultListRequest(keyword = keyword)
            else -> GetSearchResultListRequest(
                keyword = keyword,
                page = page,
                size = PAGING_SIZE
            )
        }
        when (_selectedCategory.value) {
            SearchCategoryType.All -> getAllSearchResultListUseCase(requestData as GetAllSearchResultListRequest)
            SearchCategoryType.Activity -> getExperienceSearchResultListUseCase(requestData as GetSearchResultListRequest, SearchCategoryType.Activity)
            SearchCategoryType.Art -> getExperienceSearchResultListUseCase(requestData as GetSearchResultListRequest, SearchCategoryType.Art)
            SearchCategoryType.Festival -> getFestivalSearchResultListUseCase(requestData as GetSearchResultListRequest)
            SearchCategoryType.Nature -> getNatureSearchResultListUseCase(requestData as GetSearchResultListRequest)
            SearchCategoryType.Market -> getMarketSearchResultListUseCase(requestData as GetSearchResultListRequest)
            SearchCategoryType.NanaPick -> getNanaPickSearchResultListUseCase(requestData as GetSearchResultListRequest)
            SearchCategoryType.Restaurant -> getRestaurantSearchResultListUseCase(requestData as GetSearchResultListRequest)
        }.onEach { networkResult ->
            networkResult.onSuccess { code, message, data ->
                data?.let {
                    when (_selectedCategory.value) {
                        SearchCategoryType.All -> {
                            data as AllSearchResultListData
                            val map = mapOf(
                                SearchCategoryType.Nature.name to data.nature,
                                SearchCategoryType.Festival.name to data.festival,
                                SearchCategoryType.Market.name to data.market,
                                SearchCategoryType.Activity.name to data.activity,
                                SearchCategoryType.Art.name to data.art,
                                SearchCategoryType.Restaurant.name to data.restaurant,
                                SearchCategoryType.NanaPick.name to data.nana
                            )
                            _allSearchResultList.update {
                                UiState.Success(map)
                            }
                        }
                        else -> {
                            data as SearchResultData
                            _categorizedSearchResultList.update {
                                if (prevList.isNullOrEmpty()) {
                                    UiState.Success(data)
                                } else {
                                    UiState.Success(data.copy(data = prevList + data.data))
                                }
                            }
                        }
                    }
                }
            }.onError { code, message ->

            }.onException {

            }
        }
        .catch { LogUtil.e("flow Error", "getSearchResult\n" + it.message) }
        .launchIn(viewModelScope)
    }

    fun getAllRecentSearches() {
        viewModelScope.launch {
            val map = getAllRecentSearchUseCase().first()
            val sortedPairList = map.entries
                .sortedByDescending { it.value.toString() }
                .map { it.key.name to it.value.toString() }
            _recentSearchList.update {
                sortedPairList
            }
        }
    }

    fun addRecentSearch(keyword: String) {
        viewModelScope.launch {
            val time = Calendar.getInstance().timeInMillis
            addRecentSearchUseCase(keyword, "$time")
        }
    }

    fun deleteRecentSearch(key: String) {
        viewModelScope.launch {
            deleteRecentSearchUseCase(key)
            getAllRecentSearches()
        }
    }

    fun deleteAllRecentSearches() {
        _recentSearchList.value.forEach { item ->
            deleteRecentSearch(item.first)
        }
        getAllRecentSearches()
    }

    fun getHotPosts() {
        getHotPostsUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _hotPostList.update {
                            UiState.Success(data)
                        }
                    }
                }.onError { code, message ->

                }.onException {

                }
            }
            .launchIn(viewModelScope)
    }

    fun toggleHotPostFavorite(contentId: Int, category: String?) {
        LogUtil.e("toggleHotPostFavorite", "toggleHotPostFavorite")
        if (category == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = category
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _hotPostList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.map { item ->
                                    if (item.id == contentId && item.category == category) item.copy(favorite = data.favorite)
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

    fun toggleSearchResultFavorite(contentId: Int, category: String?) {
        LogUtil.e("toggleSearchResultFavorite", "toggleSearchResultFavorite")
        if (category == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = category
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _categorizedSearchResultList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.data.map { item ->
                                    if (item.id == contentId) item.copy(favorite = data.favorite)
                                    else item
                                }
                                UiState.Success(uiState.data.copy(data = newList))
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

    fun toggleSearchResultFavoriteWithNoApi(contentId: Int, isFavorite: Boolean) {
        _categorizedSearchResultList.update { uiState ->
            if (uiState is UiState.Success) {
                val newList = uiState.data.data.map { item ->
                    if (item.id == contentId) item.copy(favorite = isFavorite)
                    else item
                }
                UiState.Success(uiState.data.copy(data = newList))
            } else {
                uiState
            }
        }
    }

    fun toggleAllSearchResultFavorite(contentId: Int, category: String?) {
        LogUtil.e("toggleAllSearchResultFavorite", "toggleAllSearchResultFavorite")
        if (category == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = category
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, message, data ->
                    data?.let {
                        _allSearchResultList.update { uiState ->
                            if (uiState is UiState.Success) {
                                LogUtil.e("toggleAllSearchResultFavorite", "${uiState.data.hashCode()}")
                                val newMap = uiState.data.toMutableMap()
                                val categoryString = when (category) {
                                    "NATURE" -> "Nature"
                                    "FESTIVAL" -> "Festival"
                                    "MARKET" -> "Market"
                                    "EXPERIENCE" -> "Experience"
                                    "RESTAURANT" -> "Restaurant"
                                    "NANAPICK" -> "NanaPick"
                                    else -> ""
                                }
                                if (newMap.containsKey(categoryString)) {
                                    val newSearchResultData = newMap[categoryString]!!.copy(
                                        data = newMap[categoryString]!!.data.map { item ->
                                            if (item.id == contentId) item.copy(favorite = data.favorite)
                                            else item
                                        }
                                    )
                                    newMap[categoryString] = newSearchResultData
                                }
                                LogUtil.e("toggleAllSearchResultFavorite", "${newMap}")
                                UiState.Success(newMap)
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

    fun toggleAllSearchResultFavoriteWithNoApi(contentId: Int, isFavorite: Boolean, category: String?) {
        if (category == null) return
        _allSearchResultList.update { uiState ->
            if (uiState is UiState.Success) {
                LogUtil.e("toggleAllSearchResultFavorite", "${uiState.data.hashCode()}")
                val newMap = uiState.data.toMutableMap()
                val categoryString = when (category) {
                    "NATURE" -> "Nature"
                    "FESTIVAL" -> "Festival"
                    "MARKET" -> "Market"
                    "EXPERIENCE" -> "Experience"
                    else -> ""
                }
                if (newMap.containsKey(categoryString)) {
                    val newSearchResultData = newMap[categoryString]!!.copy(
                        data = newMap[categoryString]!!.data.map { item ->
                            if (item.id == contentId) item.copy(favorite = isFavorite)
                            else item
                        }
                    )
                    newMap[categoryString] = newSearchResultData
                }
                LogUtil.e("toggleAllSearchResultFavorite", "${newMap}")
                UiState.Success(newMap)
            } else {
                uiState
            }
        }
    }
}