package com.jeju.nanaland.ui.component.main.home

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.ui.component.thumbnail.MainHomeThumbnail
import com.jeju.nanaland.util.ui.UiState

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreenRecommendedPosts(
    recommendedPosts: UiState<List<RecommendedPostData>>,
    onFavoriteButtonClick: (Int, String?) -> Unit,
    onClick: (Int, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    when (recommendedPosts) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            FlowRow {
                repeat(recommendedPosts.data.size) { idx ->
                    val item = recommendedPosts.data[idx]
                    MainHomeThumbnail(
                        imageUri = item.firstImage?.thumbnailUrl,
                        isFavorite = item.favorite,
                        title = item.title,
                        onFavoriteButtonClick = { onFavoriteButtonClick(item.id, item.category) },
                        onClick = { onClick(item.id, item.category, false) },
                        moveToSignInScreen = moveToSignInScreen
                    )

                    if (idx % 2 == 0) Spacer(Modifier.weight(1f))
                }
            }
        }
        is UiState.Failure -> {}
    }
}