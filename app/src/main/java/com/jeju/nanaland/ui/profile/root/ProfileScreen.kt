package com.jeju.nanaland.ui.profile.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.ui.component.common.topbar.MyTopBar
import com.jeju.nanaland.ui.profile.component.parts.ReportSheet
import com.jeju.nanaland.ui.profile.root.component.ProfileScreenNoticeListSection
import com.jeju.nanaland.ui.profile.root.component.ProfileScreenProfileSection
import com.jeju.nanaland.ui.profile.root.component.ProfileScreenReviewListSection
import com.jeju.nanaland.ui.profile.root.component.parts.ProfileScreenMoreInfoPart
import com.jeju.nanaland.ui.profile.root.component.parts.ProfileScreenTabPart
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState

/** 마이 페이지 **/
@Composable
fun ProfileScreen(
    onBackButtonClicked: (() -> Unit)?,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: (TravelType) -> Unit,
    moveToReviewWriteScreen: () -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToProfileNoticeListScreen: (Int?) -> Unit,
) {
    ProfileScreen(
        isMine = true,
        onBackButtonClicked = onBackButtonClicked,
        moveToSettingsScreen = moveToSettingsScreen,
        moveToProfileModificationScreen = moveToProfileModificationScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToTypeTestScreen = moveToTypeTestScreen,
        moveToTypeTestResultScreen = { _, it -> moveToTypeTestResultScreen(it) },
        moveToReviewWriteScreen = moveToReviewWriteScreen,
        moveToProfileReviewListScreen = moveToProfileReviewListScreen,
        moveToProfileNoticeListScreen = moveToProfileNoticeListScreen,

        moveToReportScreen = {},
    )
}
/** 타인 프로필 **/
@Composable
fun ProfileScreen(
    onBackButtonClicked: () -> Unit,
    moveToTypeTestResultScreen: (String, TravelType) -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToReportScreen: (Int) -> Unit,
) {
    ProfileScreen(
        isMine = false,
        onBackButtonClicked = onBackButtonClicked,
        moveToTypeTestResultScreen = moveToTypeTestResultScreen,
        moveToProfileReviewListScreen = moveToProfileReviewListScreen,
        moveToReportScreen = moveToReportScreen,

        moveToSettingsScreen = {},
        moveToReviewWriteScreen = {},
        moveToProfileModificationScreen = {_,_,_ -> },
        moveToSignInScreen = {},
        moveToTypeTestScreen = {},
        moveToProfileNoticeListScreen = {},
    )
}

@Composable
private fun ProfileScreen(
    isMine: Boolean,
    onBackButtonClicked: (() -> Unit)?,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: (String, TravelType) -> Unit,
    moveToReviewWriteScreen: () -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToProfileNoticeListScreen: (Int?) -> Unit,
    moveToReportScreen: (Int) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val userProfile = viewModel.userProfile.collectAsState()
    val reviews = viewModel.reviews.collectAsLazyPagingItems()
    val notices = if(isMine)
        viewModel.notices.collectAsLazyPagingItems()
    else
        null

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    val isReviewList = viewModel.isReviewList.collectAsStateWithLifecycle()
    var moreOptionDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor().main5)
            .verticalScroll(rememberScrollState())
    ) {
        MyTopBar(
            title = if(isMine) getString(R.string.common_나의_나나) else "",
            onBackButtonClicked = onBackButtonClicked,
            menus = arrayOf(
                if(isMine)
                    R.drawable.ic_gear_outlined to moveToSettingsScreen
                else
                    R.drawable.ic_more_vert to { moreOptionDialog = true },
            )
        )
        when (val up = userProfile.value) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                val reviewList = reviews.itemSnapshotList.items.take(12)
                val noticeList = notices?.itemSnapshotList?.items

                Spacer(Modifier.height(24.dp))

                ProfileScreenProfileSection(
                    profile = up.data,
                    isMine = isMine,
                    moveToSignInScreen = moveToSignInScreen,
                    moveToProfileModificationScreen = {
                        moveToProfileModificationScreen(
                            up.data.profileImage.originUrl,
                            up.data.nickname,
                            up.data.description
                        )
                    },
                    moveToTypeTestScreen = moveToTypeTestScreen,
                    moveToTypeTestResultScreen = {
                        up.data.travelType?.let {
                            moveToTypeTestResultScreen(up.data.nickname, it)
                        }
                    }
                )

                Spacer(Modifier.height(24.dp))

                ProfileScreenTabPart(
                    isReviewList = isReviewList.value,
                    reviewSize = reviewList.size,
                    moveToReviewScreen = { moveToProfileReviewListScreen(null) },
                    toggleReviewNoticeTab = if(!isMine) null else {
                        { viewModel.setIsReviewList(!isReviewList.value) }
                    }
                )
                Column(Modifier.background(getColor().white)) {

                    if (isMine) {
                        if (!isReviewList.value || !viewModel.isGuest)
                            ProfileScreenMoreInfoPart(
                                isReviewList = isReviewList.value,
                                listSize = if (isReviewList.value) reviewList.size else noticeList?.size
                                    ?: 0,
                                moveToListPage = {
                                    if (isReviewList.value) moveToProfileReviewListScreen(null)
                                    else moveToProfileNoticeListScreen(null)
                                },
                                moveToReviewWriteScreen = moveToReviewWriteScreen
                            )
                    } else
                        HorizontalDivider()

                    if (isReviewList.value) {
                        ProfileScreenReviewListSection(reviewList) {
                            moveToProfileReviewListScreen(it)
                        }
                    } else if (!noticeList.isNullOrEmpty()) {
                        ProfileScreenNoticeListSection(noticeList) {
                            moveToProfileNoticeListScreen(it)
                        }
                    }
                }

                if (isMine){
                    if(isReviewList.value) {
                        if (viewModel.isGuest) {
                            ListCenterText(getString(R.string.mypage_screen_review_guest))
                            return@Column
                        }
                        else if (reviewList.isEmpty()) {
                            ListCenterText(getString(R.string.mypage_screen_review_empty))
                            return@Column
                        }
                    }
                    else if(noticeList.isNullOrEmpty()){
                        ListCenterText(getString(R.string.mypage_screen_notice_empty))
                        return@Column
                    }
                }
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .background(getColor().white))

            }
            is UiState.Failure -> {}
        }
    }

    if(moreOptionDialog)
        ReportSheet(
            onDismissRequest = { moreOptionDialog = false },
            onReport = { viewModel.userId?.let { moveToReportScreen(it) } }
        )
}

@Composable
private fun ColumnScope.ListCenterText(txt: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(getColor().white)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = txt,
            color = getColor().gray01,
            style = body01,
            textAlign = TextAlign.Center
        )
    }
}