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
import com.jeju.nanaland.domain.entity.experience.ExperienceThumbnailData
import com.jeju.nanaland.ui.component.thumbnail.ExperienceThumbnail
import com.jeju.nanaland.util.ui.UiState

@Composable
fun ExperienceThumbnailList(
    experienceCategory: String,
    listState: LazyGridState,
    thumbnailList: UiState<List<ExperienceThumbnailData>>,
    toggleFavorite: (Int) -> Unit,
    moveToExperienceContentScreen: (Int, String) -> Unit,
    moveToSignInScreen: () -> Unit
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
                        ExperienceThumbnail(
                            imageUri = item.firstImage?.originUrl,
                            isFavorite = item.favorite,
                            title = item.title,
                            place = item.addressTag,
                            rating = item.ratingAvg.toString(),
                            onFavoriteButtonClick = { toggleFavorite(item.id) },
                            onClick = { moveToExperienceContentScreen(item.id, experienceCategory) },
                            moveToSignInScreen = moveToSignInScreen,
                        )
                    }
                }
            }
        }
        is UiState.Failure -> {}
    }
}