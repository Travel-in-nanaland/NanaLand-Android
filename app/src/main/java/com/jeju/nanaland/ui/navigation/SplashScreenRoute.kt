package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_SELECTION
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.globalvalue.constant.ROUTE_SPLASH
import com.jeju.nanaland.ui.splash.SplashScreen

fun NavGraphBuilder.splashScreen(navController: NavController) = composable(route = ROUTE_SPLASH) {
    SplashScreen(
        moveToMainScreen = {
            navController.navigate(ROUTE_MAIN) {
                popUpTo(ROUTE_SPLASH) { inclusive = true }
                launchSingleTop = true
            }
        },
        moveToLanguageSelectionScreen = {
            navController.navigate(ROUTE_LANGUAGE_SELECTION) {
                popUpTo(ROUTE_SPLASH) { inclusive = true }
                launchSingleTop = true
            }
        },
        moveToSignInScreen = {
            navController.navigate(ROUTE_SIGN_IN) {
                popUpTo(ROUTE_SPLASH) { inclusive = true }
                launchSingleTop = true
            }
        }
    )
}