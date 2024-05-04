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
import com.nanaland.domain.entity.market.MarketThumbnailData
import com.nanaland.ui.component.thumbnail.MarketThumbnail
import com.nanaland.util.ui.UiState

@Composable
fun MarketThumbnailList(
    listState: LazyGridState,
    thumbnailList: UiState<List<MarketThumbnailData>>,
    toggleFavorite: (Long) -> Unit,
    moveToMarketContentScreen: (Long) -> Unit,
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
                        MarketThumbnail(
                            imageUri = item.thumbnailUrl,
                            isLiked = item.favorite,
                            title = item.title,
                            tag = item.addressTag,
                            onLikeButtonClick = { toggleFavorite(item.id) },
                            onClick = { moveToMarketContentScreen(item.id) }
                        )
                    }
                }
            }
        }
        is UiState.Failure -> {}
    }
}