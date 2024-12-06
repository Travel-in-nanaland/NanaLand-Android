package com.jeju.nanaland.ui.searchInContent

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.domain.entity.search.SearchResultThumbnailData
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.domain.request.favorite.ToggleFavoriteRequest
import com.jeju.nanaland.domain.request.search.GetSearchResultListRequest
import com.jeju.nanaland.domain.usecase.favorite.ToggleFavoriteUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.AddRecentSearchUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.ClearRecentSearchDataStoreUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.DeleteRecentSearchUseCase
import com.jeju.nanaland.domain.usecase.recentsearchdatastore.GetAllRecentSearchUseCase
import com.jeju.nanaland.domain.usecase.search.GetExperienceSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetFestivalSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetMarketSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetNatureSearchResultListUseCase
import com.jeju.nanaland.domain.usecase.search.GetRestaurantSearchResultListUseCase
import com.jeju.nanaland.globalvalue.constant.PAGING_SIZE
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.network.onError
import com.jeju.nanaland.util.network.onException
import com.jeju.nanaland.util.network.onSuccess
import com.jeju.nanaland.util.ui.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchInContentViewModel @Inject constructor(
    private val getNatureSearchResultListUseCase: GetNatureSearchResultListUseCase,
    private val getMarketSearchResultListUseCase: GetMarketSearchResultListUseCase,
    private val getFestivalSearchResultListUseCase: GetFestivalSearchResultListUseCase,
    private val getExperienceSearchResultListUseCase: GetExperienceSearchResultListUseCase,
    private val getRestaurantSearchResultListUseCase: GetRestaurantSearchResultListUseCase,
    private val getAllRecentSearchUseCase: GetAllRecentSearchUseCase,
    private val addRecentSearchUseCase: AddRecentSearchUseCase,
    private val deleteRecentSearchUseCase: DeleteRecentSearchUseCase,
    private val clearRecentSearchDataStoreUseCase: ClearRecentSearchDataStoreUseCase,
    private val toggleFavoriteUseCase: ToggleFavoriteUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    val category = (savedStateHandle.toRoute() as ROUTE.Content.SearchInContent).routeString

    private val _inputText = MutableStateFlow("")
    val inputText = _inputText.asStateFlow()

    private val _categorizedSearchResultList = MutableStateFlow<UiState<SearchResultData>>(UiState.Loading)
    val categorizedSearchResultList = _categorizedSearchResultList.asStateFlow()
    private var page = 0

    @OptIn(ExperimentalCoroutinesApi::class)
    val recentSearchList = getAllRecentSearchUseCase.inContent(category)
        .flatMapLatest {
            val list = it
            inputText.map { m ->
                    m.split(" ").filter { it.isNotBlank() }
            }.mapLatest { ml ->
                if(ml.isEmpty()) return@mapLatest list

                list.filter { f ->
                    ml.any { f.contains(it) }
                }
                .map { m ->
                    ml.fold(m) { acc, s ->
                        acc.replace(s, "{$s}")
                    }
                }
                .sortedWith(compareBy(
                    { cb ->
                        cb.count { c -> c == '{' } * -1
                    }, { cb ->
                        cb.length
                    }
                ))

            }
        }.stateIn(
            viewModelScope,SharingStarted.Eagerly, emptyList()
        )


    fun getSearchCategoryType() = when(category) {
        ROUTE.Content.Experience(true).toString() -> SearchCategoryType.Experience
        ROUTE.Content.Experience(false).toString() -> SearchCategoryType.Experience
        ROUTE.Content.Festival.toString() -> SearchCategoryType.Festival
        ROUTE.Content.Nature.toString() -> SearchCategoryType.Nature
        ROUTE.Content.Market.toString() -> SearchCategoryType.Market
        ROUTE.Content.Restaurant.toString() -> SearchCategoryType.Restaurant
        else -> throw Exception("Invalid route")
    }

    fun updateInputText(text: String) {
        _inputText.update { text }
    }
    fun onSearch(text: String) {
        viewModelScope.launch {
            addRecentSearchUseCase.inContent(category, text)
            page = 0
            getSearchResult(text)
        }
    }
    fun removeRecentSearch(text: String) {
        viewModelScope.launch {
            deleteRecentSearchUseCase.inContent(category, text.replace("{","").replace("}",""))
        }
    }
    fun removeRecentSearchAll() {
        viewModelScope.launch {
            clearRecentSearchDataStoreUseCase.inContent(category)
        }
    }
    fun selectRecentSearch(text: String) {
        updateInputText(text)
        onSearch(text)
    }


    fun getSearchResult(keyword: String) {
        var prevList: List<SearchResultThumbnailData>? = null
        if (_categorizedSearchResultList.value is UiState.Success) {
            page++
            prevList = (_categorizedSearchResultList.value as UiState.Success).data.data
        }
        val requestData = GetSearchResultListRequest(
            keyword = keyword,
            page = page,
            size = PAGING_SIZE
        )

        when (category) {
            ROUTE.Content.Experience(true).toString() -> getExperienceSearchResultListUseCase(requestData) // TODO
            ROUTE.Content.Experience(false).toString() -> getExperienceSearchResultListUseCase(requestData) // TODO
            ROUTE.Content.Festival.toString() -> getFestivalSearchResultListUseCase(requestData)
            ROUTE.Content.Nature.toString() -> getNatureSearchResultListUseCase(requestData)
            ROUTE.Content.Market.toString() -> getMarketSearchResultListUseCase(requestData)
            ROUTE.Content.Restaurant.toString() -> getRestaurantSearchResultListUseCase(requestData)
            else -> throw Exception("Invalid route")
        }.onEach { networkResult ->
            networkResult.onSuccess { code, message, data ->
                data?.let {
                    _categorizedSearchResultList.update {
                        if (prevList.isNullOrEmpty()) {
                            UiState.Success(data)
                        } else {
                            UiState.Success(data.copy(data = prevList + data.data))
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
}