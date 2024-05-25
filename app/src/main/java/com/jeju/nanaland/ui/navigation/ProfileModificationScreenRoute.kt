package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_PROFILE_MODIFICATION
import com.jeju.nanaland.ui.main.mypage.profilemodification.ProfileModificationScreen

fun NavGraphBuilder.profileModificationScreen(navController: NavController) = composable(route = ROUTE_PROFILE_MODIFICATION) {
    ProfileModificationScreen()
}