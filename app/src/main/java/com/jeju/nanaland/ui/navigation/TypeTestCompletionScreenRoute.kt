package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_COMPLETION
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_LOADING
import com.jeju.nanaland.ui.typetest.TypeTestCompletionScreen

fun NavGraphBuilder.typeTestCompletionScreen(navController: NavController) = composable(route = ROUTE_TYPE_TEST_COMPLETION) {
    TypeTestCompletionScreen(
        moveToTypeTestLoadingScreen = { navController.navigate(ROUTE_TYPE_TEST_LOADING) {
            popUpTo(ROUTE_TYPE_TEST_COMPLETION) { inclusive = true }
            launchSingleTop = true
        } }
    )
}