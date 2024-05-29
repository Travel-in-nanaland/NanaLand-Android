package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_POLICY_SETTING
import com.jeju.nanaland.globalvalue.constant.ROUTE_SETTINGS
import com.jeju.nanaland.ui.settings.SettingsScreen

fun NavGraphBuilder.settingsScreen(navController: NavController) = composable(route = ROUTE_SETTINGS) {
    SettingsScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToPolicySettingScreen = { navController.navigate(ROUTE_POLICY_SETTING) { launchSingleTop = true } }
    )
}