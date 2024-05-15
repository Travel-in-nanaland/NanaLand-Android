package com.jeju.nanaland.ui.main.home.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.ui.component.main.searchresult.SearchResultScreenCategorySelectionTab
import com.jeju.nanaland.ui.component.main.searchresult.SearchResultScreenSearchResult
import com.jeju.nanaland.ui.main.home.HomeViewModel
import com.jeju.nanaland.util.ui.UiState

@Composable
fun SearchResultContent(
    moveToCategoryContentScreen: (Long, String?, Boolean) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val inputText = homeViewModel.inputText.collectAsState().value
    val selectedCategory = searchViewModel.selectedCategory.collectAsState().value
    val allSearchResultList = searchViewModel.allSearchResultList.collectAsState().value
    val categorizedSearchResultList = searchViewModel.categorizedSearchResultList.collectAsState().value
    SearchResultContent(
        inputText = inputText,
        getSearchResult = searchViewModel::getSearchResult,
        selectedCategory = selectedCategory,
        allSearchResultList = allSearchResultList,
        categorizedSearchResultList = categorizedSearchResultList,
        updateSelectedCategory = searchViewModel::updateSelectedCategoryType,
        toggleAllSearchResultFavorite = searchViewModel::toggleAllSearchResultFavorite,
        toggleSearchResultFavorite = searchViewModel::toggleSearchResultFavorite,
        onPostClick = moveToCategoryContentScreen,
        isContent = true
    )
}

@Composable
private fun SearchResultContent(
    inputText: String,
    getSearchResult: (String) -> Unit,
    selectedCategory: SearchCategoryType,
    allSearchResultList: UiState<Map<String, SearchResultData>>,
    categorizedSearchResultList: UiState<SearchResultData>,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
    toggleAllSearchResultFavorite: (Long, String?) -> Unit,
    toggleSearchResultFavorite: (Long, String?) -> Unit,
    onPostClick: (Long, String?, Boolean) -> Unit,
    isContent: Boolean
) {
    SearchResultScreenCategorySelectionTab(
        getSearchResult = { getSearchResult(inputText) },
        selectedCategory = selectedCategory,
        updateSelectedCategory = updateSelectedCategory
    )

    Spacer(Modifier.height(24.dp))

    SearchResultScreenSearchResult(
        selectedCategory = selectedCategory,
        allSearchResultList = allSearchResultList,
        categorizedSearchResultList = categorizedSearchResultList,
        getSearchResult = { getSearchResult(inputText) },
        updateSearchCategoryType = updateSelectedCategory,
        toggleAllSearchResultFavorite = toggleAllSearchResultFavorite,
        toggleSearchResultFavorite = toggleSearchResultFavorite,
        onPostClick = onPostClick
    )
}





@Preview
@Composable
private fun SearchResultContentPreview() {

}