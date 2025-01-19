package com.jeju.nanaland.ui.component.main.home

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.ui.component.thumbnail.MainHomeThumbnail
import com.jeju.nanaland.util.ui.UiState

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
            Row (
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Spacer(Modifier.width(8.dp))
                repeat(recommendedPosts.data.size) { idx ->
                    val item = recommendedPosts.data[idx]
                    MainHomeThumbnail(
                        imageUri = item.firstImage?.originUrl,
                        isFavorite = item.favorite,
                        title = item.title,
                        onFavoriteButtonClick = { onFavoriteButtonClick(item.id, item.category) },
                        onClick = { onClick(item.id, item.category, false) },
                        moveToSignInScreen = moveToSignInScreen
                    )
                }
                Spacer(Modifier.width(8.dp))
            }
        }
        is UiState.Failure -> {}
    }
}