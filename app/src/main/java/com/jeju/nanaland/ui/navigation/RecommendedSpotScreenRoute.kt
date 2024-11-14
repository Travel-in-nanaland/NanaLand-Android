package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.typetest.recommendedspot.RecommendedSpotScreen

fun NavGraphBuilder.recommendedSpotScreen(navViewModel: NavViewModel) = composable<ROUTE.RecommendedSpot> {
    RecommendedSpotScreen(
        moveToMainScreen = {
            navViewModel.navigatePopUpTo(
                ROUTE.Main,
                ROUTE.TypeTest.Result("",true, true)
            )
       },
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToDetailScreen = { id, category ->
            when(category) {
                "NATURE" -> navViewModel.navigate(ROUTE.Content.Nature.Detail(id))
                "EXPERIENCE" -> navViewModel.navigate(ROUTE.Content.Experience(id))
                "FESTIVAL" -> navViewModel.navigate(ROUTE.Content.Festival.Detail(id))
                "MARKET" -> navViewModel.navigate(ROUTE.Content.Market.Detail(id))
            }
        }

    )
}