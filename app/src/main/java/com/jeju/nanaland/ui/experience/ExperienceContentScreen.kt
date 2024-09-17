package com.jeju.nanaland.ui.experience

import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.animateTo
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.experience.ExperienceContent
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.ReviewBottomBar
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBarWithShareButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenNotice
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.ExperienceDetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.ui.component.review.ReportBottomDialog
import com.jeju.nanaland.ui.component.review.ReportDialogDimBackground
import com.jeju.nanaland.ui.component.review.ReviewCard
import com.jeju.nanaland.ui.component.review.TotalRatingStar
import com.jeju.nanaland.ui.component.review.TotalReviewCountText
import com.jeju.nanaland.ui.component.review.getReportAnchoredDraggableState
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch

@Composable
fun ExperienceContentScreen(
    experienceCategory: String,
    contentId: Int?,
    isSearch: Boolean,
    updatePrevScreenListFavorite: (Int, Boolean) -> Unit,
    moveToBackScreen: () -> Unit,
    moveToInfoModificationProposalScreen: () -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToReviewListScreen: (Boolean, String, String, String) -> Unit,
    moveToReviewWritingScreen: (Int, String, String, String) -> Unit,
    moveToReportScreen: (Int) -> Unit,
    moveToProfileScreen: (Int) -> Unit,
    viewModel: ExperienceContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getExperienceContent(contentId, isSearch)
        viewModel.getReview(contentId)
    }
    val experienceContent = viewModel.experienceContent.collectAsState().value
    val reviewList = viewModel.reviewList.collectAsState().value
    ExperienceContentScreen(
        experienceCategory = experienceCategory,
        contentId = contentId,
        experienceContent = experienceContent,
        toggleFavorite = viewModel::toggleFavorite,
        toggleReviewFavorite = viewModel::toggleReviewFavorite,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        reviewList = reviewList,
        moveToBackScreen = moveToBackScreen,
        moveToInfoModificationProposalScreen = moveToInfoModificationProposalScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToReviewListScreen = {
            if(experienceContent is UiState.Success) {
                moveToReviewListScreen(
                    experienceContent.data.favorite,
                    experienceContent.data.images.firstOrNull()?.originUrl ?: "",
                    experienceContent.data.title,
                    experienceContent.data.address,
                )
            }
        },
        moveToReviewWritingScreen = {
            if(experienceContent is UiState.Success)
                moveToReviewWritingScreen(
                    experienceContent.data.id,
                    experienceContent.data.images.firstOrNull()?.originUrl ?: "",
                    experienceContent.data.title,
                    experienceContent.data.address,
                )
        },
        moveToReportScreen = moveToReportScreen,
        moveToProfileScreen = moveToProfileScreen,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExperienceContentScreen(
    experienceCategory: String,
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
    moveToReportScreen: (Int) -> Unit,
    moveToProfileScreen: (Int) -> Unit,
    isContent: Boolean
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val reportDialogAnchoredDraggableState = remember { getReportAnchoredDraggableState() }
    val selectedReviewId = remember { mutableIntStateOf(0) }
    val isDimBackgroundShowing = remember { mutableIntStateOf(-1) }
    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CustomTopBarWithShareButton(
                    title = if (experienceCategory == ExperienceCategoryType.Activity.toString()) getString(R.string.common_액티비티) else getString(R.string.common_문화예술),
                    onBackButtonClicked = moveToBackScreen,
                    onShareButtonClicked = {
                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "http://13.125.110.80:8080/share/${getLanguage()}?category=experience&id=${contentId}")
                            type = "text/plain"
                        }
                        val shareIntent = Intent.createChooser(sendIntent, null)
                        context.startActivity(shareIntent)
                    }
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
                                DetailScreenTopBannerImage(imageUri = experienceContent.data.images[0].originUrl)

                                Spacer(Modifier.height(24.dp))

                                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                                    ExperienceDetailScreenDescription(
                                        tag = experienceContent.data.addressTag,
                                        keywords = experienceContent.data.keywords,
                                        title = experienceContent.data.title,
                                        content = experienceContent.data.content,
                                    )

                                    Spacer(Modifier.height(24.dp))

                                    DetailScreenNotice(
                                        title = getString(R.string.detail_screen_common_간단_설명),
                                        content = experienceContent.data.intro
                                    )

                                    Spacer(Modifier.height(32.dp))

                                    if (experienceContent.data.address.isNotEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_location_outlined,
                                            title = getString(R.string.detail_screen_common_주소),
                                            content = experienceContent.data.address
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }

                                    if (experienceContent.data.contact.isNotEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_phone_outlined,
                                            title = getString(R.string.detail_screen_common_연락처),
                                            content = experienceContent.data.contact
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }

                                    if (experienceContent.data.time.isNotEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_clock_outlined,
                                            title = getString(R.string.detail_screen_common_이용_시간),
                                            content = experienceContent.data.time
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }

                                    if (experienceContent.data.amenity.isNotEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_ticket_outlined,
                                            title = getString(R.string.detail_screen_common_입장료),
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

                        Spacer(
                            Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .background(getColor().gray03))

                        Spacer(Modifier.height(32.dp))

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
                                            toggleReviewFavorite = toggleReviewFavorite,
                                            onProfileClick = moveToProfileScreen,
                                            onMenuButtonClick = {
                                                isDimBackgroundShowing.intValue = it.id
                                                coroutineScope.launch { reportDialogAnchoredDraggableState.animateTo(AnchoredDraggableContentState.Open) }
                                                selectedReviewId.intValue = it.id
                                            }
                                        )

                                        Spacer(Modifier.height(16.dp))
                                    }

                                    Spacer(Modifier.height(24.dp))
                                }

                                if (reviewList.data.totalElements > 3) {
                                    Text(
                                        modifier = Modifier
                                            .padding(horizontal = 16.dp)
                                            .fillMaxWidth()
                                            .background(
                                                color = getColor().white,
                                                shape = RoundedCornerShape(50)
                                            )
                                            .border(
                                                border = BorderStroke(
                                                    width = 1.dp,
                                                    color = getColor().gray02,
                                                ),
                                                shape = RoundedCornerShape(50)
                                            )
                                            .clickableNoEffect {
                                                moveToReviewListScreen()
                                            }
                                            .padding(vertical = 11.dp),
                                        text = getString(R.string.detail_screen_common_후기_더보기),
                                        color = getColor().gray01,
                                        style = bodyBold,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                Spacer(Modifier.height(80.dp))
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

            if (isDimBackgroundShowing.intValue > 0) {
                ReportDialogDimBackground(
                    isDimBackgroundShowing = isDimBackgroundShowing,
                    reportAnchoredDraggableState = reportDialogAnchoredDraggableState
                )
            }

            ReportBottomDialog(
                onClick = { moveToReportScreen(selectedReviewId.intValue) },
                hideDimBackground = { isDimBackgroundShowing.intValue = -1 },
                anchoredDraggableState = reportDialogAnchoredDraggableState
            )
        }
    }
}

@ScreenPreview
@Composable
private fun ExperienceContentScreenPreview() {

}