package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_RESTAURANT_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_RESTAURANT_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.ui.restaurant.RestaurantListScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.restaurantListScreen(navController: NavController) = composable(route = ROUTE_RESTAURANT_LIST) {
    RestaurantListScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToRestaurantContentScreen = { contentId ->
            val bundle = bundleOf(
                "contentId" to contentId
            )
            navController.navigate(ROUTE_RESTAURANT_CONTENT, bundle)
        },
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } }
    )
}