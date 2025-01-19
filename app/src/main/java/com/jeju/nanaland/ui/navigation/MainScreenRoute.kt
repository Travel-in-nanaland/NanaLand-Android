package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.main.MainScreen
import com.jeju.nanaland.util.intent.DeepLinkData

fun NavGraphBuilder.mainScreen(
    deepLinkData: DeepLinkData,
    navViewModel: NavViewModel
) = composable<ROUTE.Main> {
    val data: ROUTE.Main = it.toRoute()

    MainScreen(
        viewTypeByPopStack = when (data.toScreenIndex) {
            0 -> MainScreenViewType.Home
            1 -> MainScreenViewType.Favorite
            2 -> MainScreenViewType.NanaPick
            3 -> MainScreenViewType.MyPage
            else -> null
        },
        retry = {navViewModel.navigate(ROUTE.Main())},
        deepLinkData = deepLinkData,
        moveToNotificationScreen = { navViewModel.navigate(ROUTE.Main.Home.Notification) },
        moveToCategoryContentScreen = { contentId, category, isSearch ->
            when (category) {
                "NATURE" ->  { navViewModel.navigate(ROUTE.Content.Nature.Detail(contentId, isSearch)) }
                "FESTIVAL" ->  { navViewModel.navigate(ROUTE.Content.Festival.Detail(contentId, isSearch)) }
                "MARKET" ->  { navViewModel.navigate(ROUTE.Content.Market.Detail(contentId, isSearch)) }
                "EXPERIENCE" ->  { navViewModel.navigate(ROUTE.Content.Experience.Detail(false, contentId, isSearch)) }
                "CULTURE_AND_ARTS" ->  { navViewModel.navigate(ROUTE.Content.Experience.Detail(false, contentId, isSearch)) }
                "ACTIVITY" ->  { navViewModel.navigate(ROUTE.Content.Experience.Detail(true, contentId, isSearch)) }
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
        moveToActivityListScreen = { navViewModel.navigate(ROUTE.Content.Experience(true)) },
        moveToArtListScreen = { navViewModel.navigate(ROUTE.Content.Experience(false)) },
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
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main()) },
        moveToTypeTestScreen = { navViewModel.navigate(ROUTE.TypeTest.Testing()) },
        moveToTypeTestResultScreen = { navViewModel.navigate(
            ROUTE.TypeTest.Result(
                name = UserData.nickname,
                travelType = it,
                isMine = true,
                isFirst = false
            )
        ) },
        moveToReviewWriteScreen = { navViewModel.navigate(ROUTE.Content.ReviewWrite.StartDest()) },
        moveToProfileNoticeListScreen = {
            if(it == null) navViewModel.navigate(ROUTE.Main.Profile.NoticeList)
            else navViewModel.navigate(ROUTE.Main.Profile.NoticeList.NoticeDetail(it))
        },
        moveToProfileReviewListScreen = {
            navViewModel.navigate(ROUTE.Main.Profile.ReviewList(it))
        },
        moveToNanaPickAllListScreen = {
            navViewModel.navigate(ROUTE.Main.NanaPick.AllList)
        },
        moveToReviewEditScreen = { id, category ->
            navViewModel.navigate(ROUTE.Content.ReviewWrite.StartDest(id, category.toString(), true))
        }
    )
}