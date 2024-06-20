package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NOTIFICATION
import com.jeju.nanaland.ui.experience.ExperienceContentScreen
import com.jeju.nanaland.ui.notification.NotificationScreen

fun NavGraphBuilder.notificationScreen(navController: NavController) = composable(route = ROUTE_NOTIFICATION) {
    NotificationScreen(
        moveToBackScreen = { navController.popBackStack() }
    )
}