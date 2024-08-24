package com.jeju.nanaland.ui.noticeDetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE

fun NavGraphBuilder.noticeDetailScreen(navController: NavController) = composable<ROUTE.NoticeDetail> {
    NoticeDetailScreen(
        moveToBackScreen = { navController.popBackStack() }
    )
}