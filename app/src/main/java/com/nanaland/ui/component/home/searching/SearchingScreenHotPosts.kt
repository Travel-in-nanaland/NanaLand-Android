package com.nanaland.ui.component.home.searching

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.domain.entity.search.HotPostThumbnailData
import com.nanaland.ui.component.thumbnail.SearchThumbnail
import com.nanaland.util.ui.UiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchingScreenHotPosts(
    hotPosts: UiState<List<HotPostThumbnailData>>,
    onLikeButtonClick: (Long, String?) -> Unit,
    onClick: (Long) -> Unit,
) {
    when (hotPosts) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            FlowRow {
                repeat(hotPosts.data.size) { idx ->
                    val item = hotPosts.data[idx]
                    SearchThumbnail(
                        imageUri = item.thumbnailUrl,
                        isLiked = item.favorite,
                        title = item.title,
                        onLikeButtonClick = { onLikeButtonClick(item.id, item.category) },
                        onClick = {}
                    )

                    if (idx % 2 == 0) Spacer(Modifier.width(8.dp))
                }
            }
        }
        is UiState.Failure -> {}
    }
}