package com.nanaland.ui.component.main.searchresult

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.domain.entity.search.SearchResultData
import com.nanaland.globalvalue.type.SearchCategoryType
import com.nanaland.ui.component.thumbnail.SearchThumbnail

@Composable
fun SearchResultScreenSearchResultList(
    selectedCategory: SearchCategoryType,
    scaffoldPadding: PaddingValues,
    categorizedSearchResultList: SearchResultData,
    onLikeButtonClick: (Long, String?) -> Unit,
    onPostClick: (Long, String?) -> Unit,
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
        columns = GridCells.Fixed(2)
    ) {
        itemsIndexed(categorizedSearchResultList.data) { idx, item ->
            Box(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 16.dp)
            ) {
                SearchThumbnail(
                    imageUri = item.thumbnailUrl,
                    isLiked = item.favorite,
                    title = item.title,
                    onLikeButtonClick = {
                        onLikeButtonClick(item.id, when (selectedCategory) {
                            SearchCategoryType.Nature -> "NATURE"
                            SearchCategoryType.Festival -> "FESTIVAL"
                            SearchCategoryType.Market -> "MARKET"
                            SearchCategoryType.Experience -> "EXPERIENCE"
                            else -> ""
                        })
                                        },
                    onClick = {
                        onPostClick(item.id, when (selectedCategory) {
                            SearchCategoryType.Nature -> "NATURE"
                            SearchCategoryType.Festival -> "FESTIVAL"
                            SearchCategoryType.Market -> "MARKET"
                            SearchCategoryType.Experience -> "EXPERIENCE"
                            else -> ""
                        })
                    }
                )
            }
        }
        item {
            Spacer(Modifier.height(scaffoldPadding.calculateBottomPadding()))
        }
    }
}