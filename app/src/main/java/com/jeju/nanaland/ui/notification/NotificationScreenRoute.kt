package com.jeju.nanaland.ui.notification

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.entity.notification.NotificationType
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_FESTIVAL_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NATURE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_RESTAURANT_CONTENT
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.notificationScreen(navController: NavController) = composable<ROUTE.Home.Notification> {
    NotificationScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToContentScreen = { id, category ->
            val bundle = bundleOf(
                "contentId" to id
            )
            when(category) {
                NotificationType.NATURE -> navController.navigate(ROUTE_NATURE_CONTENT, bundle)
                NotificationType.FESTIVAL -> navController.navigate(ROUTE_FESTIVAL_CONTENT, bundle)
                NotificationType.MARKET -> navController.navigate(ROUTE_MARKET_CONTENT, bundle)
                NotificationType.EXPERIENCE -> navController.navigate(ROUTE_EXPERIENCE_CONTENT, bundle)
                NotificationType.NANA -> navController.navigate(ROUTE_NANAPICK_CONTENT, bundle)
                NotificationType.RESTAURANT -> navController.navigate(ROUTE_RESTAURANT_CONTENT, bundle)
                else -> {}
            }

        },
        moveToNoticeScreen = {
            navController.navigate(ROUTE.NoticeDetail(it))
        },
    )
}
