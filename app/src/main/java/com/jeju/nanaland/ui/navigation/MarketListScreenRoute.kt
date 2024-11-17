package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.market.MarketListScreen

fun NavGraphBuilder.marketListScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Market> {
    MarketListScreen(
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToMarketContentScreen = { contentId ->
            navViewModel.navigate(ROUTE.Content.Market.Detail(contentId))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main) }
    )
}