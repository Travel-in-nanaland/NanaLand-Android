package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_POLICY_SETTING
import com.jeju.nanaland.ui.settings.policy.PolicySettingScreen

fun NavGraphBuilder.policySettingScreen(navController: NavController) = composable(route = ROUTE_POLICY_SETTING) {
    PolicySettingScreen(
        moveToBackScreen = { navController.popBackStack() }
    )
}