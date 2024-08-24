package com.jeju.nanaland.ui.notification

import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.notificationScreen(navController: NavController) = composable<ROUTE.Home.Notification> {
    val context = LocalContext.current

    NotificationScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToNanaPickScreen = {
            val bundle = bundleOf(
                "contentId" to it
            )
            navController.navigate(ROUTE_NANAPICK_CONTENT, bundle)
        },
        moveToNoticeScreen = {
            navController.navigate(ROUTE.NoticeDetail(it))
        },
        moveToFavoriteScreen = {
            Toast.makeText(context, "찜 클릭 이벤트",Toast.LENGTH_SHORT).show()
        },

    )
}