package com.jeju.nanaland.ui.component.listscreen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.market.MarketThumbnail
import com.jeju.nanaland.globalvalue.constant.TOP_BAR_HEIGHT
import com.jeju.nanaland.ui.component.thumbnail.MarketThumbnail
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun MarketThumbnailList(
    listState: LazyGridState,
    thumbnailList: UiState<List<MarketThumbnail>>,
    toggleFavorite: (Int) -> Unit,
    moveToMarketContentScreen: (Int) -> Unit,
    moveToSignInScreen: () -> Unit,
    filterReset: () -> Unit,
) {
    when (thumbnailList) {
        is UiState.Loading -> {}
        is UiState.Success -> {
            if(thumbnailList.data.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = getString(R.string.list_screen_common_empty_list_by_filter_head),
                        color = getColor().gray01,
                        style = body01
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = getString(R.string.list_screen_common_empty_list_by_filter_sub),
                        color = Color(0xFFC4C4C4),
                        style = body02
                    )
                    Spacer(Modifier.height(20.dp))
                    Text(
                        modifier = Modifier
                            .clickableNoEffect(filterReset)
                            .background(getColor().gray02, shape = RoundedCornerShape(50))
                            .padding(horizontal = 27.dp, vertical = 9.dp),
                        text = getString(R.string.list_screen_common_reset_filter),
                        color = getColor().gray01,
                        style = body02SemiBold
                    )

                }
            } else
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
                        MarketThumbnail(
                            imageUri = item.firstImage?.originUrl,
                            isFavorite = item.favorite,
                            title = item.title,
                            tag = item.addressTag,
                            onFavoriteButtonClick = { toggleFavorite(item.id) },
                            onClick = { moveToMarketContentScreen(item.id) },
                            moveToSignInScreen = moveToSignInScreen,
                        )
                    }
                }
                items(2) {
                    Spacer(modifier = Modifier.fillMaxWidth().height(TOP_BAR_HEIGHT.dp))
                }
            }
        }
        is UiState.Failure -> {}
    }
}