package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_POLICY_AGREE
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.ui.signin.SignInScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.signInScreen(navController: NavController) = composable(route = ROUTE_SIGN_IN) {
    SignInScreen(
        moveToMainScreen = {
            navController.navigate(ROUTE_MAIN) {
                popUpTo(ROUTE_SIGN_IN) { inclusive = true }
                launchSingleTop = true
            }
        },
        moveToSignUpScreen = { provider, email, id ->
            val bundle = bundleOf(
                "provider" to provider,
                "email" to email,
                "providerId" to id
            )
            navController.navigate(ROUTE_POLICY_AGREE, bundle)
        }
    )
}