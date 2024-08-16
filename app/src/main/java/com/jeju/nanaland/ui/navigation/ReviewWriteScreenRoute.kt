package com.jeju.nanaland.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_COMPLETE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_KEYWORD
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_SEARCH
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteViewModel
import com.jeju.nanaland.ui.reviewwrite.screen.ReviewWriteCompleteScreen
import com.jeju.nanaland.ui.reviewwrite.screen.ReviewWriteKeywordScreen
import com.jeju.nanaland.ui.reviewwrite.screen.ReviewWriteScreen
import com.jeju.nanaland.ui.reviewwrite.screen.ReviewWriteSearchScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.reviewWriteRoute(navController: NavController) {
    val startViewForRouting = ROUTE_REVIEW_WRITE_ROUTE + "routing"
    navigation(
        route = ROUTE_REVIEW_WRITE_ROUTE,
        startDestination = startViewForRouting
    ) {
        composable(startViewForRouting) {
            val parentEntry = remember(it) { navController.getBackStackEntry(ROUTE_REVIEW_WRITE_ROUTE) }
            val viewModel:ReviewWriteViewModel = hiltViewModel(parentEntry)

            LaunchedEffect(Unit) {
                val id = it.arguments?.getInt("id", -1)?.takeIf { it != -1 }
                val category = it.arguments?.getString("category")?.let { ReviewCategoryType.valueOf(it) }
                val isEdit = it.arguments?.getBoolean("isEdit") == true
                viewModel.init(id, category, isEdit)

                val popUpTo = navOptions { popUpTo(startViewForRouting) { inclusive = true } }
                if(id == null && category == null) // Move To Review Search
                    navController.navigate(ROUTE_REVIEW_WRITE_SEARCH, popUpTo)
                else if(id != null && category != null) {
                    navController.navigate(
                        route = ROUTE_REVIEW_WRITE,
                        args = bundleOf("id" to id, "category" to category.toString()),
                        navOptions = popUpTo
                    )
                }
                else
                    throw Exception("require arg")
            }
        }

        composable(ROUTE_REVIEW_WRITE_SEARCH){
            val parentEntry = remember(it) { navController.getBackStackEntry(ROUTE_REVIEW_WRITE_ROUTE) }
            ReviewWriteSearchScreen(
                navController = navController,
                viewModel = hiltViewModel(parentEntry)
            )
        }
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