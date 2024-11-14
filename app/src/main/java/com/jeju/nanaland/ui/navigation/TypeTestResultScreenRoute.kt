package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.constant.TravelType
import com.jeju.nanaland.ui.typetest.TypeTestResultScreen
import com.jeju.nanaland.util.resource.getString

fun NavGraphBuilder.typeTestResultScreen(navViewModel: NavViewModel) = composable<ROUTE.TypeTest.Result> {
    val data: ROUTE.TypeTest.Result= it.toRoute()

    val filledButtonString = if(data.isFirst)
            getString(R.string.type_test_screen_button2)
        else
            getString(R.string.mypage_screen_테스트_다시하기)

    TypeTestResultScreen(
        name = data.name,
        travelType = data.travelType ?: TravelType.GAMGYUL,
        filledButtonString = filledButtonString,
        moveToRecommendedSpotScreen = {
            navViewModel.navigatePopUpTo(
                ROUTE.RecommendedSpot,
                data
            )
        },
        onFilledButtonClick = if(data.isMine) {
            {
                if(data.isFirst) {
                    navViewModel.navigatePopUpTo(
                        ROUTE.Main,
                        data
                    )
                }
                else {
                    navViewModel.navigatePopUpTo(
                        ROUTE.TypeTest.Testing(),
                        data
                    )
                }
            }
        } else {
            null
        },
        moveToBackScreen = if(data.isFirst) null else {
            { navViewModel.popBackStack() }
        }
    )
}