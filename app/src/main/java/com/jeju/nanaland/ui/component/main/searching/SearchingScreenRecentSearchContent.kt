package com.jeju.nanaland.ui.component.main.searching

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.globalvalue.type.HomeScreenViewType
import com.jeju.nanaland.ui.component.main.searching.parts.SearchingScreenDeleteAllRecentSearchText
import com.jeju.nanaland.ui.component.main.searching.parts.SearchingScreenRecentSearchItem
import com.jeju.nanaland.ui.component.main.searching.parts.SearchingScreenRecentSearchText
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SearchingScreenRecentSearchContent(
    recentSearchList: List<Pair<String, String>>,
    deleteAllRecentSearches: () -> Unit,
    deleteRecentSearch: (String) -> Unit,
    searchKeyword: (String) -> Unit,
    updateViewType: (HomeScreenViewType) -> Unit,
    updateInputText: (String) -> Unit,
    addRecentSearch: (String) -> Unit,
) {
    Column {
        Row{
            SearchingScreenRecentSearchText()

            Spacer(Modifier.weight(1f))

            SearchingScreenDeleteAllRecentSearchText(
                onClick = deleteAllRecentSearches
            )
        }

        if (recentSearchList.isEmpty()) {
            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier.height(26.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Text(
                    text = "최근 검색어가 없습니다.",
                    color = getColor().gray01,
                    style = body02
                )
            }
        } else {
            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                recentSearchList.forEachIndexed { idx, item ->
                    SearchingScreenRecentSearchItem(
                        text = item.first,
                        onCloseClick = { deleteRecentSearch(item.first) },
                        onClick = {
                            searchKeyword(item.first)
                            updateInputText(item.first)
                            addRecentSearch(item.first)
                            updateViewType(HomeScreenViewType.SearchResult)
                        }
                    )

                    if (idx != recentSearchList.size - 1) {
                        Spacer(Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}