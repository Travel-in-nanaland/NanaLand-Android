package com.nanaland.ui.component.main.searchresult.parts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.domain.entity.search.SearchResultData
import com.nanaland.globalvalue.type.SearchCategoryType
import com.nanaland.ui.component.thumbnail.SearchThumbnail
import com.nanaland.util.resource.getString
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchResultScreenPreviewByCategory(
    category: SearchCategoryType,
    allSearchResultList: Map<String, SearchResultData>,
    getSearchResult: () -> Unit,
    updateSelectedCategory: (SearchCategoryType) -> Unit,
    onLikeButtonClick: (Long, String?) -> Unit,
    onPostClick: (Long, String?) -> Unit,
) {
    Row(
        modifier = Modifier
            .clickableNoEffect {
                getSearchResult()
                updateSelectedCategory(category)
            },
        verticalAlignment = Alignment.Bottom
    ) {
        SearchResultScreenPreviewByCategoryTitle(text = getString(category.titleResId))

        Spacer(Modifier.width(10.dp))

        SearchResultScreenItemCount(count = allSearchResultList[category.name]?.count ?: 0)

        Spacer(Modifier.weight(1f))

        Image(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = null
        )
    }

    Spacer(Modifier.height(10.dp))

    SearchResultScreenPreviewByCategoryHorizontalDivider()

    Spacer(Modifier.height(10.dp))

    if ((allSearchResultList[category.name]?.data?.size ?: 0) == 0) {
        SearchResultScreenEmptySearchResultContent()
    } else {
        FlowRow {
            allSearchResultList[category.name]?.data?.let { searchResult ->
                searchResult.forEachIndexed { idx, item ->
                    SearchThumbnail(
                        imageUri = item.thumbnailUrl,
                        isLiked = item.favorite,
                        title = item.title,
                        onLikeButtonClick = {
                            onLikeButtonClick(item.id, when (category) {
                                SearchCategoryType.Nature -> "NATURE"
                                SearchCategoryType.Festival -> "FESTIVAL"
                                SearchCategoryType.Market -> "MARKET"
                                SearchCategoryType.Experience -> "EXPERIENCE"
                                else -> ""
                            })
                        },
                        onClick = {
                            onPostClick(item.id, when (category) {
                                SearchCategoryType.Nature -> "NATURE"
                                SearchCategoryType.Festival -> "FESTIVAL"
                                SearchCategoryType.Market -> "MARKET"
                                SearchCategoryType.Experience -> "EXPERIENCE"
                                else -> ""
                            })
                        }
                    )

                    if (idx % 2 == 0) {
                        Spacer(Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}

@ComponentPreview
@Composable
private fun SearchResultScreenPreviewPreview() {

}