package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.ui.nanapick.NanaPickContentScreen

fun NavGraphBuilder.nanaPickContentScreen(navController: NavController) = composable(route = ROUTE_NANAPICK_CONTENT) {
    NanaPickContentScreen(
        contentId = it.arguments?.getInt("contentId"),
        moveToBackScreen = { navController.popBackStack() },
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } }
    )
}