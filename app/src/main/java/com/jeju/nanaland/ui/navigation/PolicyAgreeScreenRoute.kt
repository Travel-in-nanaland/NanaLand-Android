package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE.Splash.SignIn.PolicyAgree
import com.jeju.nanaland.ui.signup.PolicyAgreeScreen

fun NavGraphBuilder.policyAgreeScreen(navViewModel: NavViewModel) = composable<PolicyAgree> {
    val data: PolicyAgree = it.toRoute()

    PolicyAgreeScreen(
        moveToPrivacyPolicyDetailsScreen = { navViewModel.navigate(PolicyAgree.Detail) },
        moveToMarketingPolicyScreen = { navViewModel.navigate(PolicyAgree.Marketing) },
        moveToLocationPolicyScreen = { navViewModel.navigate(PolicyAgree.Location) },
        moveToSignUpProfileSettingScreen = { isPrivacyPolicyAgreed, isMarketingPolicyAgreed, isLocationPolicyAgreed ->
            navViewModel.navigate(
                PolicyAgree.SignUp(
                    data.provider,
                    data.email,
                    data.providerId,
                    isPrivacyPolicyAgreed,
                    isMarketingPolicyAgreed,
                    isLocationPolicyAgreed
                )
            )
        }
    )
}