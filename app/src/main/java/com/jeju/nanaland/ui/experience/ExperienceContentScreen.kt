package com.jeju.nanaland.ui.experience

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.experience.ExperienceContent
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.ReviewBottomBar
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBarWithShareButton
import com.jeju.nanaland.ui.component.detailscreen.other.ExperienceDetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.ui.component.review.ReviewCard
import com.jeju.nanaland.ui.component.review.TotalRatingStar
import com.jeju.nanaland.ui.component.review.TotalReviewCountText
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import kotlinx.coroutines.launch
import kotlin.math.exp

@Composable
fun ExperienceContentScreen(
    contentId: Int?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToReviewListScreen: () -> Unit,
    moveToReviewWritingScreen: (Int, String, String, String) -> Unit,
    viewModel: ExperienceContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getExperienceContent(contentId, isSearch)
        viewModel.getReview(contentId)
    }
    val experienceContent = viewModel.experienceContent.collectAsState().value
    val reviewList = viewModel.reviewList.collectAsState().value
    ExperienceContentScreen(
        contentId = contentId,
        experienceContent = experienceContent,
        toggleFavorite = viewModel::toggleFavorite,
        toggleReviewFavorite = viewModel::toggleReviewFavorite,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        reviewList = reviewList,
        moveToBackScreen = moveToBackScreen,
        moveToInfoModificationProposalScreen = moveToInfoModificationProposalScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToReviewListScreen = moveToReviewListScreen,
        moveToReviewWritingScreen = {
            if(experienceContent is UiState.Success)
                moveToReviewWritingScreen(
                    experienceContent.data.id,
                    experienceContent.data.images.firstOrNull()?.thumbnailUrl ?: "",
                    experienceContent.data.title,
                    experienceContent.data.address,
                )
        },
        isContent = true
    )
}

@Composable
private fun ExperienceContentScreen(
    contentId: Int?,
    experienceContent: UiState<ExperienceContent>,
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
                when (experienceContent) {
                    is UiState.Loading -> {}
                    is UiState.Success -> {
                        DetailScreenTopBannerImage(imageUri = experienceContent.data.images[0].thumbnailUrl)

                        Spacer(Modifier.height(24.dp))

                        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                            ExperienceDetailScreenDescription(
                                tag = experienceContent.data.addressTag,
                                title = experienceContent.data.title,
                                content = experienceContent.data.content,
                            )

                            Spacer(Modifier.height(32.dp))

                            if (experienceContent.data.address.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_location_outlined,
                                    title = "주소",
                                    content = experienceContent.data.address
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (experienceContent.data.contact.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_phone_outlined,
                                    title = "연락처",
                                    content = experienceContent.data.contact
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (experienceContent.data.time.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_clock_outlined,
                                    title = "이용 시간",
                                    content = experienceContent.data.time
                                )

                                Spacer(Modifier.height(24.dp))
                            }

                            if (experienceContent.data.amenity.isNotEmpty()) {
                                DetailScreenInformation(
                                    drawableId = R.drawable.ic_ticket_outlined,
                                    title = "입장료",
                                    content = experienceContent.data.amenity
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


        when (experienceContent) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                ReviewBottomBar(
                    isFavorite = experienceContent.data.favorite,
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

@ScreenPreview
@Composable
private fun ExperienceContentScreenPreview() {

}