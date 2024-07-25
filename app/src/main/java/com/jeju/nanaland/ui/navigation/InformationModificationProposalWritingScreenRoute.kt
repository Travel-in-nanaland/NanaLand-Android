package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL_COMPLETE
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL_WRITING
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalWritingScreen

fun NavGraphBuilder.informationModificationProposalWritingScreen(navController: NavController) = composable(route = ROUTE_INFORMATION_MODIFICATION_PROPOSAL_WRITING) {
    InformationModificationProposalWritingScreen(
        postId = (it.arguments?.getInt("postId") ?: 0),
        fixType = (it.arguments?.getString("fixType") ?: ""),
        category = (it.arguments?.getString("category") ?: ""),
        moveToBackScreen = { navController.popBackStack() },
        moveToCompleteScreen = { navController.navigate(ROUTE_INFORMATION_MODIFICATION_PROPOSAL_COMPLETE) {
            popUpTo(ROUTE_INFORMATION_MODIFICATION_PROPOSAL_WRITING) { inclusive = true }
            launchSingleTop = true
        } }
    )
}