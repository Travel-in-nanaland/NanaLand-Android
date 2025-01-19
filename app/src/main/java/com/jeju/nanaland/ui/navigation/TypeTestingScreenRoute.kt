package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.typetest.TypeTestingScreen

fun NavGraphBuilder.typeTestingScreen(navViewModel: NavViewModel) = composable<ROUTE.TypeTest.Testing> {
    val data: ROUTE.TypeTest.Testing= it.toRoute()

    TypeTestingScreen(
        moveToTypeTestCompletionScreen = { travelType ->
            navViewModel.popBackStack(data, true)
            navViewModel.navigate(
                ROUTE.TypeTest.TestEnd(data.isFirst,travelType),
            )
        },
        moveToBackScreen = {
            if(data.isFirst) {
                navViewModel.navigatePopUpTo(
                    ROUTE.Main(),
                    ROUTE.TypeTest.Testing()
                )
            } else {
                navViewModel.popBackStack()
            }
        }
    )
}