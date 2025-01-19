package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.typetest.recommendedspot.RecommendedSpotScreen

fun NavGraphBuilder.recommendedSpotScreen(navViewModel: NavViewModel) = composable<ROUTE.RecommendedSpot> {
    val data: ROUTE.RecommendedSpot = it.toRoute()
    RecommendedSpotScreen(
        name = data.name,
//        moveToMainScreen = {
//            navViewModel.navigatePopUpTo(
//                ROUTE.Main(),
//                ROUTE.TypeTest.Result("",true, true)
//            )
//       },
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToDetailScreen = { id, category ->
            when(category) {
                "NATURE" -> navViewModel.navigate(ROUTE.Content.Nature.Detail(id))
                "EXPERIENCE" -> navViewModel.navigate(ROUTE.Content.Experience.Detail(false,id))
                "CULTURE_AND_ARTS" -> navViewModel.navigate(ROUTE.Content.Experience.Detail(false, id))
                "ACTIVITY" -> navViewModel.navigate(ROUTE.Content.Experience.Detail(true, id))
                "FESTIVAL" -> navViewModel.navigate(ROUTE.Content.Festival.Detail(id))
                "MARKET" -> navViewModel.navigate(ROUTE.Content.Market.Detail(id))
            }
        }

    )
}