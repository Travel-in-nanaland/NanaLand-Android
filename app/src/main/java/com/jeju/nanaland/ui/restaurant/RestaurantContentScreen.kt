package com.jeju.nanaland.ui.restaurant

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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.BuildConfig
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.restaurant.RestaurantContentData
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.globalvalue.type.AnchoredDraggableContentState
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.ReviewBottomBar
import com.jeju.nanaland.ui.component.common.topbar.TopBarCommon
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformation
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenInformationModificationProposalButton
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.ExperienceDetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.other.MoveToTopButton
import com.jeju.nanaland.ui.component.detailscreen.restaurant.MenuItem
import com.jeju.nanaland.ui.component.review.ReportBottomDialog
import com.jeju.nanaland.ui.component.review.ReportDialogDimBackground
import com.jeju.nanaland.ui.component.review.ReviewCard
import com.jeju.nanaland.ui.component.review.TotalRatingStar
import com.jeju.nanaland.ui.component.review.TotalReviewCountText
import com.jeju.nanaland.ui.component.review.getReportAnchoredDraggableState
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect
import kotlinx.coroutines.launch

@Composable
fun RestaurantContentScreen(
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
        moveToReviewListScreen = {
            if(restaurantContent is UiState.Success) {
                moveToReviewListScreen(
                    restaurantContent.data.favorite,
                    restaurantContent.data.images.firstOrNull()?.originUrl ?: "",
                    restaurantContent.data.title,
                    restaurantContent.data.address,
                )
            }
        },
        moveToReviewWritingScreen = {
            if(restaurantContent is UiState.Success) {
                moveToReviewWritingScreen(
                    restaurantContent.data.id,
                    restaurantContent.data.images.firstOrNull()?.originUrl ?: "",
                    restaurantContent.data.title,
                    restaurantContent.data.address,
                )
            }
        },
        moveToReportScreen = moveToReportScreen,
        moveToProfileScreen = moveToProfileScreen,
        isContent = true
    )
}

@OptIn(ExperimentalFoundationApi::class)
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
    moveToReportScreen: (Int) -> Unit,
    moveToProfileScreen: (Int?) -> Unit,
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
                TopBarCommon(
                    title = getString(R.string.common_제주_맛집),
                    onBackButtonClicked = moveToBackScreen,
                    menus = arrayOf(
                        R.drawable.ic_share to {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(Intent.EXTRA_TEXT, "${BuildConfig.BASE_URL}/share/${getLanguage()}?category=restaurant&id=${contentId}")
                                type = "text/plain"
                            }
                            val shareIntent = Intent.createChooser(sendIntent, null)
                            context.startActivity(shareIntent)
                        }
                    )
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
                                DetailScreenTopBannerImage(imageUri = restaurantContent.data.images[0].originUrl)

                                Spacer(Modifier.height(24.dp))

                                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
                                    ExperienceDetailScreenDescription(
                                        tag = restaurantContent.data.addressTag,
                                        keywords = restaurantContent.data.keywords,
                                        title = restaurantContent.data.title,
                                        content = restaurantContent.data.content,
                                    )

                                    Spacer(Modifier.height(32.dp))

                                    Text(
                                        text = getString(R.string.detail_screen_common_대표_메뉴),
                                        color = getColor().black,
                                        style = title02Bold
                                    )
                                }

                                restaurantContent.data.menus.forEachIndexed { idx, item ->
                                    Box(
                                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                                    ) {
                                        MenuItem(
                                            menuName = item.menuName,
                                            price = item.price,
                                            imageUrl = item.firstImage.originUrl
                                        )
                                    }

                                    if (idx != restaurantContent.data.menus.size - 1) {
                                        Spacer(Modifier
                                            .fillMaxWidth()
                                            .height(1.dp)
                                            .background(getColor().gray03))
                                    }
                                }

                                Spacer(Modifier.height(8.dp))
                                Spacer(Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .background(getColor().gray03))
                                Spacer(Modifier.height(16.dp))

                                Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {

                                    if (!restaurantContent.data.address.isNullOrEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_location_outlined,
                                            title = getString(R.string.detail_screen_common_주소),
                                            content = restaurantContent.data.address
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }

                                    if (!restaurantContent.data.time.isNullOrEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_clock_outlined,
                                            title = getString(R.string.detail_screen_common_영업_시간),
                                            content = restaurantContent.data.time
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }

                                    if (!restaurantContent.data.contact.isNullOrEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_phone_outlined,
                                            title = getString(R.string.detail_screen_common_연락처),
                                            content = restaurantContent.data.contact
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }

                                    if (!restaurantContent.data.service.isNullOrEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_headset_outlined,
                                            title = getString(R.string.detail_screen_common_제공_서비스),
                                            content = restaurantContent.data.service
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }

                                    if (!restaurantContent.data.homepage.isNullOrEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_clip_outlined,
                                            title = getString(R.string.detail_screen_common_홈페이지),
                                            content = restaurantContent.data.homepage
                                        )

                                        Spacer(Modifier.height(24.dp))
                                    }

                                    if (!restaurantContent.data.instagram.isNullOrEmpty()) {
                                        DetailScreenInformation(
                                            drawableId = R.drawable.ic_instagram_outlined,
                                            title = getString(R.string.detail_screen_common_인스타그램),
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
                                                coroutineScope.launch { reportDialogAnchoredDraggableState.animateTo(
                                                    AnchoredDraggableContentState.Open) }
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

            if (isDimBackgroundShowing.intValue > 0) {
                ReportDialogDimBackground(
                    isDimBackgroundShowing = isDimBackgroundShowing,
                    reportAnchoredDraggableState = reportDialogAnchoredDraggableState
                )
            }

            ReportBottomDialog(
                onClick = { moveToReportScreen(isDimBackgroundShowing.intValue) },
                hideDimBackground = { isDimBackgroundShowing.intValue = -1 },
                anchoredDraggableState = reportDialogAnchoredDraggableState
            )
        }
    }
}