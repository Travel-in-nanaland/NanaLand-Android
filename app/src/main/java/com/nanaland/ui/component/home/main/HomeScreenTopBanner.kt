package com.nanaland.ui.component.home.main

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.nanaland.ui.component.home.main.parts.HomeScreenNanaPickBanner
import com.nanaland.ui.component.home.main.parts.HomeScreenTopBannerPageIndicator
import com.nanaland.util.ui.UiState
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenTopBanner(
    topBanner: UiState<List<NanaPickBannerData>>,
    onBannerClick: () -> Unit,
) {
    Box {
        val pagerState = rememberPagerState(
            initialPage = 200,
            pageCount = {1000}
        )
        val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
        if (!isDragged) {
            LaunchedEffect(Unit) {
                while (true) {
                    delay(3500)
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }
        }
        when (topBanner) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                HorizontalPager(state = pagerState) { page ->
                    HomeScreenNanaPickBanner(
                        item = topBanner.data[page % (topBanner.data.size)],
                        onClick = { onBannerClick() }
                    )
                }

                HomeScreenTopBannerPageIndicator(
                    itemCnt = topBanner.data.size,
                    pageNum = pagerState.currentPage
                )
            }
            is UiState.Failure -> {}
        }
    }
}