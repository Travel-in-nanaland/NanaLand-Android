package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.ui.typetest.TypeTestingScreen

fun NavGraphBuilder.typeTestingScreen(navController: NavController) = composable<ROUTE.TypeTest.Testing> {
    val data: ROUTE.TypeTest.Testing= it.toRoute()

    TypeTestingScreen(
        moveToTypeTestCompletionScreen = { travelType ->
            navController.popBackStack(ROUTE.TypeTest.Testing, true)
            navController.navigate( ROUTE.TypeTest.TestEnd(
                data.isFirst,travelType
            ) )
        },
        moveToBackScreen = {
            if(!navController.popBackStack())
                navController.navigate(ROUTE_MAIN) {
                    popUpTo(ROUTE.TypeTest.Testing) { inclusive = true }
                }
        }
    )
}