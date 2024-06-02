package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL_WRITING
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalCategoryScreen
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.informationModificationProposalCategoryScreen(navController: NavController) = composable(route = ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY) {
    InformationModificationProposalCategoryScreen(
        moveToBackScreen = { navController.popBackStack() },
        moveToInfoModificationProposalWritingScreen = { fixType ->
            val bundle = bundleOf(
                "postId" to it.arguments?.getLong("postId"),
                "fixType" to fixType,
                "category" to it.arguments?.getString("category")
            )
            navController.navigate(ROUTE_INFORMATION_MODIFICATION_PROPOSAL_WRITING, bundle)
        }
    )
}