package com.nanaland.ui.component.home.searching

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.home.searching.parts.SearchingScreenTopKeyword
import com.nanaland.util.ui.UiState

@Composable
fun SearchingScreenTopKeywords(
    topKeywords: UiState<List<String>>,
) {
    Row {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            repeat(4) {
                Box(
                    modifier = Modifier.height(40.dp)
                ) {
                    when (topKeywords) {
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            SearchingScreenTopKeyword(
                                text = topKeywords.data[it],
                                rank = it
                            )
                        }
                        is UiState.Failure -> {}
                    }
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
                    when (topKeywords) {
                        is UiState.Loading -> {}
                        is UiState.Success -> {
                            SearchingScreenTopKeyword(
                                text = topKeywords.data[it + 4],
                                rank = it + 4
                            )
                        }
                        is UiState.Failure -> {}
                    }
                }
            }
        }
    }
}