package com.jeju.nanaland.ui.component.main.searchresult

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.search.SearchResultData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.type.SearchCategoryType
import com.jeju.nanaland.ui.component.thumbnail.SearchThumbnail

@Composable
fun SearchResultScreenSearchResultList(
    selectedCategory: SearchCategoryType,
    categorizedSearchResultList: SearchResultData,
    getSearchResult: () -> Unit,
    onFavoriteButtonClick: (Int, String?) -> Unit,
    onPostClick: (Int, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    val lazyGridState = rememberLazyGridState()
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyGridState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleItemIndex > (totalItemsNumber - PAGING_THRESHOLD)
        }
    }

    LaunchedEffect(loadMore.value) {
        if (loadMore.value) {
            getSearchResult()
        }
    }

    LazyVerticalGrid(
        state = lazyGridState,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(categorizedSearchResultList.data) { idx, item ->
            Box(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 16.dp)
            ) {
                SearchThumbnail(
                    imageUri = item.firstImage?.thumbnailUrl,
                    isFavorite = item.favorite,
                    title = item.title,
                    onFavoriteButtonClick = {
                        onFavoriteButtonClick(item.id, when (selectedCategory) {
                            SearchCategoryType.Nature -> "NATURE"
                            SearchCategoryType.Festival -> "FESTIVAL"
                            SearchCategoryType.Market -> "MARKET"
                            SearchCategoryType.Experience -> "EXPERIENCE"
                            SearchCategoryType.Restaurant -> "RESTAURANT"
                            else -> ""
                        })
                    },
                    onClick = {
                        onPostClick(item.id, when (selectedCategory) {
                            SearchCategoryType.Nature -> "NATURE"
                            SearchCategoryType.Festival -> "FESTIVAL"
                            SearchCategoryType.Market -> "MARKET"
                            SearchCategoryType.Experience -> "EXPERIENCE"
                            SearchCategoryType.Restaurant -> "RESTAURANT"
                            else -> ""
                        }, true)
                    },
                    moveToSignInScreen = moveToSignInScreen,
                )
            }
        }
    }
}