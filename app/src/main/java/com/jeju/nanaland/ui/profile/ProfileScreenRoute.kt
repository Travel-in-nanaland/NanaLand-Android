package com.jeju.nanaland.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_REPORT
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_RESULT
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.profile.profileNoticeList.ProfileNoticeListScreen
import com.jeju.nanaland.ui.profile.profileReviewList.ProfileReviewListScreen
import com.jeju.nanaland.ui.profile.profileupdate.ProfileUpdateScreen
import com.jeju.nanaland.ui.profile.root.ProfileScreen
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.profileScreenRoute(navController: NavController) = navigation<ROUTE.Profile>(
    ROUTE.Profile.StartDest(null)
) {
    composable<ROUTE.Profile.StartDest> {
        CustomSurface { isImeKeyboardShowing ->
            Scaffold(
                containerColor = getColor().surface,
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                Column(
                    modifier = Modifier
                        .imePadding()
                        .padding(bottom = if (isImeKeyboardShowing) 0.dp else it.calculateBottomPadding())
                ) {
                    ProfileScreen(
                        onBackButtonClicked = { navController.popBackStack() },
                        moveToTypeTestResultScreen = {
                            navController.navigate(ROUTE_TYPE_TEST_RESULT,  bundleOf("travelType" to it))
                        },
                        moveToProfileReviewListScreen = {
                            navController.navigate(ROUTE.Profile.ReviewList(it))
                        },
                    )
                }
            }
        }
    }

    composable<ROUTE.Profile.Update> {
        val data: ROUTE.Profile.Update= it.toRoute()

        ProfileUpdateScreen(
            profileImageUri = data.profileImageUri,
            nickname = data.nickname,
            introduction = data.introduction,
            moveToBackScreen = { navController.popBackStack() }
        )
    }

    composable<ROUTE.Profile.NoticeList>{
        val parentEntry = remember(it) { navController.previousBackStackEntry!! }

        ProfileNoticeListScreen(
            moveToProfileNoticeListScreen = {
                navController.navigate(ROUTE.NoticeDetail(it))
            },
            moveToBackScreen = { navController.popBackStack() },
            viewModel = hiltViewModel(parentEntry)
        )
    }

    composable<ROUTE.Profile.ReviewList> {
        val data: ROUTE.Profile.ReviewList= it.toRoute()
        val parentEntry = remember(it) { navController.previousBackStackEntry!! }

        ProfileReviewListScreen(
            moveToBackScreen = { navController.popBackStack() },
            moveToReviewReportScreen = {
                navController.navigate(
                    ROUTE_REPORT,
                    bundleOf("reviewId" to it)
                )
            },
            moveToReviewEditScreen = { id, category ->
                navController.navigate(
                    ROUTE_REVIEW_WRITE_ROUTE, bundleOf(
                        "id" to id, "category" to category.toString(), "isEdit" to true
                    )
                )
            },
            initialScrollToItemId = data.initialScrollToItemId ?: -1,
            viewModel = hiltViewModel(parentEntry)
        )
    }
}