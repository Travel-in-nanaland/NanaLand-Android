package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_PERMISSION_CHECKING
import com.jeju.nanaland.ui.permissionchecking.PermissionCheckingScreen

fun NavGraphBuilder.permissionCheckingRoute(navController: NavController) = composable(route = ROUTE_PERMISSION_CHECKING) {
    PermissionCheckingScreen(
        moveToBackScreen = { navController.popBackStack() }
    )
}