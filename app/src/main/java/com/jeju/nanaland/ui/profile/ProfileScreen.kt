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
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomTopBarWithMenu
import com.jeju.nanaland.ui.profile.component.ProfileScreenListSection
import com.jeju.nanaland.ui.profile.component.ProfileScreenProfileSection
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
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToProfileNoticeListScreen: (Int?) -> Unit,
) {
    ProfileScreen(
        moveToSettingsScreen = moveToSettingsScreen,
        moveToProfileModificationScreen = moveToProfileModificationScreen,
        moveToSignInScreen = moveToSignInScreen,
        moveToTypeTestScreen = moveToTypeTestScreen,
        moveToTypeTestResultScreen = moveToTypeTestResultScreen,
        moveToProfileReviewListScreen = moveToProfileReviewListScreen,
        moveToProfileNoticeListScreen = moveToProfileNoticeListScreen,

        userId = null,
        onBackButtonClicked ={},
    )
}
/** 타인 프로필 **/
@Composable
fun ProfileScreen(
    userId: Int,
    moveToTypeTestResultScreen: () -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
) {
    ProfileScreen(
        userId = userId,
        moveToTypeTestResultScreen = moveToTypeTestResultScreen,
        moveToProfileReviewListScreen = moveToProfileReviewListScreen,

        onBackButtonClicked = {},
        moveToSettingsScreen = {},
        moveToProfileModificationScreen = {_,_,_ -> },
        moveToSignInScreen = {},
        moveToTypeTestScreen = {},
        moveToProfileNoticeListScreen = {},
    )
}

@Composable
private fun ProfileScreen(
    userId: Int?,
    onBackButtonClicked: () -> Unit,
    moveToSettingsScreen: () -> Unit,
    moveToProfileModificationScreen: (String?, String?, String?) -> Unit,
    moveToSignInScreen: () -> Unit,
    moveToTypeTestScreen: () -> Unit,
    moveToTypeTestResultScreen: () -> Unit,
    moveToProfileReviewListScreen: (Int?) -> Unit,
    moveToProfileNoticeListScreen: (Int?) -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val isMine = userId == null
    val userProfile = viewModel.userProfile.collectAsState()
    val reviews = viewModel.reviews.collectAsState()
    val notices = viewModel.notices.collectAsState()

    var reportDialog by remember { mutableStateOf(false) }


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
                    else reportDialog = true
                },
            painter = painterResource(
                if(isMine) R.drawable.ic_gear_outlined
                else R.drawable.ic_more_vert
            ),
            contentDescription = null,
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(getColor().main10)
            .verticalScroll(rememberScrollState())
    ) {
        when (val up = userProfile.value) {
            is UiState.Loading -> {}
            is UiState.Success -> {
                Spacer(Modifier.height(24.dp))

                ProfileScreenProfileSection(
                    profile = up.data,
                    isMine = true,
                    moveToSignInScreen = moveToSignInScreen,
                    moveToProfileModificationScreen = {
                        moveToProfileModificationScreen(
                            up.data.profileImageUrl,
                            up.data.nickname,
                            up.data.description
                        )
                    },
                    moveToTypeTestScreen = moveToTypeTestScreen,
                    moveToTypeTestResultScreen = moveToTypeTestResultScreen
                )

                Spacer(Modifier.height(24.dp))

                ProfileScreenListSection(
                    reviews = reviews.value,
                    notices = notices.value,
                    moveToReviewWriteScreen = { /*TODO*/ },
                    moveToReviewScreen = moveToProfileReviewListScreen,
                    moveToNoticeScreen = moveToProfileNoticeListScreen
                )

                Spacer(modifier = Modifier
                    .fillMaxSize()
                    .background(getColor().white))

            }
            is UiState.Failure -> {}
        }
    }
}
