package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TESTING
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_COMPLETION
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_LOADING
import com.jeju.nanaland.ui.typetest.TypeTestCompletionScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.typeTestCompletionScreen(navController: NavController) = composable(route = ROUTE_TYPE_TEST_COMPLETION) {
    TypeTestCompletionScreen(
        moveToTypeTestLoadingScreen = {
            val bundle = bundleOf(
                "travelType" to (it.arguments?.getString("travelType") ?: "")
            )
            navController.popBackStack(ROUTE_TYPE_TEST_COMPLETION, true)
            navController.navigate(ROUTE_TYPE_TEST_LOADING, bundle)
        }
    )
}