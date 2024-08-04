package com.jeju.nanaland.ui.review

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.domain.entity.review.ReviewData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.review.ReviewCard
import com.jeju.nanaland.util.ui.UiState

@Composable
fun ReviewListScreen(
    contentId: Int?,
    category: String?,
    moveToBackScreen: () -> Unit,
    viewModel: ReviewListViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getReviewList(
            contentId = contentId,
            category = category
        )
    }
    val reviewList = viewModel.reviewList.collectAsState().value
    ReviewListScreen(
        reviewList = reviewList,
        getReviewList = {
            viewModel.getReviewList(
                contentId = contentId,
                category = category
            )
        },
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun ReviewListScreen(
    reviewList: UiState<List<ReviewData>>,
    getReviewList: () -> Unit,
    moveToBackScreen: () -> Unit,
    isContent: Boolean
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
            getReviewList()
        }
    }

    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBar(
                    title = "",
                    onBackButtonClicked = moveToBackScreen
                )

                when (reviewList) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(1)
                        ) {
                            itemsIndexed(reviewList.data) { idx, item ->
                                ReviewCard(
                                    data = item
                                )
                            }
                        }
                    }
                    is UiState.Failure -> {}
                }
            }
        }
    }
}