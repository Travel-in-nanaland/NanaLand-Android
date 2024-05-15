package com.jeju.nanaland.ui.component.main.searching

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.search.HotPostThumbnailData
import com.jeju.nanaland.ui.component.thumbnail.SearchThumbnail
import com.jeju.nanaland.util.ui.UiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchingScreenHotPosts(
    hotPosts: UiState<List<HotPostThumbnailData>>,
    onFavoriteButtonClick: (Long, String?) -> Unit,
    onPostClick: (Long, String?, Boolean) -> Unit,
) {
    when (hotPosts) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            FlowRow {
                repeat(hotPosts.data.size) { idx ->
                    val item = hotPosts.data[idx]
                    Column {
                        SearchThumbnail(
                            imageUri = item.thumbnailUrl,
                            isFavorite = item.favorite,
                            title = item.title,
                            onFavoriteButtonClick = { onFavoriteButtonClick(item.id, item.category) },
                            onClick = { onPostClick(item.id, item.category, false) }
                        )

                        Spacer(Modifier.height(16.dp))
                    }

                    if (idx % 2 == 0) Spacer(Modifier.width(8.dp))
                }
            }
        }
        is UiState.Failure -> {}
    }
}