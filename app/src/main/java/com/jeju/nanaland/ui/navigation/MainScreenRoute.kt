package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.main.MainScreen
import com.jeju.nanaland.util.intent.DeepLinkData

fun NavGraphBuilder.mainScreen(
    deepLinkData: DeepLinkData,
    navViewModel: NavViewModel
) = composable<ROUTE.Main> {
    MainScreen(
        deepLinkData = deepLinkData,
        moveToNotificationScreen = { navViewModel.navigate(ROUTE.Main.Home.Notification) },
        moveToCategoryContentScreen = { contentId, category, isSearch ->
            when (category) {
                "NATURE" ->  { navViewModel.navigate(ROUTE.Content.Nature.Detail(contentId, isSearch)) }
                "FESTIVAL" ->  { navViewModel.navigate(ROUTE.Content.Festival.Detail(contentId, isSearch)) }
                "MARKET" ->  { navViewModel.navigate(ROUTE.Content.Market.Detail(contentId, isSearch)) }
                "EXPERIENCE" ->  { navViewModel.navigate(ROUTE.Content.Experience(contentId, isSearch)) }
                "RESTAURANT" ->  { navViewModel.navigate(ROUTE.Content.Restaurant.Detail(contentId, isSearch)) }
                else  ->  { navViewModel.navigate(ROUTE.Main.NanaPick.Detail(contentId)) }
            }
        },
        moveToRestaurantListScreen = { navViewModel.navigate(ROUTE.Content.Restaurant) },
        moveToNatureListScreen = { filter ->
            navViewModel.navigate(ROUTE.Content.Nature(filter))
        },
        moveToFestivalListScreen = { filter ->
            navViewModel.navigate(ROUTE.Content.Festival(filter))
        },
        moveToMarketListScreen = { navViewModel.navigate(ROUTE.Content.Market) },
        moveToExperienceListScreen = { navViewModel.navigate(ROUTE.Content.Experience.Art /*TODO*/) },
        moveToSettingsScreen = { navViewModel.navigate(ROUTE.Main.Profile.Setting) },
        moveToProfileModificationScreen = { profileImageUri, nickname, introduction ->
            navViewModel.navigate(
                ROUTE.Main.Profile.Update(
                    profileImageUri = profileImageUri ?: "",
                    nickname = nickname ?: "",
                    introduction = introduction ?: ""
                )
            )
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main) },
        moveToTypeTestScreen = { navViewModel.navigate(ROUTE.TypeTest.Testing()) },
        moveToTypeTestResultScreen = { navViewModel.navigate(
            ROUTE.TypeTest.Result(
                name = UserData.nickname,
                travelType = it,
                isMine = true,
                isFirst = false
            )
        ) },
        moveToReviewWriteScreen = { navViewModel.navigate(ROUTE.Content.ReviewWrite()) },
        moveToProfileNoticeListScreen = {
            if(it == null) navViewModel.navigate(ROUTE.Main.Profile.NoticeList)
            else navViewModel.navigate(ROUTE.Main.Profile.NoticeList.NoticeDetail(it))
        },
        moveToProfileReviewListScreen = {
            navViewModel.navigate(ROUTE.Main.Profile.ReviewList(it))
        },
        moveToNanaPickAllListScreen = {
            navViewModel.navigate(ROUTE.Main.NanaPick.AllList)
        }
    )
}