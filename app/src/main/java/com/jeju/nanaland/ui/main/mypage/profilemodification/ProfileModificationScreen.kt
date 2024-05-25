package com.jeju.nanaland.ui.main.mypage.profilemodification

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.CustomTopBar

@Composable
fun ProfileModificationScreen(
    moveToBackScreen: () -> Unit,
    viewModel: ProfileModificationViewModel = hiltViewModel()
) {
    ProfileModificationScreen(
        moveToBackScreen = moveToBackScreen,
        isContent = true
    )
}

@Composable
private fun ProfileModificationScreen(
    moveToBackScreen: () -> Unit,
    isContent: Boolean
) {
    CustomSurface {
        CustomTopBar(
            title = "",
            onBackButtonClicked = {
                moveToBackScreen()
            }
        )
    }
}