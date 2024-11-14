package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.signup.SignUpScreen

fun NavGraphBuilder.signUpScreen(navViewModel: NavViewModel) = composable<ROUTE.Splash.SignIn.PolicyAgree.SignUp> {
    val data: ROUTE.Splash.SignIn.PolicyAgree.SignUp = it.toRoute()

    SignUpScreen(
        provider = data.provider,
        email = data.email,
        providerId = data.providerId,
        isPrivacyPolicyAgreed = data.isPrivacyPolicyAgreed,
        isMarketingPolicyAgreed = data.isMarketingPolicyAgreed,
        isLocationPolicyAgreed = data.isLocationPolicyAgreed,
        moveToTypeTestingScreen = {
            navViewModel.navigatePopUpTo(ROUTE.TypeTest.Testing(true), ROUTE.Splash.SignIn)
        }
    )
}