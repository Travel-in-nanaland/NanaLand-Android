package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.typetest.TypeTestLoadingScreen

fun NavGraphBuilder.typeTestLoadingScreen(navController: NavController) = composable<ROUTE.TypeTest.Loading> {
    val data: ROUTE.TypeTest.Loading= it.toRoute()

    TypeTestLoadingScreen(
        moveToTypeTestResultScreen = {
            navController.popBackStack(ROUTE.TypeTest.Loading, true)
            navController.navigate( ROUTE.TypeTest.Result(
                    name = UserData.nickname,
                    travelType = data.travelType,
                    isMine = true,
                    isFirst = data.isFirst
            ))
        }
    )
}