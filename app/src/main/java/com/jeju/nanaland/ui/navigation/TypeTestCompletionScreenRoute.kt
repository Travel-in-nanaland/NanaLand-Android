package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.typetest.TypeTestCompletionScreen

fun NavGraphBuilder.typeTestCompletionScreen(navViewModel: NavViewModel) = composable<ROUTE.TypeTest.TestEnd> {
    val data: ROUTE.TypeTest.Loading= it.toRoute()

    TypeTestCompletionScreen(
        moveToTypeTestLoadingScreen = {
            navViewModel.navigatePopUpTo(
                ROUTE.TypeTest.Loading(
                    isFirst = data.isFirst,
                    travelType =  data.travelType
                ),
                data
            )
        },
    )
}