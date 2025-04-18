package com.jeju.nanaland.ui.restaurant

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
import com.jeju.nanaland.domain.entity.restaurant.RestaurantContentData
import com.jeju.nanaland.domain.entity.review.ReviewData
import com.jeju.nanaland.domain.entity.review.ReviewListData
import com.jeju.nanaland.domain.navigation.ROUTE
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
import com.jeju.nanaland.ui.component.detailscreen.other.DetailScreenTopBannerImage
import com.jeju.nanaland.ui.component.detailscreen.other.ExperienceDetailScreenDescription
import com.jeju.nanaland.ui.component.detailscreen.restaurant.MenuItem
import com.jeju.nanaland.ui.component.review.ReviewCard
import com.jeju.nanaland.ui.component.review.TotalRatingStar
import com.jeju.nanaland.ui.component.review.TotalReviewCountText
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.intent.goToShare
import com.jeju.nanaland.util.network.NetworkResult
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
    moveToReviewEditScreen: (Int, ReviewCategoryType) -> Unit,
    moveToMap: (ROUTE.Content.Map)-> Unit,
    viewModel: RestaurantContentViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getRestaurantContent(contentId, isSearch)
        viewModel.getReview(contentId)
    }
    val scope = rememberCoroutineScope()
    val restaurantContent = viewModel.restaurantContent.collectAsState().value
    val reviewList = viewModel.reviewList.collectAsState().value
    var removeReviewId by remember { mutableIntStateOf(-1) }

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
        moveToMap = moveToMap,
        onEdit = { moveToReviewEditScreen(it.id, ReviewCategoryType.RESTAURANT) },
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
                    title = getString(R.string.common_제주_맛집),
                    onBackButtonClicked = moveToBackScreen,
                    menus = arrayOf(
                        R.drawable.ic_share to { goToShare(context, "restaurant",contentId) }
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
                                        style = bodyBold
                                    )
                                }

                                restaurantContent.data.menus.forEachIndexed { idx, item ->
                                    Box(
                                        modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                                    ) {
                                        MenuItem(
                                            menuName = item.menuName,
                                            price = item.price,
                                            imageUrl = item.firstImage
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
                                            content = restaurantContent.data.address,
                                            moveToMap = { moveToMap(ROUTE.Content.Map(
                                                name = restaurantContent.data.title,
                                                localLocate = restaurantContent.data.address,
                                                id = restaurantContent.data.id,
                                                category = "RESTAURANT"
                                            )) }
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

            if(selectedReviewId.intValue != -1) {
                BottomSheetSelectDialog(
                    onDismiss = { selectedReviewId.intValue = -1 },
                    items = arrayOf(getString(R.string.common_신고하기) to { moveToReportScreen(selectedReviewId.intValue) })
                )
            }
        }
    }
}