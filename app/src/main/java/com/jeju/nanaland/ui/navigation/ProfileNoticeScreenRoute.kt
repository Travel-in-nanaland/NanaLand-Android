package com.jeju.nanaland.ui.navigation

import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_PROFILE_NOTICE
import com.jeju.nanaland.ui.profile.screen.ProfileNoticeListScreen

fun NavGraphBuilder.profileNoticeScreenRoute(
    navController: NavController,
) = composable(route = ROUTE_PROFILE_NOTICE) {
    val parentEntry = remember(it) { navController.previousBackStackEntry!! }

    ProfileNoticeListScreen(
        moveToBackScreen = { navController.popBackStack() },
        viewModel = hiltViewModel(parentEntry)
    )
}