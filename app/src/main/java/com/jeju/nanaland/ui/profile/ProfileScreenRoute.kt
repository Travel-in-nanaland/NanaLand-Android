package com.jeju.nanaland.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.jeju.nanaland.globalvalue.constant.ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_REVIEW_WRITE_ROUTE
import com.jeju.nanaland.globalvalue.constant.ROUTE_SETTINGS
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.globalvalue.userdata.UserData
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.profile.profileNoticeList.ProfileNoticeListScreen
import com.jeju.nanaland.ui.profile.profileReviewList.ProfileReviewListScreen
import com.jeju.nanaland.ui.profile.profileupdate.ProfileUpdateScreen
import com.jeju.nanaland.ui.profile.root.ProfileScreen
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.profileScreenRoute(navController: NavController) = navigation<ROUTE.Profile>(
    ROUTE.Profile.StartDest(null)
) {
    // TODO 현재는 타인 프로필만 이용 -> 병합 후 "isMine" key 상수 value 수정
    composable<ROUTE.Profile.StartDest> {
        val data: ROUTE.Profile.StartDest = it.toRoute()

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
                            onBackButtonClicked = { navController.popBackStack() },
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
                            moveToTypeTestScreen = { navController.navigate(ROUTE.TypeTest.Testing()) },
                            moveToTypeTestResultScreen = { navController.navigate(
                                ROUTE.TypeTest.Result(
                                    name = UserData.nickname,
                                    travelType = it,
                                    isMine = true,
                                    isFirst = false
                                )
                            ) },
                            moveToReviewWriteScreen = { navController.navigate(ROUTE_REVIEW_WRITE_ROUTE) },
                            moveToProfileNoticeListScreen = {
                                if(it == null) navController.navigate(ROUTE.Profile.NoticeList)
                                else navController.navigate(ROUTE.NoticeDetail(it))
                            },
                            moveToProfileReviewListScreen = {
                                navController.navigate(ROUTE.Profile.ReviewList(it))
                            },
                        )

                    } else { /** 타인 프로필 **/
                        ProfileScreen(
                            onBackButtonClicked = { navController.popBackStack() },
                            moveToTypeTestResultScreen = { name, type ->
                                navController.navigate( ROUTE.TypeTest.Result(
                                    name = name,
                                    travelType = type,
                                    isMine = false,
                                    isFirst = false
                                ))
                            },
                            moveToProfileReviewListScreen = {
                                navController.navigate(ROUTE.Profile.ReviewList(it))
                            },
                            moveToReportScreen = {
                                navController.navigate(ROUTE.Report(it, false))
                            }
                        )
                    }
                }
            }
        }
    }

    composable<ROUTE.Profile.Update> {
        val data: ROUTE.Profile.Update= it.toRoute()

        ProfileUpdateScreen(
            profileImageUri = data.profileImageUri,
            nickname = data.nickname,
            introduction = data.introduction,
            moveToBackScreen = { navController.popBackStack() }
        )
    }

    composable<ROUTE.Profile.NoticeList>{
        val parentEntry = remember(it) { navController.previousBackStackEntry!! }

        ProfileNoticeListScreen(
            moveToProfileNoticeListScreen = {
                navController.navigate(ROUTE.NoticeDetail(it))
            },
            moveToBackScreen = { navController.popBackStack() },
            viewModel = hiltViewModel(parentEntry)
        )
    }

    composable<ROUTE.Profile.ReviewList> {
        val data: ROUTE.Profile.ReviewList= it.toRoute()
        val parentEntry = remember(it) { navController.previousBackStackEntry!! }

        ProfileReviewListScreen(
            moveToBackScreen = { navController.popBackStack() },
            moveToReviewReportScreen = {
                navController.navigate(
                    ROUTE.Report(it, false)
                )
            },
            moveToReviewEditScreen = { id, category ->
                navController.navigate(
                    ROUTE_REVIEW_WRITE_ROUTE, bundleOf(
                        "id" to id, "category" to category.toString(), "isEdit" to true
                    )
                )
            },
            initialScrollToItemId = data.initialScrollToItemId ?: -1,
            viewModel = hiltViewModel(parentEntry)
        )
    }
}