package com.nanaland.ui.main.home.search

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.domain.entity.search.SearchResultData
import com.nanaland.globalvalue.type.SearchCategoryType
import com.nanaland.ui.component.main.searchresult.SearchResultScreenCategoryListTab
import com.nanaland.ui.component.main.searchresult.SearchResultScreenSearchResult
import com.nanaland.ui.main.home.HomeViewModel
import com.nanaland.util.ui.UiState

@Composable
fun SearchResultContent(
    scaffoldPadding: PaddingValues,
    moveToCategoryContentScreen: (Long, String?) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val inputText = homeViewModel.inputText.collectAsState().value
    val selectedCategory = searchViewModel.selectedCategory.collectAsState().value
    val allSearchResultList = searchViewModel.allSearchResultList.collectAsState().value
    val categorizedSearchResultList = searchViewModel.categorizedSearchResultList.collectAsState().value
    SearchResultContent(
        scaffoldPadding = scaffoldPadding,
        inputText = inputText,
        getSearchResult = searchViewModel::getSearchResult,
        selectedCategory = selectedCategory,
        allSearchResultList = allSearchResultList,
        categorizedSearchResultList = categorizedSearchResultList,
        updateSelectedCategory = searchViewModel::updateSelectedCategory,
        toggleAllSearchResultFavorite = searchViewModel::toggleAllSearchResultFavorite,
        toggleSearchResultFavorite = searchViewModel::toggleSearchResultFavorite,
        onPostClick = moveToCategoryContentScreen,
        isContent = true
    )
}

@Composable
private fun SearchResultContent(
    scaffoldPadding: PaddingValues,
    inputText: String,
    getSearchResult: (String) -> Unit,
    selectedCategory: SearchCategoryType,
    allSearchResultList: UiState<Map<String, SearchResultData>>,
    categorizedSearchResultList: UiState<SearchResultData>,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
    toggleAllSearchResultFavorite: (Long, String?) -> Unit,
    toggleSearchResultFavorite: (Long, String?) -> Unit,
    onPostClick: (Long, String?) -> Unit,
    isContent: Boolean
) {
    SearchResultScreenCategoryListTab(
        getSearchResult = { getSearchResult(inputText) },
        selectedCategory = selectedCategory,
        updateSelectedCategory = updateSelectedCategory
    )

    Spacer(Modifier.height(24.dp))

    SearchResultScreenSearchResult(
        scaffoldPadding = scaffoldPadding,
        selectedCategory = selectedCategory,
        allSearchResultList = allSearchResultList,
        categorizedSearchResultList = categorizedSearchResultList,
        getSearchResult = { getSearchResult(inputText) },
        updateSelectedCategory = updateSelectedCategory,
        toggleAllSearchResultFavorite = toggleAllSearchResultFavorite,
        toggleSearchResultFavorite = toggleSearchResultFavorite,
        onPostClick = onPostClick
    )

    Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
}





@Preview
@Composable
private fun SearchResultContentPreview() {

}