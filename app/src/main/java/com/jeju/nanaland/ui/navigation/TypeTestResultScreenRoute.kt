package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_RESULT
import com.jeju.nanaland.ui.typetest.TypeTestResultScreen

fun NavGraphBuilder.typeTestResultScreen(navController: NavController) = composable(route = ROUTE_TYPE_TEST_RESULT) {
    TypeTestResultScreen(
        moveToMainScreen = { navController.navigate(ROUTE_MAIN) {
            popUpTo(ROUTE_TYPE_TEST_RESULT) { inclusive = true }
            launchSingleTop = true
        } }
    )
}