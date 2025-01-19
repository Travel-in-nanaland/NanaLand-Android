package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.settings.policy.PolicySettingScreen

fun NavGraphBuilder.policySettingScreen(navViewModel: NavViewModel) = composable<ROUTE.Main.Profile.Setting.Policy> {
    PolicySettingScreen(
        moveToBackScreen = { navViewModel.popBackStack() }
    )
}