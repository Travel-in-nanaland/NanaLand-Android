package com.jeju.nanaland.ui.restaurant

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.restaurant.RestaurantContentData
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.ReviewBottomBar
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBarWithShareButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.ExperienceDetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.ui.component.detailscreen.restaurant.MenuItem
import com.jeju.nanaland.ui.component.review.ReviewCard
import com.jeju.nanaland.ui.component.review.TotalRatingStar
import com.jeju.nanaland.ui.component.review.TotalReviewCountText
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch

@Composable
fun RestaurantContentScreen(
    contentId: Int?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToReviewListScreen: () -> Unit,
    moveToReviewWritingScreen: (Int, String, String, String) -> Unit,
    viewModel: RestaurantContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getRestaurantContent(contentId, isSearch)
        viewModel.getReview(contentId)
    }
    val restaurantContent = viewModel.restaurantContent.collectAsState().value
    val reviewList = viewModel.reviewList.collectAsState().value
    RestaurantContentScreen(
        contentId = contentId,
        restaurantContent = restaurantContent,
        toggleFavorite = viewModel::toggleFavorite,
        toggleReviewFavorite = viewModel::toggleReviewFavorite,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        reviewList = reviewList,
        moveToBackScreen = moveToBackScreen,
        moveToInfoModificationProposalScreen = moveToInfoModificationProposalScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToReviewListScreen = moveToReviewListScreen,
        moveToReviewWritingScreen = {
            if(restaurantContent is UiState.Success)
                moveToReviewWritingScreen(
                    restaurantContent.data.id,
                    restaurantContent.data.images.firstOrNull()?.thumbnailUrl ?: "",
                    restaurantContent.data.title,
                    restaurantContent.data.address,
                )
        },
        isContent = true
    )
}

@Composable
private fun RestaurantContentScreen(
    contentId: Int?,
    restaurantContent: UiState<RestaurantContentData>,
    toggleFavorite: (Int, (Int, Boolean) -> Unit) -> Unit,
    toggleReviewFavorite: (Int) -> Unit,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    reviewList: UiState<ReviewListData>,
    moveToBackScreen: () -> Unit,
    moveToReviewListScreen: () -> Unit,
    moveToReviewWritingScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    isContent: Boolean
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    CustomSurface {
        CustomTopBarWithShareButton(
            title = "액티비티",
            onBackButtonClicked = moveToBackScreen,
            onShareButtonClicked = {}
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Column(modifier = Modifier.verticalScroll(scrollState)) {
                when (restaurantContent) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        DetailScreenTopBannerImage(imageUri = restaurantContent.data.images[0].thumbnailUrl)

                        Spacer(Modifier.height(24.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            ExperienceDetailScreenDescription(
                                tag = restaurantContent.data.addressTag,
                                title = restaurantContent.data.title,
                                content = restaurantContent.data.content,
                            )

                            Spacer(Modifier.height(32.dp))

                            Text(
                                text = "대표 메뉴",
                                color = getColor().black,
                                style = title02Bold
                            )

                            restaurantContent.data.menus.forEach {
                                MenuItem(
                                    menuName = it.menuName,
                                    price = it.price,
                                    imageUrl = it.firstImage.thumbnailUrl
                                )
                            }

                            Spacer(Modifier.height(32.dp))

                            if (!restaurantContent.data.address.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_location_outlined,
                                    title = "주소",
                                    content = restaurantContent.data.address
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!restaurantContent.data.time.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_clock_outlined,
                                    title = "영업 시간",
                                    content = restaurantContent.data.time
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!restaurantContent.data.contact.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_phone_outlined,
                                    title = "연락처",
                                    content = restaurantContent.data.contact
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!restaurantContent.data.service.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_headset_outlined,
                                    title = "제공 서비스",
                                    content = restaurantContent.data.service
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!restaurantContent.data.homepage.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_clip_outlined,
                                    title = "홈페이지",
                                    content = restaurantContent.data.homepage
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (!restaurantContent.data.instagram.isNullOrEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_instagram_outlined,
                                    title = "인스타그램",
                                    content = restaurantContent.data.instagram
                                )

                                Spacer(Modifier.height(24.dp))
                            }
                        }

                        Spacer(Modifier.height(16.dp))

                        DetailScreenInformationModificationProposalButton {
                            moveToInfoModificationProposalScreen()
                        }

                        Spacer(Modifier.height(40.dp))

                    }
                    is UiState.Failure -> {}
                }


                when (reviewList) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        Column(
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                        ) {
                            Row {
                                TotalReviewCountText(count = reviewList.data.totalElements)

                                Spacer(Modifier.weight(1f))

                                TotalRatingStar(rating = reviewList.data.totalAvgRating)
                            }

                            Spacer(Modifier.height(24.dp))

                            reviewList.data.data.forEach {
                                ReviewCard(
                                    data = it,
                                    toggleReviewFavorite = toggleReviewFavorite
                                )

                                Spacer(Modifier.height(16.dp))
                            }

                            Spacer(Modifier.height(24.dp))
                        }

                        if (reviewList.data.totalElements > 3) {
                            BottomOkButton(
                                text = "후기 더보기",
                                isActivated = true,
                                onClick = moveToReviewListScreen
                            )

                            Spacer(Modifier.height(80.dp))
                        }
                    }
                    is UiState.Failure -> {}
                }
            }

            MoveToTopButton {
                coroutineScope.launch { scrollState.animateScrollTo(0) }
            }
        }

        when (restaurantContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                ReviewBottomBar(
                    isFavorite = restaurantContent.data.favorite,
                    toggleFavorite = {
                        if (contentId == null) return@ReviewBottomBar
                        toggleFavorite(
                            contentId,
                            updatePrevScreenListFavorite
                        )
                    },
                    moveToReviewWritingScreen = moveToReviewWritingScreen,
                    moveToSignInScreen = moveToSignInScreen
                )
            }
            is UiState.Failure -> {}
        }
    }
}