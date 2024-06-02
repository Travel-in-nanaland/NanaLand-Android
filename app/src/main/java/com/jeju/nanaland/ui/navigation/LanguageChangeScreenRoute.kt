package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_CHANGE
import com.jeju.nanaland.ui.experience.ExperienceContentScreen
import com.jeju.nanaland.ui.languagechange.LanguageChangeScreen

fun NavGraphBuilder.languageChangeScreen(navController: NavController) = composable(route = ROUTE_LANGUAGE_CHANGE) {
    LanguageChangeScreen(
        moveToBackScreen = { navController.popBackStack() }
    )
}