package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_FESTIVAL_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NATURE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_RECOMMENDED_SPOT
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_RESULT
import com.jeju.nanaland.ui.typetest.recommendedspot.RecommendedSpotScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.recommendedSpotScreen(navController: NavController) = composable(route = ROUTE_RECOMMENDED_SPOT) {
    RecommendedSpotScreen(
        moveToMainScreen = { navController.navigate(ROUTE_MAIN) {
            popUpTo(ROUTE_TYPE_TEST_RESULT) { inclusive = true }
            launchSingleTop = true
        } },
        moveToBackScreen = { navController.popBackStack() },
        moveToDetailScreen = { id, category ->
            val bundle = bundleOf(
                "contentId" to id
            )
            when(category) {
                "NATURE" -> navController.navigate(ROUTE_NATURE_CONTENT, bundle)
                "EXPERIENCE" -> navController.navigate(ROUTE_EXPERIENCE_CONTENT, bundle)
                "FESTIVAL" -> navController.navigate(ROUTE_FESTIVAL_CONTENT, bundle)
                "MARKET" -> navController.navigate(ROUTE_MARKET_CONTENT, bundle)
            }
        }

    )
}