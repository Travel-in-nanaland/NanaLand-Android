package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_INITIALIZATION
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.ui.languageinitialization.LanguageInitializationScreen

fun NavGraphBuilder.languageInitializationScreen(navController: NavController) = composable(route = ROUTE_LANGUAGE_INITIALIZATION) {
    LanguageInitializationScreen(
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
            popUpTo(ROUTE_LANGUAGE_INITIALIZATION) { inclusive = true }
            launchSingleTop = true
        } }
    )
}