package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_INITIALIZATION
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_NATURE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NATURE_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.ui.nature.NatureListScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.natureListScreen(navController: NavController) = composable(route = ROUTE_NATURE_LIST) {
    NatureListScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToNatureContentScreen = { contentId ->
            val bundle = bundleOf(
                "contentId" to contentId
            )
            navController.navigate(ROUTE_NATURE_CONTENT, bundle)
        },
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } }
    )
}