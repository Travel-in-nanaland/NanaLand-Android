package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.R
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_RECOMMENDED_SPOT
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TESTING
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_RESULT
import com.jeju.nanaland.globalvalue.constant.toTravelType
import com.jeju.nanaland.ui.typetest.TypeTestResultScreen
import com.jeju.nanaland.util.resource.getString

fun NavGraphBuilder.typeTestResultScreen(navController: NavController) = composable(route = ROUTE_TYPE_TEST_RESULT) {
    val isFirst = it.arguments?.getBoolean("isFirst") == true
    val filledButtonString = if(isFirst)
            getString(R.string.type_test_screen_button2)
        else
            getString(R.string.mypage_screen_테스트_다시하기)

    TypeTestResultScreen(
        travelType = it.arguments!!.getString("travelType")!!.toTravelType(),
        filledButtonString = filledButtonString,
        moveToRecommendedSpotScreen = { navController.navigate(ROUTE_RECOMMENDED_SPOT) {
            popUpTo(ROUTE_TYPE_TEST_RESULT) { inclusive = false }
            launchSingleTop = true
        } },
        onFilledButtonClick = {
            if(isFirst) { navController.navigate(ROUTE_MAIN) {
                popUpTo(ROUTE_TYPE_TEST_RESULT) { inclusive = true }
                launchSingleTop = true
            } }
            else { navController.navigate(ROUTE_TYPE_TESTING) {
                popUpTo(ROUTE_TYPE_TEST_RESULT) { inclusive = true }
                launchSingleTop = true
            } }
        },
    )
}