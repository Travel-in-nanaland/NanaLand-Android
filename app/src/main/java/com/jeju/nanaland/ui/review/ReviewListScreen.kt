package com.jeju.nanaland.ui.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.review.ReviewData
import com.jeju.nanaland.globalvalue.constant.PAGING_THRESHOLD
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
import com.jeju.nanaland.ui.component.review.ReviewCard
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.ui.theme.title02
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
    val reviewCount = viewModel.reviewCount.collectAsState().value
    val reviewRating = viewModel.reviewRating.collectAsState().value
    val reviewList = viewModel.reviewList.collectAsState().value
    ReviewListScreen(
        reviewCount = reviewCount,
        reviewRating = reviewRating,
        reviewList = reviewList,
        getReviewList = {
            viewModel.getReviewList(
                contentId = contentId,
                category = category
            )
        },
        toggleReviewFavorite = viewModel::toggleReviewFavorite,
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun ReviewListScreen(
    reviewCount: UiState<Int>,
    reviewRating: UiState<Double>,
    reviewList: UiState<List<ReviewData>>,
    getReviewList: () -> Unit,
    toggleReviewFavorite: (Int) -> Unit,
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

                Spacer(Modifier.height(26.dp))

                when (reviewCount) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        Row(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        ) {
                            Text(
                                text = "후기",
                                color = getColor().black,
                                style = title01Bold
                            )

                            Spacer(Modifier.width(4.dp))

                            Text(
                                text = reviewCount.data.toString(),
                                color = getColor().main,
                                style = title02
                            )
                        }
                    }
                    is UiState.Failure -> {}
                }

                Spacer(Modifier.height(8.dp))

                when (reviewRating) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        Row(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(5) {
                                Image(
                                    modifier = Modifier.size(24.dp),
                                    painter = painterResource(if (reviewRating.data - 1 >= it) R.drawable.ic_star else R.drawable.ic_star_empty),
                                    contentDescription = null
                                )
                            }

                            Spacer(Modifier.width(8.dp))

                            Text(
                                text = String.format("%.1f", reviewRating.data),
                                color = getColor().black,
                                style = body02Bold
                            )
                        }
                    }
                    is UiState.Failure -> {}
                }

                Spacer(Modifier.height(24.dp))

                when (reviewList) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(1)
                        ) {
                            itemsIndexed(reviewList.data) { idx, item ->
                                Box(
                                    modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                                ) {
                                    ReviewCard(
                                        data = item,
                                        toggleReviewFavorite = toggleReviewFavorite
                                    )
                                }
                            }
                        }
                    }
                    is UiState.Failure -> {}
                }
            }
        }
    }
}