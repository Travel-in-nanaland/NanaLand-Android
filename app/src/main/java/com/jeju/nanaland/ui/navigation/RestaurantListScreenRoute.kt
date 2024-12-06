package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.restaurant.RestaurantListScreen

fun NavGraphBuilder.restaurantListScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Restaurant> {
    RestaurantListScreen(
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToRestaurantContentScreen = { contentId ->
            navViewModel.navigate(ROUTE.Content.Restaurant.Detail(contentId))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main()) },
        toHome = { navViewModel.navigatePopUpTo(ROUTE.Main(0), ROUTE.Content.Restaurant) },
        toFavorite = { navViewModel.navigatePopUpTo(ROUTE.Main(1), ROUTE.Content.Restaurant) },
        toNana = { navViewModel.navigatePopUpTo(ROUTE.Main(2), ROUTE.Content.Restaurant) },
        toProfile = { navViewModel.navigatePopUpTo(ROUTE.Main(3), ROUTE.Content.Restaurant) },
        moveToSearchScreen = { navViewModel.navigate(ROUTE.Content.SearchInContent(ROUTE.Content.Restaurant.toString())) }
    )
}