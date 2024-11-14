package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.nature.NatureListScreen

fun NavGraphBuilder.natureListScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Nature> {
    val data: ROUTE.Content.Nature = it.toRoute()

    NatureListScreen(
        filter = data.filter,
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToNatureContentScreen = { contentId ->
            navViewModel.navigate(ROUTE.Content.Nature.Detail(contentId))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main) }
    )
}