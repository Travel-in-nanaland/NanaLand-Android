package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.experience.ExperienceListScreen

fun NavGraphBuilder.experienceListScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Experience> {
    val data: ROUTE.Content.Experience = it.toRoute()

    ExperienceListScreen(
        isActivity = data.isActivity,
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToExperienceContentScreen = { contentId, experienceCategoryType ->
            navViewModel.navigate(ROUTE.Content.Experience.Detail(contentId = contentId, isActivity = data.isActivity))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main()) },
        toHome = { navViewModel.navigatePopUpTo(ROUTE.Main(0), data) },
        toFavorite = { navViewModel.navigatePopUpTo(ROUTE.Main(1), data) },
        toNana = { navViewModel.navigatePopUpTo(ROUTE.Main(2), data) },
        toProfile = { navViewModel.navigatePopUpTo(ROUTE.Main(3), data) },
    )
}