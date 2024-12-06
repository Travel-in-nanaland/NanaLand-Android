package com.jeju.nanaland.ui.searchInContent

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE

fun NavGraphBuilder.searchInContentRoute(navViewModel: NavViewModel) = composable<ROUTE.Content.SearchInContent> {
    SearchInContentScreen(
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main()) },
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToCategoryContentScreen = { contentId, category, isSearch ->
            when (category) {
                "NATURE" ->  { navViewModel.navigate(ROUTE.Content.Nature.Detail(contentId, isSearch)) }
                "FESTIVAL" ->  { navViewModel.navigate(ROUTE.Content.Festival.Detail(contentId, isSearch)) }
                "MARKET" ->  { navViewModel.navigate(ROUTE.Content.Market.Detail(contentId, isSearch)) }
                "EXPERIENCE" ->  { navViewModel.navigate(ROUTE.Content.Experience.Detail(false, contentId, isSearch)) }
                "RESTAURANT" ->  { navViewModel.navigate(ROUTE.Content.Restaurant.Detail(contentId, isSearch)) }
                else  ->  { navViewModel.navigate(ROUTE.Main.NanaPick.Detail(contentId)) }
            }
        }
    )
}