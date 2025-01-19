package com.jeju.nanaland.ui.searchInContent

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE

fun NavGraphBuilder.mapRoute(navViewModel: NavViewModel) = composable<ROUTE.Content.Map> {
    val data: ROUTE.Content.Map = it.toRoute()

    MapScreen(
        name = data.name,
        localLocate = data.localLocate,
        koreaLocate = data.koreaLocate,
        lat = data.lat,
        lng = data.lng,
        moveToBackScreen = { navViewModel.popBackStack() }
    )
}