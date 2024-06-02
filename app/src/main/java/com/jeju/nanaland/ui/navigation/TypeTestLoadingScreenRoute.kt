package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_COMPLETION
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_LOADING
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_RESULT
import com.jeju.nanaland.ui.typetest.TypeTestLoadingScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.typeTestLoadingScreen(navController: NavController) = composable(route = ROUTE_TYPE_TEST_LOADING) {
    TypeTestLoadingScreen(
        moveToTypeTestResultScreen = {
            val bundle = bundleOf(
                "travelType" to (it.arguments?.getString("travelType") ?: "")
            )
            navController.popBackStack(ROUTE_TYPE_TEST_LOADING, true)
            navController.navigate(ROUTE_TYPE_TEST_RESULT, bundle)
        }
    )
}