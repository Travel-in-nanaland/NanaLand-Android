package com.jeju.nanaland.ui.profile

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_PROFILE_UPDATE
import com.jeju.nanaland.ui.profile.profileupdate.ProfileUpdateScreen

fun NavGraphBuilder.profileUpdateScreen(navController: NavController) = composable(route = ROUTE_PROFILE_UPDATE) {
    ProfileUpdateScreen(
        profileImageUri = it.arguments?.getString("profileImageUri") ?: "",
        nickname = it.arguments?.getString("nickname") ?: "",
        introduction = it.arguments?.getString("introduction") ?: "",
        moveToBackScreen = { navController.popBackStack() }
    )
}