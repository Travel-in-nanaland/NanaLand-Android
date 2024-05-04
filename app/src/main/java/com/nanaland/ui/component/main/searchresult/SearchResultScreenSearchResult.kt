package com.nanaland.ui.component.main.searchresult

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.domain.entity.search.SearchResultData
import com.nanaland.globalvalue.type.SearchCategoryType
import com.nanaland.ui.component.main.searchresult.parts.SearchResultScreenItemCount
import com.nanaland.ui.component.main.searchresult.parts.SearchResultScreenPreviewByCategory
import com.nanaland.util.ui.UiState

@Composable
fun SearchResultScreenSearchResult(
    scaffoldPadding: PaddingValues,
    selectedCategory: SearchCategoryType,
    allSearchResultList: UiState<Map<String, SearchResultData>>,
    categorizedSearchResultList: UiState<SearchResultData>,
    getSearchResult: () -> Unit,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
    toggleAllSearchResultFavorite: (Long, String?) -> Unit,
    toggleSearchResultFavorite: (Long, String?) -> Unit,
    onPostClick: (Long, String?) -> Unit,
) {
    if (selectedCategory == SearchCategoryType.All) {
        when (allSearchResultList) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Column(
                    modifier = Modifier
                        .imePadding()
                        .verticalScroll(rememberScrollState())
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    SearchCategoryType.entries.forEach {
                        if (it != SearchCategoryType.All) {
                            SearchResultScreenPreviewByCategory(
                                category = it,
                                allSearchResultList = allSearchResultList.data,
                                getSearchResult = getSearchResult,
                                updateSelectedCategory = updateSelectedCategory,
                                onLikeButtonClick = toggleAllSearchResultFavorite,
                                onPostClick = onPostClick
                            )

                            Spacer(Modifier.height(40.dp))
                        }
                    }

                    Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
                }
            }
            is UiState.Failure -> {}
        }
    } else {
        when (categorizedSearchResultList) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                SearchResultScreenItemCount(
                    modifier = Modifier.padding(start = 16.dp),
                    count = categorizedSearchResultList.data.count
                )

                Spacer(Modifier.height(16.dp))
                
                SearchResultScreenSearchResultList(
                    selectedCategory = selectedCategory,
                    scaffoldPadding = scaffoldPadding,
                    categorizedSearchResultList = categorizedSearchResultList.data,
                    onLikeButtonClick = toggleSearchResultFavorite,
                    onPostClick = onPostClick
                )
            }
            is UiState.Failure -> {}
        }
    }
}