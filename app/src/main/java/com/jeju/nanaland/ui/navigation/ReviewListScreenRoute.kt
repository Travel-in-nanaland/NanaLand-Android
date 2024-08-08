package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.review.ReviewListScreen
import com.jeju.nanaland.util.log.LogUtil
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.reviewListScreen(navController: NavController) = composable(route = ROUTE_REVIEW_LIST) {
    ReviewListScreen(
        isFavorite = it.arguments?.getBoolean("isFavorite"),
        contentId = it.arguments?.getInt("contentId"),
        category = it.arguments?.getString("category"),
        thumbnailUrl = it.arguments?.getString("thumbnailUrl"),
        contentTitle = it.arguments?.getString("contentTitle"),
        contentAddress = it.arguments?.getString("contentAddress"),
        moveToBackScreen = { navController.popBackStack() },
        moveToReviewWritingScreen = { id, image, title, address ->
            LogUtil.e("moveToReviewWritingScreen", "moveToReviewWritingScreen")
            val bundle = bundleOf(
                "id" to id,
                "category" to ReviewCategoryType.EXPERIENCE.toString(),
                "image" to image,
                "title" to title,
                "address" to address,
            )
            navController.navigate(ROUTE_REVIEW_WRITE_ROUTE, bundle)
        },
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } }
    )
}