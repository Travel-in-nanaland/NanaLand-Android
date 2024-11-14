package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.signin.SignInScreen
import com.jeju.nanaland.util.intent.DeepLinkData

fun NavGraphBuilder.signInScreen(
    deepLinkData: DeepLinkData,
    navViewModel: NavViewModel
) = composable<ROUTE.Splash.SignIn> {
    SignInScreen(
        deepLinkData = deepLinkData,
        moveToMainScreen = { navViewModel.navigatePopUpTo(ROUTE.Main, ROUTE.Splash.SignIn) },
        moveToSignUpScreen = { provider, email, id ->
            navViewModel.navigate(ROUTE.Splash.SignIn.PolicyAgree(provider, email, id))
        }
    )
}