package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_ALL_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
import com.jeju.nanaland.ui.nanapick.NanaPickAllListScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.nanapickAllListScreen(navController: NavController) = composable(route = ROUTE_NANAPICK_ALL_LIST) {
    NanaPickAllListScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToNanaPickContentScreen = { contentId ->
            val bundle = bundleOf(
                "contentId" to contentId
            )
            navController.navigate(ROUTE_NANAPICK_CONTENT, bundle)
        }
    )
}