package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.languageinitialization.LanguageInitializationScreen

fun NavGraphBuilder.languageInitializationScreen(navViewModel: NavViewModel) = composable<ROUTE.Splash.LanguageSetting> {
    LanguageInitializationScreen(
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Splash) }
    )
}