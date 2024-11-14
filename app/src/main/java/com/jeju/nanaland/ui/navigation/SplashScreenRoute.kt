package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.splash.SplashScreen
import com.jeju.nanaland.util.intent.DeepLinkData

fun NavGraphBuilder.splashScreen(
    deepLinkData: DeepLinkData,
    navViewModel: NavViewModel
) = composable<ROUTE.Splash> {
    SplashScreen(
        deepLinkData = deepLinkData,
        moveToMainScreen = {
            navViewModel.navigatePopUpTo(ROUTE.Main, ROUTE.Splash)
        },
        moveToLanguageInitScreen = {
            navViewModel.navigatePopUpTo(ROUTE.Splash.LanguageSetting, ROUTE.Splash)
        },
        moveToSignInScreen = {
            navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Splash)
        }
    )
}