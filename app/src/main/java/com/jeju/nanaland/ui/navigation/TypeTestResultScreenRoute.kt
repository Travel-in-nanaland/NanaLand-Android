package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_RECOMMENDED_SPOT
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.ui.typetest.TypeTestResultScreen
import com.jeju.nanaland.util.resource.getString

fun NavGraphBuilder.typeTestResultScreen(navController: NavController) = composable<ROUTE.TypeTest.Result> {
    val data: ROUTE.TypeTest.Result= it.toRoute()

    val filledButtonString = if(data.isFirst)
            getString(R.string.type_test_screen_button2)
        else
            getString(R.string.mypage_screen_테스트_다시하기)

    TypeTestResultScreen(
        name = data.name,
        travelType = data.travelType ?: TravelType.GAMGYUL,
        filledButtonString = filledButtonString,
        moveToRecommendedSpotScreen = { navController.navigate(ROUTE_RECOMMENDED_SPOT) {
            popUpTo(ROUTE.TypeTest.Result) { inclusive = false }
            launchSingleTop = true
        } },
        onFilledButtonClick = if(data.isMine) {
            {
                if(data.isFirst) { navController.navigate(ROUTE_MAIN) {
                    popUpTo(ROUTE.TypeTest.Result) { inclusive = true }
                    launchSingleTop = true
                } }
                else { navController.navigate(ROUTE.TypeTest.Testing()) {
                    popUpTo(ROUTE.TypeTest.Result) { inclusive = true }
                    launchSingleTop = true
                } }
            }
        } else {
            null
        },
        moveToBackScreen = if(data.isFirst) null else {
            { navController.popBackStack() }
        }
    )
}