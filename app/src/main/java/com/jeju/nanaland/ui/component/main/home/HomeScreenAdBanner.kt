package com.jeju.nanaland.ui.component.main.home

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
import com.jeju.nanaland.ui.component.main.home.parts.HomeScreenAdContent
import com.jeju.nanaland.util.listfilter.ListFilter
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreenAdBanner(
    moveToNatureListScreen: (ListFilter) -> Unit,
    moveToFestivalListScreen: (ListFilter) -> Unit,
    moveToMarketListScreen: () -> Unit
) {
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
                delay(5000)
                pagerState.animateScrollToPage(pagerState.currentPage + 1)
            }
        }
    }
    HorizontalPager(state = pagerState) { page ->
        HomeScreenAdContent(
            idx = page,
            onClick = {
                when (page % 4) {
                    0 -> moveToNatureListScreen(ListFilter("성산"))
                    1 -> moveToNatureListScreen(ListFilter("애월"))
                    2 -> moveToMarketListScreen()
                    else -> moveToFestivalListScreen(ListFilter("2024.07.01~2024.07.31"))
                }
            }
        )
    }

    Spacer(Modifier.height(10.dp))

    HomeScreenAdPageIndicator(pageNum = pagerState.currentPage)
}