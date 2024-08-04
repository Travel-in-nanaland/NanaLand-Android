package com.jeju.nanaland.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBarWithMenu
import com.jeju.nanaland.ui.profile.component.ProfileScreenListSection
import com.jeju.nanaland.ui.profile.component.ProfileScreenProfileSection
import com.jeju.nanaland.ui.theme.body01
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReportSheet(
    onDismissRequest: () -> Unit,
    onReport: () -> Unit
) {
    ModalBottomSheet(
        containerColor = getColor().white,
        shape = RoundedCornerShape(12.dp, 12.dp, 0.dp, 0.dp),
        onDismissRequest = onDismissRequest,
        dragHandle = null
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Icon(
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp)
                    .size(28.dp)
                    .align(Alignment.End)
                    .clickableNoEffect { onDismissRequest() },
                painter = painterResource(id = R.drawable.ic_close),
                contentDescription = null
            )

            Text(
                modifier = Modifier
                    .clickableNoEffect { onReport() }
                    .padding(horizontal = 16.dp, vertical = 10.dp),
                text = getString(R.string.common_신고),
                style = body01,
                color = getColor().black
            )
        }
    }
}