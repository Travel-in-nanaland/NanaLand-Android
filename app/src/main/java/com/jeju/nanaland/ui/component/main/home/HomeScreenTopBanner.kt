package com.jeju.nanaland.ui.component.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import com.jeju.nanaland.domain.entity.nanapick.NanaPickBannerData
import com.jeju.nanaland.ui.component.main.home.parts.HomeScreenNanaPickBanner
import com.jeju.nanaland.ui.component.main.home.parts.HomeScreenTopBannerPageIndicator
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenTopBanner(
    topBanner: UiState<List<NanaPickBannerData>>,
    onBannerClick: (Long, String?, Boolean) -> Unit,
) {
    Box {
        val pagerState = rememberPagerState(
            initialPage = 200,
            pageCount = {100000}
        )
        LaunchedEffect(Unit) {
            pagerState.scrollToPage(200)
        }
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
                if (topBanner.data.isNotEmpty()) {
                    HorizontalPager(state = pagerState) { page ->
                        HomeScreenNanaPickBanner(
                            item = topBanner.data[page % (topBanner.data.size)],
                            onClick = { onBannerClick(topBanner.data[page % (topBanner.data.size)].id, "NANA", false) }
                        )
                    }

                    HomeScreenTopBannerPageIndicator(
                        itemCnt = topBanner.data.size,
                        pageNum = pagerState.currentPage
                    )
                }
            }
            is UiState.Failure -> {}
        }
    }
}