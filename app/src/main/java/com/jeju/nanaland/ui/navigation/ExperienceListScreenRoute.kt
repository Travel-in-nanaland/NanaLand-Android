package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_LIST
import com.jeju.nanaland.ui.experience.ExperienceListScreen

fun NavGraphBuilder.experienceListScreen(navController: NavController) = composable(route = ROUTE_EXPERIENCE_LIST) {
    ExperienceListScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToExperienceContentScreen = {  },
        moveToSignInScreen = {  }
    )
}