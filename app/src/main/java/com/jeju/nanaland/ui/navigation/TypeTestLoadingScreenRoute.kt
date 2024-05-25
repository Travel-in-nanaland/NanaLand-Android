package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_LOADING
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_RESULT
import com.jeju.nanaland.ui.typetest.TypeTestLoadingScreen

fun NavGraphBuilder.typeTestLoadingScreen(navController: NavController) = composable(route = ROUTE_TYPE_TEST_LOADING) {
    TypeTestLoadingScreen(
        moveToTypeTestResultScreen = { navController.navigate(ROUTE_TYPE_TEST_RESULT) {
            popUpTo(ROUTE_TYPE_TEST_LOADING) { inclusive = true }
            launchSingleTop = true
        } }
    )
}