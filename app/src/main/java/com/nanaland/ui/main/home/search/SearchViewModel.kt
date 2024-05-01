package com.nanaland.ui.main.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nanaland.BuildConfig
import com.nanaland.domain.entity.search.SearchResult
import com.nanaland.domain.request.search.GetAllSearchResultListRequest
import com.nanaland.domain.request.search.GetSearchResultListRequest
import com.nanaland.domain.response.search.GetAllSearchResultListResponse
import com.nanaland.domain.response.search.GetSearchResultListResponse
import com.nanaland.domain.usecase.search.GetAllSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetExperienceSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetFestivalSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetMarketSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetNatureSearchResultListUseCase
import com.nanaland.domain.usecase.search.GetTopKeywordsUseCase
import com.nanaland.globalvalue.type.SearchCategoryType
import com.nanaland.util.log.LogUtil
import com.nanaland.util.network.finally
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
class SearchViewModel @Inject constructor(
    private val getTopKeywordsUseCase: GetTopKeywordsUseCase,
    private val getNatureSearchResultListUseCase: GetNatureSearchResultListUseCase,
    private val getMarketSearchResultListUseCase: GetMarketSearchResultListUseCase,
    private val getFestivalSearchResultListUseCase: GetFestivalSearchResultListUseCase,
    private val getExperienceSearchResultListUseCase: GetExperienceSearchResultListUseCase,
    private val getAllSearchResultListUseCase: GetAllSearchResultListUseCase
) : ViewModel() {
    private val _topKeywords = MutableStateFlow<UiState<List<String>>>(UiState.Empty)
    val topKeywords = _topKeywords.asStateFlow()
    private val _selectedCategory = MutableStateFlow(SearchCategoryType.All)
    val selectedCategory = _selectedCategory.asStateFlow()
    private val _allSearchResultList = MutableStateFlow<UiState<Map<String, SearchResult>>>(UiState.Empty)
    val allSearchResultList = _allSearchResultList.asStateFlow()
    private val _categorizedSearchResultList = MutableStateFlow<UiState<SearchResult>>(UiState.Empty)
    val categorizedSearchResultList = _categorizedSearchResultList.asStateFlow()

    fun updateSelectedCategory(category: SearchCategoryType) {
        _selectedCategory.update { category }
    }

    fun getTopKeywords() {
        _topKeywords.update { UiState.Loading }
        getTopKeywordsUseCase()
            .onEach { networkResult ->
                networkResult.onSuccess { _, data ->
                    data?.let {
                        _topKeywords.update {
                            UiState.Success(data.data)
                        }
                    }
                }.onError { _, _ ->

                }.onException { _ ->

                }.finally { _ ->
                    LogUtil.printNetworkLog("", networkResult, "GetPopularSearchListUseCase")
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
            }.onError { _, _ ->

            }.onException { _ ->

            }.finally {
                LogUtil.printNetworkLog("", networkResult, "GetNatureSearchResultListUseCase")
            }
        }
        .catch { LogUtil.printLog("flow Error", "GetNatureSearchResultListUseCase") }
        .launchIn(viewModelScope)
    }
}