package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL_COMPLETE
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalCompleteScreen
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalWritingScreen

fun NavGraphBuilder.informationModificationProposalCompleteScreen(navController: NavController) = composable(route = ROUTE_INFORMATION_MODIFICATION_PROPOSAL_COMPLETE) {
    InformationModificationProposalCompleteScreen(
        moveToContentScreen = { navController.popBackStack(ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY, true) },
        moveToInfoModificationProposalCategoryScreen = { navController.popBackStack(ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY, false) }
    )
}