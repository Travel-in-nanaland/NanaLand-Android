package com.jeju.nanaland.ui.component.main.searchresult

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.ui.component.main.searchresult.parts.SearchResultScreenItemCount
import com.jeju.nanaland.ui.component.main.searchresult.parts.SearchResultScreenPreviewByCategory
import com.jeju.nanaland.util.ui.UiState

@Composable
fun SearchResultScreenSearchResult(
    selectedCategory: SearchCategoryType,
    allSearchResultList: UiState<Map<String, SearchResultData>>,
    categorizedSearchResultList: UiState<SearchResultData>,
    getSearchResult: () -> Unit,
    updateSearchCategoryType: (SearchCategoryType) -> Unit,
    toggleAllSearchResultFavorite: (Long, String?) -> Unit,
    toggleSearchResultFavorite: (Long, String?) -> Unit,
    onPostClick: (Long, String?, Boolean) -> Unit,
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
                                updateSearchCategoryType = updateSearchCategoryType,
                                onFavoriteButtonClick = toggleAllSearchResultFavorite,
                                onPostClick = onPostClick
                            )

                            Spacer(Modifier.height(40.dp))
                        }
                    }
                }
            }
            is UiState.Failure -> {}
        }
    } else {
        if (selectedCategory == SearchCategoryType.NanaPick || selectedCategory == SearchCategoryType.JejuStory || selectedCategory == SearchCategoryType.Experience) {
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                SearchResultScreenPreparingServiceContent()
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
                        categorizedSearchResultList = categorizedSearchResultList.data,
                        getSearchResult = getSearchResult,
                        onFavoriteButtonClick = toggleSearchResultFavorite,
                        onPostClick = onPostClick
                    )
                }
                is UiState.Failure -> {}
            }
        }
    }
}