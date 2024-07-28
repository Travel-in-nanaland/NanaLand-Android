package com.jeju.nanaland.ui.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_COMPLETE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_KEYWORD
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_ROUTE
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteCompleteScreen
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteKeywordScreen
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteScreen

fun NavGraphBuilder.reviewWriteRoute(navController: NavController) {
    navigation(
        route = ROUTE_REVIEW_WRITE_ROUTE,
        startDestination = ROUTE_REVIEW_WRITE
    ) {
        composable(ROUTE_REVIEW_WRITE){
            val parentEntry = remember(it) { navController.getBackStackEntry(ROUTE_REVIEW_WRITE_ROUTE) }
            ReviewWriteScreen(
                navController = navController,
                id = it.arguments?.getInt("id")!!,
                category = ReviewCategoryType.valueOf(it.arguments?.getString("category")!!),
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable(ROUTE_REVIEW_WRITE_KEYWORD){
            val parentEntry = remember(it) { navController.getBackStackEntry(ROUTE_REVIEW_WRITE_ROUTE) }
            ReviewWriteKeywordScreen(navController, hiltViewModel(parentEntry))
        }

        composable(ROUTE_REVIEW_WRITE_COMPLETE){
            ReviewWriteCompleteScreen(
                navController,
                ReviewCategoryType.valueOf(it.arguments?.getString("category")!!)
            )
        }
    }
}