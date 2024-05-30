package com.jeju.nanaland.ui.component.main.searching

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.globalvalue.type.HomeScreenViewType
import com.jeju.nanaland.ui.component.main.searching.parts.SearchingScreenTopKeyword
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.UiState

@Composable
fun SearchingScreenTopKeywords(
    topKeywordList: UiState<List<String>>,
    searchKeyword: (String) -> Unit,
    updateViewType: (HomeScreenViewType) -> Unit,
    updateInputText: (String) -> Unit,
    addRecentSearch: (String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    when (topKeywordList) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            Row {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    repeat(4) {
                        Row(
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                modifier = Modifier.width(12.dp),
                                text = "${it + 1}.",
                                color = if (it == 0 || it == 1) getColor().main else getColor().gray01,
                                style = if (it == 0 || it == 1) body02SemiBold else body02
                            )

                            Spacer(Modifier.width(8.dp))

                            SearchingScreenTopKeyword(
                                text = if (it >= topKeywordList.data.size) "" else topKeywordList.data[it],
                                rank = it,
                                onClick = {
                                    focusManager.clearFocus()
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
                        Row(
                            modifier = Modifier.height(40.dp)
                        ) {
                            Text(
                                modifier = Modifier.width(12.dp),
                                text = "${it + 5}.",
                                color = getColor().gray01,
                                style = body02
                            )

                            Spacer(Modifier.width(8.dp))

                            SearchingScreenTopKeyword(
                                text = if (it + 4 >= topKeywordList.data.size) "" else topKeywordList.data[it + 4],
                                rank = it + 4,
                                onClick = {
                                    focusManager.clearFocus()
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