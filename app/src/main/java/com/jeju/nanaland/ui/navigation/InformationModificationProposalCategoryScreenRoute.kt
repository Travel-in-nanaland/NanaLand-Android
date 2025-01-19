package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalCategoryScreen

fun NavGraphBuilder.informationModificationProposalCategoryScreen(navViewModel: NavViewModel) = composable<ROUTE.InformationModification> {
    val data: ROUTE.InformationModification = it.toRoute()

    InformationModificationProposalCategoryScreen(
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToInfoModificationProposalWritingScreen = { fixType ->
            navViewModel.navigatePopUpTo(ROUTE.InformationModification.Write(data.postId, data.category, fixType), data)
        }
    )
}