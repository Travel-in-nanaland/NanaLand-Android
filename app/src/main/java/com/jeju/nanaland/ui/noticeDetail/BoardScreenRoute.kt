package com.jeju.nanaland.ui.noticeDetail

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE

fun NavGraphBuilder.noticeDetailScreen(navViewModel: NavViewModel) = composable<ROUTE.Main.Profile.NoticeList.NoticeDetail> {
    NoticeDetailScreen(
        moveToBackScreen = { navViewModel.popBackStack() }
    )
}