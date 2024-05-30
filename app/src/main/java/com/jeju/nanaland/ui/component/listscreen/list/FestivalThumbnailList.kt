package com.jeju.nanaland.ui.component.listscreen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.festival.FestivalThumbnailData
import com.jeju.nanaland.ui.component.thumbnail.FestivalThumbnail
import com.jeju.nanaland.util.ui.UiState

@Composable
fun FestivalThumbnailList(
    listState: LazyGridState,
    thumbnailList: UiState<List<FestivalThumbnailData>>,
    toggleFavorite: (Long) -> Unit,
    moveToFestivalContentScreen: (Long) -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    when (thumbnailList) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            LazyVerticalGrid(
                state = listState,
                contentPadding = PaddingValues(start = 16.dp, end = 16.dp),
                columns = GridCells.Fixed(2)
            ) {
                itemsIndexed(thumbnailList.data) { idx, item ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        contentAlignment = if (idx % 2 == 0) Alignment.CenterStart else Alignment.CenterEnd
                    ) {
                        FestivalThumbnail(
                            imageUri = item.thumbnailUrl,
                            isFavorite = item.favorite,
                            title = item.title,
                            subTitle = item.period,
                            tag = item.addressTag,
                            onFavoriteButtonClick = { toggleFavorite(item.id) },
                            onClick = { moveToFestivalContentScreen(item.id) },
                            moveToSignInScreen = moveToSignInScreen,
                        )
                    }
                }
            }
        }
        is UiState.Failure -> {}
    }
}