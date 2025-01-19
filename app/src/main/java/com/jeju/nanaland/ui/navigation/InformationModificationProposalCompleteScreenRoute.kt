package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalCompleteScreen

fun NavGraphBuilder.informationModificationProposalCompleteScreen(navViewModel: NavViewModel) = composable<ROUTE.InformationModification.Complete> {
    InformationModificationProposalCompleteScreen(
        moveToContentScreen = { navViewModel.popBackStack() },
    )
}