package com.jeju.nanaland.ui.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_PROFILE_REVIEW
import com.jeju.nanaland.ui.profile.screen.ProfileReviewListScreen

fun NavGraphBuilder.profileReviewScreenRoute(
    navController: NavController
) = composable(route = ROUTE_PROFILE_REVIEW) {
    val parentEntry = remember(it) { navController.previousBackStackEntry!! }
    val id = it.arguments?.getInt("id")
    ProfileReviewListScreen(if(id == -1) null else id, hiltViewModel(parentEntry))
}