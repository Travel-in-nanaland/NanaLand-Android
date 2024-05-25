package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_LOCATION_POLICY_DETAILS
import com.jeju.nanaland.ui.signup.LocationPolicyDetailsScreen

fun NavGraphBuilder.locationPolicyDetailsScreen(navController: NavController) = composable(route = ROUTE_LOCATION_POLICY_DETAILS) {
    LocationPolicyDetailsScreen()
}