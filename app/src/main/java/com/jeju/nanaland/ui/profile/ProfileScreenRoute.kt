package com.jeju.nanaland.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.profile.profileNoticeList.ProfileNoticeListScreen
import com.jeju.nanaland.ui.profile.profileReviewList.ProfileReviewListScreen
import com.jeju.nanaland.ui.profile.profileupdate.ProfileUpdateScreen
import com.jeju.nanaland.ui.profile.root.ProfileScreen
import com.jeju.nanaland.ui.theme.getColor

fun NavGraphBuilder.profileScreenRoute(
    navViewModel: NavViewModel,
    getBackStackEntry: () -> NavBackStackEntry
) = navigation<ROUTE.Main.Profile>(
    ROUTE.Main.Profile.StartDest(null)
) {
    composable<ROUTE.Main.Profile.StartDest> {
        val data: ROUTE.Main.Profile.StartDest = it.toRoute()

        CustomSurface { isImeKeyboardShowing ->
            Scaffold(
                containerColor = getColor().surface,
                contentWindowInsets = WindowInsets(0, 0, 0, 0)
            ) {
                Column(
                    modifier = Modifier
                        .imePadding()
                        .padding(bottom = if (isImeKeyboardShowing) 0.dp else it.calculateBottomPadding())
                ) {
                    if(data.userId == null) { /** 마이 페이지 **/
                        ProfileScreen(
                            onBackButtonClicked = { navViewModel.popBackStack() },
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
                        )

                    } else { /** 타인 프로필 **/
                        ProfileScreen(
                            onBackButtonClicked = { navViewModel.popBackStack() },
                            moveToTypeTestResultScreen = { name, type ->
                                navViewModel.navigate( ROUTE.TypeTest.Result(
                                    name = name,
                                    travelType = type,
                                    isMine = false,
                                    isFirst = false
                                ))
                            },
                            moveToProfileReviewListScreen = {
                                navViewModel.navigate(ROUTE.Main.Profile.ReviewList(it))
                            },
                            moveToReportScreen = {
                                navViewModel.navigate(ROUTE.Report(it, false))
                            }
                        )
                    }
                }
            }
        }
    }

    composable<ROUTE.Main.Profile.Update> {
        val data: ROUTE.Main.Profile.Update= it.toRoute()

        ProfileUpdateScreen(
            profileImageUri = data.profileImageUri,
            nickname = data.nickname,
            introduction = data.introduction,
            moveToBackScreen = { navViewModel.popBackStack() }
        )
    }

    composable<ROUTE.Main.Profile.NoticeList>{
        val parentEntry = remember(it) { getBackStackEntry() }

        ProfileNoticeListScreen(
            moveToProfileNoticeListScreen = {
                navViewModel.navigate(ROUTE.Main.Profile.NoticeList.NoticeDetail(it))
            },
            moveToBackScreen = { navViewModel.popBackStack() },
            viewModel = hiltViewModel(parentEntry)
        )
    }

    composable<ROUTE.Main.Profile.ReviewList> {
        val data: ROUTE.Main.Profile.ReviewList= it.toRoute()
        val parentEntry = remember(it) { getBackStackEntry() }

        ProfileReviewListScreen(
            moveToBackScreen = { navViewModel.popBackStack() },
            moveToReviewReportScreen = {
                navViewModel.navigate(
                    ROUTE.Report(it, true)
                )
            },
            moveToReviewEditScreen = { id, category ->
                navViewModel.navigate(ROUTE.Content.ReviewWrite(id, category.toString(), true))
            },
            initialScrollToItemId = data.initialScrollToItemId ?: -1,
            viewModel = hiltViewModel(parentEntry),
            moveToContentScreen = { reviewCategoryType, id ->
                when (reviewCategoryType) {
                    ReviewCategoryType.NATURE -> { navViewModel.navigate(ROUTE.Content.Nature.Detail(id, false)) }
                    ReviewCategoryType.FESTIVAL -> { navViewModel.navigate(ROUTE.Content.Festival.Detail(id, false)) }
                    ReviewCategoryType.MARKET -> { navViewModel.navigate(ROUTE.Content.Market.Detail(id, false)) }
                    ReviewCategoryType.EXPERIENCE -> { navViewModel.navigate(ROUTE.Content.Experience(id, false)) }
                    ReviewCategoryType.NANA, ReviewCategoryType.NANA_CONTENT -> { navViewModel.navigate(ROUTE.Main.NanaPick.Detail(id)) }
                    ReviewCategoryType.RESTAURANT -> { navViewModel.navigate(ROUTE.Content.Restaurant.Detail(id, false)) }
                }
            }
        )
    }
}