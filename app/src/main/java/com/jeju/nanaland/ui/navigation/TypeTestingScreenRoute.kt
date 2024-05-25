package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TESTING
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_COMPLETION
import com.jeju.nanaland.ui.typetest.TypeTestingScreen

fun NavGraphBuilder.typeTestingScreen(navController: NavController) = composable(route = ROUTE_TYPE_TESTING) {
    TypeTestingScreen(
        moveToTypeTestCompletionScreen = { navController.navigate(ROUTE_TYPE_TEST_COMPLETION) {
            popUpTo(ROUTE_TYPE_TESTING) { inclusive = true }
            launchSingleTop = true
        } },
        moveToMainScreen = { navController.navigate(ROUTE_MAIN) {
            popUpTo(ROUTE_TYPE_TESTING) { inclusive = true }
            launchSingleTop = true
        } }
    )
}