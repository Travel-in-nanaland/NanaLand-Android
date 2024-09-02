package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.ui.report.ReportScreen

fun NavGraphBuilder.reportScreen(navController: NavController) = composable<ROUTE.Report> {
    ReportScreen(
        moveToBackScreen = { navController.popBackStack() },
    )
}