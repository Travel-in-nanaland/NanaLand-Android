package com.jeju.nanaland.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_PROFILE
import com.jeju.nanaland.globalvalue.constant.ROUTE_PROFILE_REVIEW
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_RESULT
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.profile.screen.ProfileScreen
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.profileScreenRoute(navController: NavController) = composable(route = ROUTE_PROFILE,
) {
    if(it.arguments!!.getInt("userId", -1) == -1) throw Exception("No Arg")

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
                        navController.navigate(ROUTE_PROFILE_REVIEW, bundleOf("id" to it))
                    },
                )
            }
        }
    }
}