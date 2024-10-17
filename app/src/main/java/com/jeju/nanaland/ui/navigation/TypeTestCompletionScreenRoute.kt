package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.ui.typetest.TypeTestCompletionScreen

fun NavGraphBuilder.typeTestCompletionScreen(navController: NavController) = composable<ROUTE.TypeTest.TestEnd> {
    val data: ROUTE.TypeTest.Loading= it.toRoute()

    TypeTestCompletionScreen(
        moveToTypeTestLoadingScreen = {
            navController.popBackStack(ROUTE.TypeTest.TestEnd, true)
            navController.navigate( ROUTE.TypeTest.Loading(
                isFirst = data.isFirst,
                travelType =  data.travelType
            ))
        }
    )
}