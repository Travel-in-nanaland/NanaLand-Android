package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalWritingScreen

fun NavGraphBuilder.informationModificationProposalWritingScreen(navViewModel: NavViewModel) = composable<ROUTE.InformationModification.Write> {
    val data: ROUTE.InformationModification.Write = it.toRoute()

    InformationModificationProposalWritingScreen(
        postId = data.postId,
        fixType = data.fixType,
        category = data.category,
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToCompleteScreen = { navViewModel.navigatePopUpTo(ROUTE.InformationModification.Complete, data) }
    )
}