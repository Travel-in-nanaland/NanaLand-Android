package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.nanapick.NanaPickAllListScreen

fun NavGraphBuilder.nanapickAllListScreen(navViewModel: NavViewModel) = composable<ROUTE.Main.NanaPick.AllList>{
    NanaPickAllListScreen(
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToNanaPickContentScreen = { contentId ->
            navViewModel.navigate(ROUTE.Main.NanaPick.Detail(contentId))
        }
    )
}