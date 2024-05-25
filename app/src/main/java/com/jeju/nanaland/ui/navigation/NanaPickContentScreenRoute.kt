package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
import com.jeju.nanaland.ui.nanapick.NanaPickContentScreen

fun NavGraphBuilder.nanaPickContentScreen(navController: NavController) = composable(route = ROUTE_NANAPICK_CONTENT) {
    NanaPickContentScreen(
        contentId = it.arguments?.getLong("contentId"),
        moveToBackScreen = { navController.popBackStack() }
    )
}