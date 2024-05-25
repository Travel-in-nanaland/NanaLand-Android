package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_FESTIVAL_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_FESTIVAL_LIST
import com.jeju.nanaland.ui.festival.FestivalListScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.festivalListScreen(navController: NavController) = composable(route = ROUTE_FESTIVAL_LIST) {
    FestivalListScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToFestivalContentScreen = { contentId ->
            val bundle = bundleOf(
                "contentId" to contentId
            )
            navController.navigate(ROUTE_FESTIVAL_CONTENT, bundle)
        }
    )
}