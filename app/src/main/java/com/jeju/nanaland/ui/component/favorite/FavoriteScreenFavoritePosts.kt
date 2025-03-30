package com.jeju.nanaland.ui.component.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.favorite.FavoriteThumbnailData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailFavoriteButton
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun FavoriteScreenFavoritePosts(
    favoriteThumbnailList: List<FavoriteThumbnailData>,
    getFavoriteList: () -> Unit,
    onFavoriteButtonClick: (Int, String?) -> Unit,
    onPostClick: (Int, String?, Boolean) -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    val lazyGridState = rememberLazyGridState()
    val loadMore = remember {
        derivedStateOf {
            val layoutInfo = lazyGridState.layoutInfo
            val totalItemsNumber = layoutInfo.totalItemsCount
            val lastVisibleItemIndex = (layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0) + 1
            lastVisibleItemIndex > (totalItemsNumber - PAGING_THRESHOLD)
        }
    }

    LaunchedEffect(loadMore.value) {
        if (loadMore.value) {
            getFavoriteList()
        }
    }

    LazyVerticalGrid(
        state = lazyGridState,
        contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
        columns = GridCells.Fixed(2),
    ) {
        item {
            Spacer(Modifier.height(24.dp))
        }
        item {
            Spacer(Modifier.height(24.dp))
        }
        itemsIndexed(favoriteThumbnailList) { idx, item ->
            Box(
                modifier = Modifier.padding(start = 4.dp, end = 4.dp, bottom = 16.dp)
            ) {
                Post(
                    item = item,
                    onFavoriteButtonClick = {
                        onFavoriteButtonClick(
                            item.id,
                            if(item.category.uppercase() in listOf("ACTIVITY", "CULTURE_AND_ARTS")) "EXPERIENCE"
                            else item.category
                        )
                    },
                    onClick = {
                        onPostClick(item.id, item.category, false)
                    },
                    moveToSignInScreen = moveToSignInScreen,
                )
            }
        }
    }
}

@Composable
private fun Post(
    item: FavoriteThumbnailData,
    onFavoriteButtonClick: () -> Unit,
    onClick: () -> Unit,
    moveToSignInScreen: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickableNoEffect { onClick() }
    ) {
        Box(
            modifier = Modifier
                .height(120.dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(getColor().skeleton),
                imageModel = { item.firstImage?.originUrl }
            )

            if(item.isNotOver == false)
                Box(
                    Modifier.fillMaxSize().background(getColor().black50)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.Center),
                        text = getString(R.string.festival_list_screen_close_thumbnail),
                        color = getColor().white,
                        style = body02SemiBold,
                        textAlign = TextAlign.Center
                    )
                }


            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
            ) {
                ThumbnailFavoriteButton(
                    isFavorite = item.favorite,
                    onClick = onFavoriteButtonClick,
                    moveToSignInScreen = moveToSignInScreen,
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = item.title,
            color = getColor().black,
            style = body02SemiBold,
            maxLines = when (getLanguage()) {
                LanguageType.Korean, LanguageType.Chinese -> 1
                LanguageType.English, LanguageType.Malaysia, LanguageType.Vietnam -> 2
            },
            overflow = TextOverflow.Ellipsis
        )
    }
}