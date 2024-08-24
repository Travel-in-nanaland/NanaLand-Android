package com.jeju.nanaland.ui.noticeDetail

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE

//TODO("백엔드에서 타입 API 추가시 변경")
enum class TEMP_BoardType {
    Notice, Issue, Like, Other
}
fun NavGraphBuilder.noticeDetailScreen(navController: NavController) = composable<ROUTE.NoticeDetail> {
//    val detailType = TEMP_BoardType.valueOf(it.arguments?.getString("type") ?: throw Exception("require type arg"))
//    val id = it.arguments?.getInt("id", -1).takeIf { it != -1 } ?: throw Exception("require id arg")

    NoticeDetailScreen(
        moveToBackScreen = { navController.popBackStack() }
    )
}