package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_LOCATION_POLICY_DETAILS
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKETING_POLICY_DETAILS
import com.jeju.nanaland.globalvalue.constant.ROUTE_POLICY_AGREE
import com.jeju.nanaland.globalvalue.constant.ROUTE_PRIVACY_POLICY_DETAILS
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_UP
import com.jeju.nanaland.ui.signup.PolicyAgreeScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.policyAgreeScreen(navController: NavController) = composable(route = ROUTE_POLICY_AGREE) {
    PolicyAgreeScreen(
        moveToPrivacyPolicyDetailsScreen = { navController.navigate(ROUTE_PRIVACY_POLICY_DETAILS) {
            launchSingleTop = true
        } },
        moveToMarketingPolicyScreen = { navController.navigate(ROUTE_MARKETING_POLICY_DETAILS) {
            launchSingleTop = true
        } },
        moveToLocationPolicyScreen = { navController.navigate(ROUTE_LOCATION_POLICY_DETAILS) {
            launchSingleTop = true
        } },
        moveToSignUpProfileSettingScreen = { isPrivacyPolicyAgreed, isMarketingPolicyAgreed, isLocationPolicyAgreed ->
            val bundle = bundleOf(
                "provider" to (it.arguments?.getString("provider") ?: ""),
                "email" to (it.arguments?.getString("email") ?: ""),
                "providerId" to (it.arguments?.getString("providerId") ?: ""),
                "isPrivacyPolicyAgreed" to isPrivacyPolicyAgreed,
                "isMarketingPolicyAgreed" to isMarketingPolicyAgreed,
                "isLocationPolicyAgreed" to isLocationPolicyAgreed
            )
            navController.navigate(ROUTE_SIGN_UP, bundle)
        }
    )
}