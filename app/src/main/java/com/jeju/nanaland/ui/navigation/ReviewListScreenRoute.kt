package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_LIST
import com.jeju.nanaland.ui.review.ReviewListScreen

fun NavGraphBuilder.reviewListScreen(navController: NavController) = composable(route = ROUTE_REVIEW_LIST) {
    ReviewListScreen(
        contentId = it.arguments?.getInt("contentId"),
        category = it.arguments?.getString("category"),
        moveToBackScreen = { navController.popBackStack() }
    )
}