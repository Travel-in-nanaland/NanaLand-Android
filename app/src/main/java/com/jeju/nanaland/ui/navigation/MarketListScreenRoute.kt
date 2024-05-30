package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.ui.market.MarketListScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.marketListScreen(navController: NavController) = composable(route = ROUTE_MARKET_LIST) {
    MarketListScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToMarketContentScreen = { contentId ->
            val bundle = bundleOf(
                "contentId" to contentId
            )
            navController.navigate(ROUTE_MARKET_CONTENT, bundle)
        },
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } }
    )
}