package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_UP
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TESTING
import com.jeju.nanaland.ui.signup.SignUpScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.signUpScreen(navController: NavController) = composable(route = ROUTE_SIGN_UP) {
    SignUpScreen(
        provider = it.arguments?.getString("provider") ?: "",
        email = it.arguments?.getString("email") ?: "",
        providerId = it.arguments?.getString("providerId") ?: "",
        isPrivacyPolicyAgreed = it.arguments?.getBoolean("isPrivacyPolicyAgreed") ?: true,
        isMarketingPolicyAgreed = it.arguments?.getBoolean("isMarketingPolicyAgreed") ?: false,
        isLocationPolicyAgreed = it.arguments?.getBoolean("isLocationPolicyAgreed") ?: false,
        moveToTypeTestingScreen = {
            navController.navigate(
                route = ROUTE_TYPE_TESTING,
                args = bundleOf("isFirst" to true),
                navOptions = navOptions {
                    popUpTo(ROUTE_SIGN_IN) { inclusive = true }
                    launchSingleTop = true
                }
            )
        }
    )
}