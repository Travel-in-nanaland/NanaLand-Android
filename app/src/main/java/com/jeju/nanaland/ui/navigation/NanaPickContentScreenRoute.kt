package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.nanapick.NanaPickContentScreen

fun NavGraphBuilder.nanaPickContentScreen(navViewModel: NavViewModel) = composable< ROUTE.Main.NanaPick.Detail> {
    NanaPickContentScreen(
        contentId = it.arguments?.getInt("contentId"),
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main()) },
        moveToMap = { navViewModel.navigate(it) }
    )
}