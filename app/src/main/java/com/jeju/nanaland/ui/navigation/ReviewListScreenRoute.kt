package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.review.ReviewListScreen

fun NavGraphBuilder.reviewListScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.ReviewList> {
    val data: ROUTE.Content.ReviewList = it.toRoute()

    ReviewListScreen(
        isFavorite = data.isFavorite,
        contentId = data.contentId,
        category = data.category,
        thumbnailUrl = data.thumbnailUrl,
        contentTitle = data.contentTitle,
        contentAddress = data.contentAddress,
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToReviewWritingScreen = { id, image, title, address ->
            navViewModel.navigate(ROUTE.Content.ReviewWrite.StartDest(id, ReviewCategoryType.EXPERIENCE.toString()))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main()) },
        moveToReportScreen = { navViewModel.navigate(ROUTE.Report(it, true)) },
        moveToProfileScreen = { navViewModel.navigate(ROUTE.Main.Profile.StartDest(it)) },
        moveToReviewEditScreen = { id, category ->
            navViewModel.navigate(ROUTE.Content.ReviewWrite.StartDest(id, category.toString(), true))
        }
    )
}