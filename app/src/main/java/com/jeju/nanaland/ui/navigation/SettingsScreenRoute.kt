package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_CHANGE
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_INITIALIZATION
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_PERMISSION_CHECKING
import com.jeju.nanaland.globalvalue.constant.ROUTE_POLICY_SETTING
import com.jeju.nanaland.globalvalue.constant.ROUTE_SETTINGS
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.globalvalue.constant.ROUTE_WITHDRAW
import com.jeju.nanaland.ui.settings.SettingsScreen

fun NavGraphBuilder.settingsScreen(navController: NavController) = composable(route = ROUTE_SETTINGS) {
    SettingsScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToPolicySettingScreen = { navController.navigate(ROUTE_POLICY_SETTING) { launchSingleTop = true } },
        moveToPermissionCheckingScreen = { navController.navigate(ROUTE_PERMISSION_CHECKING) { launchSingleTop = true } },
        moveToLanguageChangeScreen = { navController.navigate(ROUTE_LANGUAGE_CHANGE) { launchSingleTop = true } },
        moveToWithdrawalScreen = { navController.navigate(ROUTE_WITHDRAW) { launchSingleTop = true } },
        moveToLanguageInitScreen = { navController.navigate(ROUTE_LANGUAGE_INITIALIZATION) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } },
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } }
    )
}