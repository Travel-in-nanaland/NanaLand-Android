package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKETING_POLICY_DETAILS
import com.jeju.nanaland.ui.signup.MarketingPolicyDetailsScreen

fun NavGraphBuilder.marketingPolicyDetailsScreen(navController: NavController) = composable(route = ROUTE_MARKETING_POLICY_DETAILS) {
    MarketingPolicyDetailsScreen()
}