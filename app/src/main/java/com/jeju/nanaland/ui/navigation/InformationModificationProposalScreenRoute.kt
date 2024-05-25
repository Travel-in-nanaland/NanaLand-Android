package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalScreen

fun NavGraphBuilder.informationModificationProposalScreen(navController: NavController) = composable(route = ROUTE_INFORMATION_MODIFICATION_PROPOSAL) {
    InformationModificationProposalScreen(
        moveToBackScreen = { navController.popBackStack() }
    )
}