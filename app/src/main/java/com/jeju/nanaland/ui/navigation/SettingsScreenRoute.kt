package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.settings.SettingsScreen

fun NavGraphBuilder.settingsScreen(navViewModel: NavViewModel) = composable<ROUTE.Main.Profile.Setting>{
    SettingsScreen(
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToPolicySettingScreen = { navViewModel.navigate(ROUTE.Main.Profile.Setting.Policy) },
        moveToPermissionCheckingScreen = { navViewModel.navigate(ROUTE.Main.Profile.Setting.PermissionChecking) },
        moveToLanguageChangeScreen = { navViewModel.navigate(ROUTE.Main.Profile.Setting.LanguageChange) },
        moveToWithdrawalScreen = { navViewModel.navigate(ROUTE.Main.Profile.Setting.Withdraw) },
        moveToNoticeScreen = { navViewModel.navigate(ROUTE.Main.Profile.NoticeList) },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main) }
    )
}