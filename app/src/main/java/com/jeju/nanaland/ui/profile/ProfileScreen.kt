package com.jeju.nanaland.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomTopBarWithMenu
import com.jeju.nanaland.ui.profile.component.ProfileScreenListSection
import com.jeju.nanaland.ui.profile.component.ProfileScreenProfileSection
import com.jeju.nanaland.ui.profile.component.parts.ReportSheet
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.UiState
import com.jeju.nanaland.util.ui.clickableNoEffect

/** 마이 페이지 **/
@Composable
fun ProfileScreen(
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: () -> Unit,
    moveToReviewWriteScreen: () -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToProfileNoticeListScreen: (Int?) -> Unit,
) {
    ProfileScreen(
        isMine = true,
        moveToSettingsScreen = moveToSettingsScreen,
        moveToProfileModificationScreen = moveToProfileModificationScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToTypeTestScreen = moveToTypeTestScreen,
        moveToTypeTestResultScreen = moveToTypeTestResultScreen,
        moveToReviewWriteScreen = moveToReviewWriteScreen,
        moveToProfileReviewListScreen = moveToProfileReviewListScreen,
        moveToProfileNoticeListScreen = moveToProfileNoticeListScreen,

        onBackButtonClicked ={},
    )
}
/** 타인 프로필 **/
@Composable
fun ProfileScreen(
    onBackButtonClicked: () -> Unit,
    moveToTypeTestResultScreen: () -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
) {
    ProfileScreen(
        isMine = false,
        onBackButtonClicked = onBackButtonClicked,
        moveToTypeTestResultScreen = moveToTypeTestResultScreen,
        moveToProfileReviewListScreen = moveToProfileReviewListScreen,

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
    onBackButtonClicked: () -> Unit,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: () -> Unit,
    moveToReviewWriteScreen: () -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToProfileNoticeListScreen: (Int?) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val userProfile = viewModel.userProfile.collectAsState()
    val reviews = viewModel.reviews.collectAsLazyPagingItems()
    val notices = if(isMine)
        viewModel.notices.collectAsLazyPagingItems()
    else
        null

    var moreOptionDialog by remember { mutableStateOf(false) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor().main5)
            .verticalScroll(rememberScrollState())
    ) {
        CustomTopBarWithMenu(
            title = if(isMine) getString(R.string.common_나의_나나) else "",
            drawShadow = false,
            onBackButtonClicked = if(isMine) null else onBackButtonClicked,
        ) {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickableNoEffect {
                        if (isMine) moveToSettingsScreen()
                        else moreOptionDialog = true
                    },
                painter = painterResource(
                    if(isMine) R.drawable.ic_gear_outlined
                    else R.drawable.ic_more_vert
                ),
                contentDescription = null,
            )
        }
        when (val up = userProfile.value) {
            is UiState.Loading -> {}
            is UiState.Success -> {                Spacer(Modifier.height(24.dp))

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
                    moveToTypeTestResultScreen = moveToTypeTestResultScreen
                )

                Spacer(Modifier.height(24.dp))

                ProfileScreenListSection(
                    reviews = reviews.itemSnapshotList.items.take(12),
                    notices = notices?.itemSnapshotList?.items?.take(12),
                    moveToReviewWriteScreen = moveToReviewWriteScreen,
                    moveToReviewScreen = moveToProfileReviewListScreen,
                    moveToNoticeScreen = moveToProfileNoticeListScreen
                )

                Spacer(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(getColor().white))

            }
            is UiState.Failure -> {}
        }
    }

    if(moreOptionDialog)
        ReportSheet(
            onDismissRequest = { moreOptionDialog = false },
            onReport = { /*TODO*/ }
        )
}
