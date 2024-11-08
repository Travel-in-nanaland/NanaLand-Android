package com.jeju.nanaland.ui.component.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.domain.entity.member.RecommendedPostData
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailFavoriteButton
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreenPopularPlaces(
    recommendedPosts: UiState<List<RecommendedPostData>>, // TODO
    onFavoriteButtonClick: (Int, String?) -> Unit,
    onClick: (Int, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (recommendedPosts) {
            is UiState.Loading -> {
                for (i in 1..5) {
                    RoundedView {
                        Spacer(
                            Modifier
                                .fillMaxSize()
                                .background(getColor().skeleton))
                    }
                }
            }
            is UiState.Failure -> {}
            is UiState.Success -> {
                for (i in 1..5) //TODO 양 늘리기 삭제
                repeat(recommendedPosts.data.size) { idx ->
                    val item = recommendedPosts.data[idx]
                    RoundedView {
                        PlaceView(
                            imageUri = item.firstImage.originUrl,
                            isFavorite = item.favorite,
                            title = item.title,
                            subTitle = "todo sub title - " +item.title, // TODO
                            onFavoriteButtonClick = { onFavoriteButtonClick(item.id, item.category) },
                            onClick = { onClick(item.id, item.category, false) },
                            moveToSignInScreen = moveToSignInScreen
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RoundedView(
    view: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clip(RoundedCornerShape(12.dp))
    ) {
        view()
    }
}

@Composable
private fun PlaceView(
    imageUri: String,
    isFavorite: Boolean,
    title: String,
    subTitle: String,
    onFavoriteButtonClick: () -> Unit,
    onClick: () -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize().clickableNoEffect(onClick)
    ) {
        GlideImage(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(getColor().skeleton),
            imageModel = { imageUri }
        )


        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(8.dp)
        ) {
            ThumbnailFavoriteButton(
                isFavorite = isFavorite,
                onClick = onFavoriteButtonClick,
                moveToSignInScreen = moveToSignInScreen
            )
        }
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = title,
                color = getColor().white,
                style = largeTitle02,
            )
            Spacer(Modifier.height(1.dp))
            Text(
                text = subTitle,
                color = getColor().white,
                style = caption01,
            )
        }

    }
}