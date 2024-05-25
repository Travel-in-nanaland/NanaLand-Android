package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_SELECTION
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.ui.languageselection.LanguageSelectionScreen

fun NavGraphBuilder.languageSelectionScreen(navController: NavController) = composable(route = ROUTE_LANGUAGE_SELECTION) {
    LanguageSelectionScreen(
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) { launchSingleTop = true } }
    )
}