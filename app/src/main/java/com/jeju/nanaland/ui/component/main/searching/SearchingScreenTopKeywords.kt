package com.jeju.nanaland.ui.component.main.searching

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.globalvalue.type.HomeScreenViewType
import com.jeju.nanaland.ui.component.main.searching.parts.SearchingScreenTopKeyword
import com.jeju.nanaland.util.ui.UiState

@Composable
fun SearchingScreenTopKeywords(
    topKeywordList: UiState<List<String>>,
    searchKeyword: (String) -> Unit,
    updateViewType: (HomeScreenViewType) -> Unit,
    updateInputText: (String) -> Unit,
    addRecentSearch: (String) -> Unit,
) {
    when (topKeywordList) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    repeat(4) {
                        Box(
                            modifier = Modifier.height(40.dp)
                        ) {
                            SearchingScreenTopKeyword(
                                text = if (it >= topKeywordList.data.size) "" else topKeywordList.data[it],
                                rank = it,
                                onClick = {
                                    searchKeyword(topKeywordList.data[it])
                                    updateInputText(topKeywordList.data[it])
                                    addRecentSearch(topKeywordList.data[it])
                                    updateViewType(HomeScreenViewType.SearchResult)
                                }
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    repeat(4) {
                        Box(
                            modifier = Modifier.height(40.dp)
                        ) {
                            SearchingScreenTopKeyword(
                                text = if (it + 4 >= topKeywordList.data.size) "" else topKeywordList.data[it + 4],
                                rank = it + 4,
                                onClick = {
                                    searchKeyword(topKeywordList.data[it + 4])
                                    updateInputText(topKeywordList.data[it + 4])
                                    addRecentSearch(topKeywordList.data[it + 4])
                                    updateViewType(HomeScreenViewType.SearchResult)
                                }
                            )
                        }
                    }
                }
            }
        }
        is UiState.Failure -> {}
    }
}