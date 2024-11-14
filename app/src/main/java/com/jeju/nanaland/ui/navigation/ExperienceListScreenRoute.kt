package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE

fun NavGraphBuilder.experienceListScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Experience> {
//    ExperienceListScreen(
//        moveToBackScreen = { navViewModel.popBackStack() },
//        moveToExperienceContentScreen = { contentId, experienceCategoryType ->
//            val bundle = bundleOf(
//                "contentId" to contentId,
//                "experienceCategoryType" to experienceCategoryType
//            )
//            navViewModel.navigate(ROUTE_EXPERIENCE_CONTENT, bundle)
//        },
//        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main) }
//    )
}