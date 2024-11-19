package com.jeju.nanaland.ui.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.reviewwrite.ReviewWriteViewModel
import com.jeju.nanaland.ui.reviewwrite.screen.ReviewWriteCompleteScreen
import com.jeju.nanaland.ui.reviewwrite.screen.ReviewWriteKeywordScreen
import com.jeju.nanaland.ui.reviewwrite.screen.ReviewWriteScreen
import com.jeju.nanaland.ui.reviewwrite.screen.ReviewWriteSearchScreen

fun NavGraphBuilder.reviewWriteRoute(
    navViewModel: NavViewModel,
    getBackStackEntry: (Any) -> NavBackStackEntry
) {
    navigation<ROUTE.Content.ReviewWrite>(
        startDestination = ROUTE.Content.ReviewWrite.StartDest()
    ) {
        composable<ROUTE.Content.ReviewWrite.StartDest> {
            val parentEntry = remember(it) { getBackStackEntry(ROUTE.Content.ReviewWrite) }
            val viewModel:ReviewWriteViewModel = hiltViewModel(parentEntry)
            val data: ROUTE.Content.ReviewWrite.StartDest = it.toRoute()

            LaunchedEffect(Unit) {
                val id = data.id
                val category = data.category?.let { ReviewCategoryType.valueOf(it) }
                val isEdit = data.isEdit
                viewModel.init(id, category, isEdit)

                if(id == null && category == null) // Move To Review Search
                    navViewModel.navigatePopUpTo(ROUTE.Content.ReviewWrite.Search, data)
                else if(id != null && category != null) {
                    navViewModel.navigatePopUpTo(
                        ROUTE.Content.ReviewWrite.Write(id, category.toString()),
                        data
                    )
                }
                else
                    throw Exception("require arg")
            }
        }

        composable<ROUTE.Content.ReviewWrite.Search> {
            val parentEntry = remember(it) { getBackStackEntry(ROUTE.Content.ReviewWrite) }
            ReviewWriteSearchScreen(
                navViewModel = navViewModel,
                viewModel = hiltViewModel(parentEntry)
            )
        }
        composable<ROUTE.Content.ReviewWrite.Write> {
            val parentEntry = remember(it) { getBackStackEntry(ROUTE.Content.ReviewWrite) }
            val data: ROUTE.Content.ReviewWrite.Write = it.toRoute()
            ReviewWriteScreen(
                navViewModel = navViewModel,
                id = data.id,
                category = ReviewCategoryType.valueOf(data.category),
                viewModel = hiltViewModel(parentEntry)
            )
        }

        composable<ROUTE.Content.ReviewWrite.Keyword>{
            val parentEntry = remember(it) { getBackStackEntry(ROUTE.Content.ReviewWrite) }
            ReviewWriteKeywordScreen(navViewModel, hiltViewModel(parentEntry))
        }

        composable<ROUTE.Content.ReviewWrite.Complete>{
            val data: ROUTE.Content.ReviewWrite.Complete = it.toRoute()
            ReviewWriteCompleteScreen(
                navViewModel,
                ReviewCategoryType.valueOf(data.category)
            )
        }
    }
}