package com.jeju.nanaland.ui.profile

import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_PROFILE_REVIEW
import com.jeju.nanaland.globalvalue.constant.ROUTE_REPORT
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_ROUTE
import com.jeju.nanaland.ui.profile.profileReviewList.ProfileReviewListScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.profileReviewScreenRoute(
    navController: NavController
) = composable(route = ROUTE_PROFILE_REVIEW) {
    val parentEntry = remember(it) { navController.previousBackStackEntry!! }
    val id = it.arguments?.getInt("id", -1) ?: -1

    ProfileReviewListScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToReviewReportScreen = { navController.navigate(ROUTE_REPORT, bundleOf("reviewId" to it)) },
        moveToReviewEditScreen = { id, category ->
            navController.navigate(ROUTE_REVIEW_WRITE_ROUTE, bundleOf(
                "id" to id, "category" to category.toString(), "isEdit" to true
            ))
        },
        initialScrollToItemId = id,
        viewModel = hiltViewModel(parentEntry)
    )
}