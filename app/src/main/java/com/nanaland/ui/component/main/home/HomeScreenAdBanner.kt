package com.nanaland.ui.component.main.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.main.home.parts.HomeScreenAdContent
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenAdBanner() {
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
    HorizontalPager(state = pagerState) { page ->
        HomeScreenAdContent(page)
    }

    Spacer(Modifier.height(10.dp))

    HomeScreenAdPageIndicator(pageNum = pagerState.currentPage)
}