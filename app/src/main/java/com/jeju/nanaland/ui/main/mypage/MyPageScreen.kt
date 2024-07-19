package com.jeju.nanaland.ui.main.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.domain.entity.member.UserProfile
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.ui.component.mypage.MyPageScreenListSection
import com.jeju.nanaland.ui.component.mypage.MyPageScreenProfileSection
import com.jeju.nanaland.ui.component.mypage.MyPageScreenTabSection
import com.jeju.nanaland.ui.component.mypage.MyPageScreenTopBar
import com.jeju.nanaland.ui.component.mypage.TempNoticeData
import com.jeju.nanaland.ui.component.mypage.TempReviewData
import com.jeju.nanaland.ui.component.nonmember.NonMemberGuideDialog
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.UiState

@Composable
fun MyPageScreen(
    prevViewType: MainScreenViewType,
    updateMainScreenViewType: (MainScreenViewType) -> Unit,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    viewModel: MyPageViewModel = hiltViewModel()
) {
    val userProfile = viewModel.userProfile.collectAsState().value
    val reviews = viewModel.reviews.collectAsState()
    val notices = viewModel.notices.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getUserProfile()
    }
    MyPageScreen(
        prevViewType = prevViewType,
        updateMainScreenViewType = updateMainScreenViewType,
        userProfile = userProfile,
        reviews = reviews.value,
        notices = notices.value,
        moveToSettingsScreen = moveToSettingsScreen,
        moveToProfileModificationScreen = moveToProfileModificationScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToTypeTestScreen = moveToTypeTestScreen,
        isContent = true
    )
}

@Composable
private fun MyPageScreen(
    prevViewType: MainScreenViewType,
    updateMainScreenViewType: (MainScreenViewType) -> Unit,
    userProfile: UiState<UserProfile>,
    reviews: List<TempReviewData>,
    notices: List<TempNoticeData>?,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    isContent: Boolean
) {

    val isNonMemberGuideDialogShowing = remember { mutableStateOf(false) }
    var isReviewList by remember { mutableStateOf(true) }

    MyPageScreenTopBar {
        moveToSettingsScreen()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor().white)
            .verticalScroll(rememberScrollState())
    ) {
        when (userProfile) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Column(
                    modifier = Modifier.background(getColor().main10)
                ) {
                    Spacer(Modifier.height(24.dp))

                    MyPageScreenProfileSection(
                        profile = userProfile.data,
                        isMine = true,
                        moveToSignInScreen = moveToSignInScreen,
                        moveToProfileModificationScreen = {
                            moveToProfileModificationScreen(
                                userProfile.data.profileImageUrl,
                                userProfile.data.nickname,
                                userProfile.data.description
                            )
                        },
                        moveToTypeTestScreen = moveToTypeTestScreen
                    )

                    Spacer(Modifier.height(24.dp))

                    MyPageScreenTabSection(
                        isReviewList = isReviewList,
                        reviewSize = reviews.size,
                        moveToReviewScreen = { },
                        toggleReviewNoticeTab = { isReviewList = !isReviewList },
                    )
                }

                MyPageScreenListSection(
                    isReviewList = isReviewList,
                    reviews = reviews,
                    notices = notices,
                    moveToReviewWriteScreen = { /*TODO*/ },
                    moveToReviewScreen = { /*TODO*/ },
                    moveToNoticeScreen = { /*TODO*/ }
                )

            }
            is UiState.Failure -> {}
        }
    }

    if (isNonMemberGuideDialogShowing.value) {
        NonMemberGuideDialog(
            onCloseClick = { isNonMemberGuideDialogShowing.value = false },
            moveToSignInScreen = moveToSignInScreen
        )
    }
//
//    if (UserData.provider == "GUEST") {
//        NonMemberGuideDialog(
//            onCloseClick = { updateMainScreenViewType(prevViewType) },
//            moveToSignInScreen = moveToSignInScreen
//        )
//    }
}

@Preview
@Composable
private fun MyPageScreenPreview() {

}