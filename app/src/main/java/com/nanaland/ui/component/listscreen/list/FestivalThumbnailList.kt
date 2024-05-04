package com.nanaland.ui.component.listscreen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.domain.entity.festival.FestivalThumbnailData
import com.nanaland.ui.component.thumbnail.FestivalThumbnail
import com.nanaland.util.ui.UiState

@Composable
fun FestivalThumbnailList(
    listState: LazyGridState,
    thumbnailList: UiState<List<FestivalThumbnailData>>,
    toggleFavorite: (Long) -> Unit,
    moveToFestivalContentScreen: (Long) -> Unit
) {
    when (thumbnailList) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            LazyVerticalGrid(
                state = listState,
                contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(thumbnailList.data) { idx, item ->
                    Box(
                        modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 16.dp)
                    ) {
                        FestivalThumbnail(
                            imageUri = item.thumbnailUrl,
                            isLiked = item.favorite,
                            title = item.title,
                            subTitle = item.period,
                            tag = item.addressTag,
                            onLikeButtonClick = { toggleFavorite(item.id) },
                            onClick = { moveToFestivalContentScreen(item.id) }
                        )
                    }
                }
            }
        }
        is UiState.Failure -> {}
    }
}