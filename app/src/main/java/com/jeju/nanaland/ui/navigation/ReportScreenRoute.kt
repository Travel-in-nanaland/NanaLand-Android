package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.report.ReportScreen

fun NavGraphBuilder.reportScreen(navViewModel: NavViewModel) = composable<ROUTE.Report> {
    ReportScreen(
        moveToBackScreen = { navViewModel.popBackStack() },
    )
}