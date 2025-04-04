package com.jeju.nanaland.ui.experience

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.experience.ExperienceContent
import com.jeju.nanaland.domain.entity.review.ReviewData
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.ExperienceCategoryType
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.ReviewBottomBar
import com.jeju.nanaland.ui.component.common.dialog.BottomSheetSelectDialog
import com.jeju.nanaland.ui.component.common.dialog.DialogCommon
import com.jeju.nanaland.ui.component.common.dialog.DialogCommonType
import com.jeju.nanaland.ui.component.common.dialog.FullImageDialog
import com.jeju.nanaland.ui.component.common.icon.GoToUpInList
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenNotice
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.ExperienceDetailScreenDescription
import com.jeju.nanaland.ui.component.review.ReviewCard
import com.jeju.nanaland.ui.component.review.TotalRatingStar
import com.jeju.nanaland.ui.component.review.TotalReviewCountText
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.intent.goToShare
import com.jeju.nanaland.util.network.NetworkResult
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
    moveToProfileScreen: (Int?) -> Unit,
    moveToReviewEditScreen: (Int, ReviewCategoryType) -> Unit,
    moveToMap: (ROUTE.Content.Map)-> Unit,
    viewModel: ExperienceContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getExperienceContent(contentId, isSearch)
        viewModel.getReview(contentId)
    }
    val scope = rememberCoroutineScope()
    val experienceContent = viewModel.experienceContent.collectAsState().value
    val reviewList = viewModel.reviewList.collectAsState().value
    var removeReviewId by remember { mutableIntStateOf(-1) }

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
        moveToMap = moveToMap,
        onEdit = { moveToReviewEditScreen(it.id, ReviewCategoryType.EXPERIENCE) },
        onRemove = { data ->
            removeReviewId = data.id
        },
        isContent = true
    )
    if (removeReviewId != -1) {
        DialogCommon(
            DialogCommonType.RemoveReview,
            onDismiss = { removeReviewId = -1 },
            onYes = { scope.launch {
                if(viewModel.setRemove(removeReviewId) is NetworkResult.Success) {
                    removeReviewId = -1
                    viewModel.getReview(contentId)
                }
            } },
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
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
    moveToProfileScreen: (Int?) -> Unit,
    moveToMap: (ROUTE.Content.Map)-> Unit,
    onEdit: (ReviewData) -> Unit,
    onRemove: (ReviewData) -> Unit,
    isContent: Boolean
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val selectedReviewId = remember { mutableIntStateOf(-1) }
    val fullImageUrl = remember { mutableStateOf<String?>(null) }

    fullImageUrl.value?.let {
        FullImageDialog(it) { fullImageUrl.value = null }
    }

    CustomSurface {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                TopBarCommon(
                    title = if (experienceCategory == ExperienceCategoryType.Activity.toString()) getString(R.string.common_액티비티) else getString(R.string.common_문화예술),
                    onBackButtonClicked = moveToBackScreen,
                    menus = arrayOf(
                        R.drawable.ic_share to {  goToShare(context, "experience",contentId) }
                    )
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
                                            content = experienceContent.data.address,
                                            moveToMap = { moveToMap(ROUTE.Content.Map(
                                                name = experienceContent.data.title,
                                                localLocate = experienceContent.data.address,
                                                id = experienceContent.data.id,
                                                category = "EXPERIENCE"
                                            )) }
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
                                            onImageClick = { fullImageUrl.value = it },
                                            onProfileClick = moveToProfileScreen,
                                            onReport = { selectedReviewId.intValue = it.id },
                                            onEdit = onEdit,
                                            onRemove = onRemove
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

                    GoToUpInList(scrollState, Modifier.align(Alignment.BottomEnd))
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

            if(selectedReviewId.intValue != -1) {
                BottomSheetSelectDialog(
                    onDismiss = { selectedReviewId.intValue = -1 },
                    items = arrayOf(getString(R.string.common_신고하기) to { moveToReportScreen(selectedReviewId.intValue) })
                )
            }
        }
    }
}

@ScreenPreview
@Composable
private fun ExperienceContentScreenPreview() {

}