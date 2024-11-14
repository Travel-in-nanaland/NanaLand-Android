package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.signup.LocationPolicyDetailsScreen

fun NavGraphBuilder.locationPolicyDetailsScreen() = composable<ROUTE.Splash.SignIn.PolicyAgree.Location> {
    LocationPolicyDetailsScreen()
}