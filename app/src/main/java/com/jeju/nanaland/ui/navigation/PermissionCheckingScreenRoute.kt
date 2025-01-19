package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.permissionchecking.PermissionCheckingScreen

fun NavGraphBuilder.permissionCheckingRoute(navViewModel: NavViewModel) = composable<ROUTE.Main.Profile.Setting.PermissionChecking> {
    PermissionCheckingScreen(
        moveToBackScreen = { navViewModel.popBackStack() }
    )
}