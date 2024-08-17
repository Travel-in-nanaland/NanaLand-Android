package com.jeju.nanaland.ui.navigation

import androidx.compose.material3.Text
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_BOARD
import com.jeju.nanaland.ui.board.screen.NoticeDetailScreen

//TODO("백엔드에서 타입 API 추가시 변경")
enum class TEMP_BoardType {
    Notice, Issue, Like, Other
}
fun NavGraphBuilder.boardScreen(navController: NavController) = composable(route = ROUTE_BOARD) {
    val detailType = TEMP_BoardType.valueOf(it.arguments?.getString("type") ?: throw Exception("require type arg"))
    val id = it.arguments?.getInt("id", -1).takeIf { it != -1 } ?: throw Exception("require id arg")


    when(detailType) {
        TEMP_BoardType.Notice -> NoticeDetailScreen(
            moveToBackScreen = { navController.popBackStack() }
        )
        else -> Text(text = "TODO")
    }
}