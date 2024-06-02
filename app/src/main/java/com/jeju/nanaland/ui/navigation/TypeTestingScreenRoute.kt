package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TESTING
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_COMPLETION
import com.jeju.nanaland.ui.typetest.TypeTestingScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.typeTestingScreen(navController: NavController) = composable(route = ROUTE_TYPE_TESTING) {
    TypeTestingScreen(
        moveToTypeTestCompletionScreen = { travelType ->
            val bundle = bundleOf(
                "travelType" to travelType
            )
            navController.popBackStack(ROUTE_TYPE_TESTING, true)
            navController.navigate(ROUTE_TYPE_TEST_COMPLETION, bundle)
        },
        moveToMainScreen = { navController.navigate(ROUTE_MAIN) {
            popUpTo(ROUTE_TYPE_TESTING) { inclusive = true }
            launchSingleTop = true
        } }
    )
}