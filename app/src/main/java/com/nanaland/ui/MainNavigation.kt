package com.nanaland.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_LIST
import com.nanaland.globalvalue.constant.ROUTE_FESTIVAL_CONTENT
import com.nanaland.globalvalue.constant.ROUTE_FESTIVAL_LIST
import com.nanaland.globalvalue.constant.ROUTE_LANGUAGE_SELECTION
import com.nanaland.globalvalue.constant.ROUTE_MAIN
import com.nanaland.globalvalue.constant.ROUTE_MARKET_CONTENT
import com.nanaland.globalvalue.constant.ROUTE_MARKET_LIST
import com.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
import com.nanaland.globalvalue.constant.ROUTE_NANAPICK_LIST
import com.nanaland.globalvalue.constant.ROUTE_NATURE_CONTENT
import com.nanaland.globalvalue.constant.ROUTE_NATURE_LIST
import com.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.nanaland.globalvalue.constant.ROUTE_SPLASH
import com.nanaland.globalvalue.type.CategoryType
import com.nanaland.ui.experience.ExperienceContentScreen
import com.nanaland.ui.experience.ExperienceListScreen
import com.nanaland.ui.languageselection.LanguageSelectionScreen
import com.nanaland.ui.main.MainScreen
import com.nanaland.ui.festival.FestivalContentScreen
import com.nanaland.ui.festival.FestivalListScreen
import com.nanaland.ui.market.MarketContentScreen
import com.nanaland.ui.market.MarketListScreen
import com.nanaland.ui.nanapick.NanaPickContentScreen
import com.nanaland.ui.nanapick.NanaPickListScreen
import com.nanaland.ui.nature.NatureContentScreen
import com.nanaland.ui.nature.NatureListScreen
import com.nanaland.ui.splash.SplashScreen
import com.nanaland.util.navigation.navigate
import com.nanaland.util.type.getCategoryType

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = ROUTE_SPLASH
    ) {
        // 스플래시 화면
        composable(route = ROUTE_SPLASH) {
            SplashScreen(
                moveToMainScreen = {
                    navController.navigate(ROUTE_MAIN) {
                        popUpTo(ROUTE_SPLASH) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        // 언어 선택 화면
        composable(route = ROUTE_LANGUAGE_SELECTION) {
            LanguageSelectionScreen(
                moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) { launchSingleTop = true } }
            )
        }

        // 로그인 화면
        composable(route = ROUTE_SIGN_IN) {
        }

        // 메인 화면
        composable(route = ROUTE_MAIN) {
            MainScreen(
                moveToCategoryContentScreen = { contentId, category ->
                    val bundle = bundleOf(
                        "contentId" to contentId
                    )
                    when (getCategoryType(category)) {
                        CategoryType.Nature -> { navController.navigate(ROUTE_NATURE_CONTENT, bundle) }
                        CategoryType.Festival -> { navController.navigate(ROUTE_FESTIVAL_CONTENT, bundle) }
                        CategoryType.Market -> { navController.navigate(ROUTE_MARKET_CONTENT, bundle) }
                        CategoryType.Experience -> { navController.navigate(ROUTE_EXPERIENCE_CONTENT, bundle) }
                        CategoryType.Nana -> { navController.navigate(ROUTE_NANAPICK_CONTENT, bundle) }
                    }
                },
                moveToNanaPickListScreen = { navController.navigate(ROUTE_NANAPICK_LIST) { launchSingleTop = true } },
                moveToNatureListScreen = { navController.navigate(ROUTE_NATURE_LIST) { launchSingleTop = true } },
                moveToFestivalListScreen = { navController.navigate(ROUTE_FESTIVAL_LIST) { launchSingleTop = true } },
                moveToMarketListScreen = { navController.navigate(ROUTE_MARKET_LIST) { launchSingleTop = true } },
                moveToExperienceScreen = { /* MVP2 구현 예정 */ }
            )
        }

        // 자연 리스트 화면
        composable(route = ROUTE_NATURE_LIST) {
            NatureListScreen(
                moveToBackScreen = { navController.popBackStack() },
                moveToNatureContentScreen = { contentId ->
                    val bundle = bundleOf(
                        "contentId" to contentId
                    )
                    navController.navigate(ROUTE_NATURE_CONTENT, bundle)
                }
            )
        }

        // 자연 상세 화면
        composable(route = ROUTE_NATURE_CONTENT) {
            NatureContentScreen(
                contentId = it.arguments?.getLong("contentId"),
                moveToBackScreen = { navController.popBackStack() }
            )
        }

        // 축제 리스트 화면
        composable(route = ROUTE_FESTIVAL_LIST) {
            FestivalListScreen(
                moveToBackScreen = { navController.popBackStack() },
                moveToFestivalContentScreen = { contentId ->
                    val bundle = bundleOf(
                        "contentId" to contentId
                    )
                    navController.navigate(ROUTE_FESTIVAL_CONTENT, bundle)
                }
            )
        }

        // 축제 상세 화면
        composable(route = ROUTE_FESTIVAL_CONTENT) {
            FestivalContentScreen(
                contentId = it.arguments?.getLong("contentId"),
                moveToBackScreen = { navController.popBackStack() }
            )
        }

        // 전통시장 리스트 화면
        composable(route = ROUTE_MARKET_LIST) {
            MarketListScreen(
                moveToBackScreen = { navController.popBackStack() },
                moveToMarketContentScreen = { contentId ->
                    val bundle = bundleOf(
                        "contentId" to contentId
                    )
                    navController.navigate(ROUTE_MARKET_CONTENT, bundle)
                }
            )
        }

        // 전통시장 상세 화면
        composable(route = ROUTE_MARKET_CONTENT) {
            val parentEntry = remember(it) {
                navController.previousBackStackEntry!!
            }
            MarketContentScreen(
                contentId = it.arguments?.getLong("contentId"),
                marketListViewModel = hiltViewModel(parentEntry),
                moveToBackScreen = { navController.popBackStack() }
            )
        }

        // 이색체험 리스트 화면
        composable(route = ROUTE_EXPERIENCE_LIST) {
            ExperienceListScreen(
                moveToExperienceContentScreen = { navController.navigate(ROUTE_EXPERIENCE_CONTENT) { launchSingleTop = true } }
            )
        }

        // 이색체험 상세 화면
        composable(route = ROUTE_EXPERIENCE_CONTENT) {
            ExperienceContentScreen()
        }

        // 나나 Pick 리스트 화면
        composable(route = ROUTE_NANAPICK_LIST) {
            NanaPickListScreen(
                moveToNanaPickContentScreen = { contentId ->
                    val bundle = bundleOf(
                        "contentId" to contentId
                    )
                    navController.navigate(ROUTE_NANAPICK_CONTENT, bundle)
                },
                moveToMainScreen = { navController.popBackStack() }
            )
        }

        // 나나 Pick 상세 화면
        composable(route = ROUTE_NANAPICK_CONTENT) {
            NanaPickContentScreen(
                contentId = it.arguments?.getLong("contentId"),
                moveToBackScreen = { navController.popBackStack() }
            )
        }
    }
}