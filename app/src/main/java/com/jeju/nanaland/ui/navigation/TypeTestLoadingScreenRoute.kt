package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.typetest.TypeTestLoadingScreen

fun NavGraphBuilder.typeTestLoadingScreen(navViewModel: NavViewModel) = composable<ROUTE.TypeTest.Loading> {
    val data: ROUTE.TypeTest.Loading= it.toRoute()

    TypeTestLoadingScreen(
        moveToTypeTestResultScreen = {
            navViewModel.popBackStack(data, true)
            navViewModel.navigate(
                ROUTE.TypeTest.Result(
                    id = null,
                    name = UserData.nickname,
                    travelType = data.travelType,
                    isMine = true,
                    isFirst = data.isFirst
                )
            )
        },
    )
}