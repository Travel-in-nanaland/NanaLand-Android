package com.nanaland.ui.main.home.search

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.domain.entity.search.HotPostThumbnailData
import com.nanaland.domain.entity.search.SearchResultData
import com.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.nanaland.domain.request.search.GetAllSearchResultListRequest
import com.nanaland.domain.request.search.GetSearchResultListRequest
import com.nanaland.domain.response.search.GetAllSearchResultListResponse
import com.nanaland.domain.response.search.GetSearchResultListResponse
import com.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.nanaland.domain.usecase.recentsearchdatastore.AddRecentSearchUseCase
import com.nanaland.domain.usecase.recentsearchdatastore.DeleteRecentSearchUseCase
import com.nanaland.domain.usecase.recentsearchdatastore.GetAllRecentSearchUseCase
import com.nanaland.domain.usecase.search.GetAllSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetExperienceSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetFestivalSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetHotPostsUseCase
import com.nanaland.domain.usecase.search.GetMarketSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetNatureSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetTopKeywordsUseCase
import com.nanaland.globalvalue.type.SearchCategoryType
import com.nanaland.util.log.LogUtil
import com.nanaland.util.network.onError
import com.nanaland.util.network.onException
import com.nanaland.util.network.onSuccess
import com.nanaland.util.ui.UiState
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

    fun updateSelectedCategory(category: SearchCategoryType) {
        _selectedCategory.update { category }
    }

    fun getTopKeywords() {
        _topKeywordList.update { UiState.Loading }
        getTopKeywordsUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _topKeywordList.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "GetPopularSearchListUseCase") }
            .launchIn(viewModelScope)
    }

    fun getSearchResult(keyword: String) {
        _categorizedSearchResultList.update { UiState.Loading }
        val requestData = when (_selectedCategory.value) {
            SearchCategoryType.All -> GetAllSearchResultListRequest(keyword = keyword)
            else -> GetSearchResultListRequest(
                keyword = keyword,
                page = 0,
                size = 12
            )
        }
        when (_selectedCategory.value) {
            SearchCategoryType.All -> getAllSearchResultListUseCase(requestData as GetAllSearchResultListRequest)
            SearchCategoryType.Experience -> getExperienceSearchResultListUseCase(requestData as GetSearchResultListRequest)
            SearchCategoryType.Festival -> getFestivalSearchResultListUseCase(requestData as GetSearchResultListRequest)
            SearchCategoryType.Nature -> getNatureSearchResultListUseCase(requestData as GetSearchResultListRequest)
            SearchCategoryType.Market -> getMarketSearchResultListUseCase(requestData as GetSearchResultListRequest)
        }.onEach { networkResult ->
            networkResult.onSuccess { _, data ->
                data?.let {
                    when (_selectedCategory.value) {
                        SearchCategoryType.All -> {
                            data as GetAllSearchResultListResponse
                            val map = mapOf(
                                SearchCategoryType.Nature.name to data.data.nature,
                                SearchCategoryType.Festival.name to data.data.festival,
                                SearchCategoryType.Market.name to data.data.market,
                                SearchCategoryType.Experience.name to data.data.experience)
                            _allSearchResultList.update {
                                UiState.Success(map)
                            }
                        }
                        else -> {
                            data as GetSearchResultListResponse
                            _categorizedSearchResultList.update {
                                UiState.Success(data.data)
                            }
                        }
                    }
                }
            }.onError { code, message ->
                LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
            }.onException {
                LogUtil.printLog("onException", "${it.message}")
            }
        }
        .catch { LogUtil.printLog("flow Error", "GetNatureSearchResultListUseCase") }
        .launchIn(viewModelScope)
    }

    fun getAllRecentSearches() {
        viewModelScope.launch {
            val map = getAllRecentSearchUseCase().first()
            val sortedPairList = map.entries
                .sortedByDescending { it.key.name }
                .map { it.key.name to it.value.toString() }
            _recentSearchList.update {
                sortedPairList
            }
        }
    }

    fun addRecentSearch(keyword: String) {
        viewModelScope.launch {
            // 이미 같은 검색어가 있다면 삭제하고 새로 추가한다.
            val duplicateItemKey = _recentSearchList.value.firstOrNull() { it.second == keyword }?.first
            if (duplicateItemKey != null) {
                deleteRecentSearch(duplicateItemKey)
            }
            val time = Calendar.getInstance().timeInMillis
            addRecentSearchUseCase("$time", keyword)
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
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _hotPostList.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .launchIn(viewModelScope)
    }

    fun toggleHotPostFavorite(contentId: Long, category: String?) {
        Log.e("toggleHotPostFavorite", "toggleHotPostFavorite")
        if (category == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = category
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _hotPostList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.map { item ->
                                    if (item.id == contentId && item.category == category) item.copy(favorite = data.data.favorite)
                                    else item
                                }
                                UiState.Success(newList)
                            } else {
                                uiState
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "ToggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleSearchResultFavorite(contentId: Long, category: String?) {
        Log.e("toggleSearchResultFavorite", "toggleSearchResultFavorite")
        if (category == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = category
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _categorizedSearchResultList.update { uiState ->
                            if (uiState is UiState.Success) {
                                val newList = uiState.data.data.map { item ->
                                    if (item.id == contentId) item.copy(favorite = data.data.favorite)
                                    else item
                                }
                                UiState.Success(uiState.data.copy(data = newList))
                            } else {
                                uiState
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "ToggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleSearchResultFavoriteWithNoApi(contentId: Long, isLiked: Boolean) {
        _categorizedSearchResultList.update { uiState ->
            if (uiState is UiState.Success) {
                val newList = uiState.data.data.map { item ->
                    if (item.id == contentId) item.copy(favorite = isLiked)
                    else item
                }
                UiState.Success(uiState.data.copy(data = newList))
            } else {
                uiState
            }
        }
    }

    fun toggleAllSearchResultFavorite(contentId: Long, category: String?) {
        Log.e("toggleAllSearchResultFavorite", "toggleAllSearchResultFavorite")
        if (category == null) return
        val requestData = ToggleFavoriteRequest(
            id = contentId,
            category = category
        )
        toggleFavoriteUseCase(requestData)
            .onEach { networkResult ->
                networkResult.onSuccess { code, data ->
                    data?.let {
                        _allSearchResultList.update { uiState ->
                            if (uiState is UiState.Success) {
                                Log.e("toggleAllSearchResultFavorite", "${uiState.data.hashCode()}")
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
                                            if (item.id == contentId) item.copy(favorite = data.data.favorite)
                                            else item
                                        }
                                    )
                                    newMap[categoryString] = newSearchResultData
                                }
                                Log.e("toggleAllSearchResultFavorite", "${newMap}")
                                UiState.Success(newMap)
                            } else {
                                uiState
                            }
                        }
                    }
                }.onError { code, message ->
                    LogUtil.printLog("onError", "code: ${code}\nmessage: $message")
                }.onException {
                    LogUtil.printLog("onException", "${it.message}")
                }
            }
            .catch { LogUtil.printLog("flow Error", "ToggleFavoriteUseCase") }
            .launchIn(viewModelScope)
    }

    fun toggleAllSearchResultFavoriteWithNoApi(contentId: Long, isLiked: Boolean, category: String?) {
        if (category == null) return
        _allSearchResultList.update { uiState ->
            if (uiState is UiState.Success) {
                Log.e("toggleAllSearchResultFavorite", "${uiState.data.hashCode()}")
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
                            if (item.id == contentId) item.copy(favorite = isLiked)
                            else item
                        }
                    )
                    newMap[categoryString] = newSearchResultData
                }
                Log.e("toggleAllSearchResultFavorite", "${newMap}")
                UiState.Success(newMap)
            } else {
                uiState
            }
        }
    }
}