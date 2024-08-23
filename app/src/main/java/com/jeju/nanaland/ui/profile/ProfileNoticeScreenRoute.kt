package com.jeju.nanaland.ui.profile

import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_BOARD
import com.jeju.nanaland.globalvalue.constant.ROUTE_PROFILE_NOTICE
import com.jeju.nanaland.ui.navigation.TEMP_BoardType
import com.jeju.nanaland.ui.profile.profileNoticeList.ProfileNoticeListScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.profileNoticeScreenRoute(
    navController: NavController,
) = composable(route = ROUTE_PROFILE_NOTICE) {
    val parentEntry = remember(it) { navController.previousBackStackEntry!! }

    ProfileNoticeListScreen(
        moveToProfileNoticeListScreen = {
            navController.navigate(
                ROUTE_BOARD, bundleOf(
                    "type" to TEMP_BoardType.Notice.toString(),
                    "id" to it
                )
            )
        },
        moveToBackScreen = { navController.popBackStack() },
        viewModel = hiltViewModel(parentEntry)
    )
}