package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_PRIVACY_POLICY_DETAILS
import com.jeju.nanaland.ui.signup.PrivacyPolicyDetailsScreen

fun NavGraphBuilder.privacyPolicyDetailsScreen(navController: NavController) = composable(route = ROUTE_PRIVACY_POLICY_DETAILS) {
    PrivacyPolicyDetailsScreen()
}