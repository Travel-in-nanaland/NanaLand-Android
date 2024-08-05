package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_REPORT
import com.jeju.nanaland.ui.report.ReportScreen

fun NavGraphBuilder.reportScreen(navController: NavController) = composable(route = ROUTE_REPORT) {
    ReportScreen(
        reviewId = it.arguments?.getInt("reviewId", 1) ?: 1,
        moveToBackScreen = { navController.popBackStack() },
    )
}