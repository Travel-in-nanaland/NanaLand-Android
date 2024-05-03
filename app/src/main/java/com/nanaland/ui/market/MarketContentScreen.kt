package com.nanaland.ui.market

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.nanaland.domain.entity.market.MarketContentData
import com.nanaland.ui.component.common.CustomSurface
import com.nanaland.ui.component.common.CustomTopBarWithShadow
import com.nanaland.ui.component.detailscreen.DetailScreenDescription
import com.nanaland.ui.component.detailscreen.DetailScreenTopBannerImage
import com.nanaland.ui.component.detailscreen.MoveToTopButton
import com.nanaland.util.ui.ScreenPreview
import com.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun MarketContentScreen(
    contentId: Long?,
    moveToBackScreen: () -> Unit,
    viewModel: MarketContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getMarketContent(contentId)
    }
    val marketContent = viewModel.marketContent.collectAsState().value
    MarketContentScreen(
        marketContent = marketContent,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun MarketContentScreen(
    marketContent: UiState<MarketContentData>,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        when (marketContent) {
            is UiState.Loading -> {}
            is UiState.Loading -> {}
            is UiState.Success -> {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column {
                        CustomTopBarWithShadow(
                            title = "전통시장",
                            onBackButtonClicked = moveToBackScreen
                        )

                        Column(
                            modifier = Modifier.verticalScroll(scrollState)
                        ) {
                            DetailScreenTopBannerImage(imageUri = marketContent.data.originUrl)

                            DetailScreenDescription(
                                isLiked = marketContent.data.favorite,
                                tag = marketContent.data.addressTag,
                                title = marketContent.data.title,
                                content = marketContent.data.content,
                                onLikeButtonClicked = {},
                                onShareButtonClicked = {}
                            )
                        }
                    }

                    MoveToTopButton {
                        Log.e("", "scroll")
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