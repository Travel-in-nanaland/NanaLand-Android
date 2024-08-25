package com.jeju.nanaland.ui.navigation

import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_FESTIVAL_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_FESTIVAL_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_ALL_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NATURE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NATURE_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_RESTAURANT_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_RESTAURANT_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_SETTINGS
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TESTING
import com.jeju.nanaland.globalvalue.constant.ROUTE_TYPE_TEST_RESULT
import com.jeju.nanaland.globalvalue.type.CategoryType
import com.jeju.nanaland.ui.main.MainScreen
import com.jeju.nanaland.util.intent.DeepLinkData
import com.jeju.nanaland.util.navigation.navigate
import com.jeju.nanaland.util.type.getCategoryType

fun NavGraphBuilder.mainScreen(
    deepLinkData: DeepLinkData,
    navController: NavController
) = composable(route = ROUTE_MAIN) {
    MainScreen(
        deepLinkData = deepLinkData,
        moveToNotificationScreen = { navController.navigate(ROUTE.Home.Notification) { launchSingleTop = true } },
        moveToCategoryContentScreen = { contentId, category, isSearch ->
            val bundle = bundleOf(
                "contentId" to contentId,
                "isSearch" to isSearch
            )
            when (getCategoryType(category)) {
                CategoryType.Nature -> { navController.navigate(ROUTE_NATURE_CONTENT, bundle) }
                CategoryType.Festival -> { navController.navigate(ROUTE_FESTIVAL_CONTENT, bundle) }
                CategoryType.Market -> { navController.navigate(ROUTE_MARKET_CONTENT, bundle) }
                CategoryType.Experience -> { navController.navigate(ROUTE_EXPERIENCE_CONTENT, bundle) }
                CategoryType.Nana -> { navController.navigate(ROUTE_NANAPICK_CONTENT, bundle) }
                CategoryType.Restaurant -> { navController.navigate(ROUTE_RESTAURANT_CONTENT, bundle) }
            }
        },
        moveToRestaurantListScreen = { navController.navigate(ROUTE_RESTAURANT_LIST) { launchSingleTop = true } },
        moveToNatureListScreen = { filter ->
            val bundle = bundleOf(
                "filter" to filter
            )
            navController.navigate(ROUTE_NATURE_LIST, bundle)
        },
        moveToFestivalListScreen = { filter ->
            val bundle = bundleOf(
                "filter" to filter
            )
            navController.navigate(ROUTE_FESTIVAL_LIST, bundle)
        },
        moveToMarketListScreen = { navController.navigate(ROUTE_MARKET_LIST) { launchSingleTop = true } },
        moveToExperienceListScreen = { navController.navigate(ROUTE_EXPERIENCE_LIST) { launchSingleTop = true } },
        moveToSettingsScreen = { navController.navigate(ROUTE_SETTINGS) { launchSingleTop = true } },
        moveToProfileModificationScreen = { profileImageUri, nickname, introduction ->
            navController.navigate(
                ROUTE.Profile.Update(
                    profileImageUri = profileImageUri ?: "",
                    nickname = nickname ?: "",
                    introduction = introduction ?: ""
                )
            )
        },
        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
            popUpTo(ROUTE_MAIN) { inclusive = true }
            launchSingleTop = true
        } },
        moveToTypeTestScreen = { navController.navigate(ROUTE_TYPE_TESTING) },
        moveToTypeTestResultScreen = { navController.navigate(ROUTE_TYPE_TEST_RESULT,  bundleOf("travelType" to it)) },
        moveToReviewWriteScreen = { navController.navigate(ROUTE_REVIEW_WRITE_ROUTE) },
        moveToProfileNoticeListScreen = {
            if(it == null) navController.navigate(ROUTE.Profile.NoticeList)
            else navController.navigate(ROUTE.NoticeDetail(it))
        },
        moveToProfileReviewListScreen = {
            navController.navigate(ROUTE.Profile.ReviewList(it))
        },
        moveToNanaPickAllListScreen = {
            navController.navigate(ROUTE_NANAPICK_ALL_LIST) { launchSingleTop = true }
        }
    )
}