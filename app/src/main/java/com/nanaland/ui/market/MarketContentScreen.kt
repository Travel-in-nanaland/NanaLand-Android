package com.nanaland.ui.market

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.R
import com.nanaland.domain.entity.market.MarketContentData
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBarWithShadow
import com.nanaland.ui.component.detailscreen.DetailScreenDescription
import com.nanaland.ui.component.detailscreen.DetailScreenInformation
import com.nanaland.ui.component.detailscreen.DetailScreenTopBannerImage
import com.nanaland.ui.component.detailscreen.MoveToTopButton
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun MarketContentScreen(
    contentId: Long?,
    marketListViewModel: MarketListViewModel,
    moveToBackScreen: () -> Unit,
    viewModel: MarketContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMarketContent(contentId)
    }
    val marketContent = viewModel.marketContent.collectAsState().value
    MarketContentScreen(
        marketContent = marketContent,
        toggleFavorite = viewModel::toggleFavorite,
        toggleListFavorite = marketListViewModel::toggleFavoriteWithNoApi,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun MarketContentScreen(
    marketContent: UiState<MarketContentData>,
    toggleFavorite: (Long, (Long, Boolean) -> Unit) -> Unit,
    toggleListFavorite: (Long, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        when (marketContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column {
                        CustomTopBarWithShadow(
                            title = "전통시장",
                            onBackButtonClicked = moveToBackScreen
                        )

                        Column(modifier = Modifier.verticalScroll(scrollState)) {
                            DetailScreenTopBannerImage(imageUri = marketContent.data.originUrl)

                            Spacer(Modifier.height(24.dp))

                            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                                DetailScreenDescription(
                                    isLiked = marketContent.data.favorite,
                                    tag = marketContent.data.addressTag,
                                    title = marketContent.data.title,
                                    content = marketContent.data.content,
                                    onLikeButtonClicked = {
                                        toggleFavorite(marketContent.data.id, toggleListFavorite)
                                    },
                                    onShareButtonClicked = {}
                                )

                                Spacer(Modifier.height(24.dp))

                                if (!marketContent.data.address.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_location_outlined,
                                        title = "주소",
                                        content = marketContent.data.address
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!marketContent.data.contact.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_phone_outlined,
                                        title = "연락처",
                                        content = marketContent.data.contact
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!marketContent.data.time.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_clock_outlined,
                                        title = "이용 시간",
                                        content = marketContent.data.time
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!marketContent.data.amenity.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_amenity_outlined,
                                        title = "편의시설",
                                        content = marketContent.data.amenity
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }

                                if (!marketContent.data.homepage.isNullOrEmpty()) {
                                    DetailScreenInformation(
                                        drawableId = R.drawable.ic_clip_outlined,
                                        title = "홈페이지",
                                        content = marketContent.data.homepage
                                    )

                                    Spacer(Modifier.height(16.dp))
                                }
                            }

                            Spacer(Modifier.height(80.dp))
                        }
                    }

                    MoveToTopButton {
                        coroutineScope.launch { scrollState.animateScrollTo(0) }
                    }
                }
            }
            is UiState.Failure -> {}
        }
    }
}

@ScreenPreview
@Composable
private fun MarketContentScreenPreview() {

}