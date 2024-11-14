package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.withdrawal.WithdrawalScreen

fun NavGraphBuilder.withdrawalScreen(navViewModel: NavViewModel) = composable<ROUTE.Main.Profile.Setting.Withdraw> {
    WithdrawalScreen(
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToLanguageInitScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.LanguageSetting, ROUTE.Main) }
    )
}