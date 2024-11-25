package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.festival.FestivalListScreen

fun NavGraphBuilder.festivalListScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Festival> {
    val data: ROUTE.Content.Festival = it.toRoute()

    FestivalListScreen(
        filter = data.filter,
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToFestivalContentScreen = { contentId ->
            navViewModel.navigate(ROUTE.Content.Festival.Detail(contentId))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main()) },
        toHome = { navViewModel.navigatePopUpTo(ROUTE.Main(0), data) },
        toFavorite = { navViewModel.navigatePopUpTo(ROUTE.Main(1), data) },
        toNana = { navViewModel.navigatePopUpTo(ROUTE.Main(2), data) },
        toProfile = { navViewModel.navigatePopUpTo(ROUTE.Main(3), data) },
    )
}