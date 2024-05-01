package com.nanaland.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
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
                    }
                }
            )
        }

        // 언어 선택 화면
        composable(route = ROUTE_LANGUAGE_SELECTION) {
            LanguageSelectionScreen(
                moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) }
            )
        }

        // 로그인 화면
        composable(route = ROUTE_SIGN_IN) {
        }

        // 메인 화면
        composable(route = ROUTE_MAIN) {
            MainScreen(
                moveToNanaPickListScreen = { navController.navigate(ROUTE_NANAPICK_LIST) },
                moveToNanaPickContentScreen = { contentId ->
                    val bundle = bundleOf(
                        "contentId" to contentId
                    )
                    navController.navigate(ROUTE_NANAPICK_CONTENT, bundle)
                },
                moveToNatureListScreen = { navController.navigate(ROUTE_NATURE_LIST) },
                moveToFestivalListScreen = { navController.navigate(ROUTE_FESTIVAL_LIST) },
                moveToMarketListScreen = { navController.navigate(ROUTE_MARKET_LIST) },
                moveToExperienceScreen = { /* MVP2 구현 예정 */ }
            )
        }

        // 자연 리스트 화면
        composable(route = ROUTE_NATURE_LIST) {
            NatureListScreen(
                moveToNatureContentScreen = { navController.navigate(ROUTE_NATURE_CONTENT) }
            )
        }

        // 자연 상세 화면
        composable(route = ROUTE_NATURE_CONTENT) {
            NatureContentScreen()
        }

        // 축제 리스트 화면
        composable(route = ROUTE_FESTIVAL_LIST) {
            FestivalListScreen(
                moveToFestivalContentScreen = { navController.navigate(ROUTE_FESTIVAL_CONTENT) }
            )
        }

        // 축제 상세 화면
        composable(route = ROUTE_FESTIVAL_CONTENT) {
            FestivalContentScreen()
        }

        // 전통시장 리스트 화면
        composable(route = ROUTE_MARKET_LIST) {
            MarketListScreen(
                moveToMarketContentScreen = { navController.navigate(ROUTE_MARKET_CONTENT) }
            )
        }

        // 전통시장 상세 화면
        composable(route = ROUTE_MARKET_CONTENT) {
            MarketContentScreen()
        }

        // 이색체험 리스트 화면
        composable(route = ROUTE_EXPERIENCE_LIST) {
            ExperienceListScreen(
                moveToExperienceContentScreen = { navController.navigate(ROUTE_EXPERIENCE_CONTENT) }
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
                moveToMainScreen = {
                    navController.popBackStack(ROUTE_MAIN, false)
                }
            )
        }

        // 나나 Pick 상세 화면
        composable(route = ROUTE_NANAPICK_CONTENT) {
            NanaPickContentScreen(
                contentId = it.arguments?.getLong("contentId"),
                moveTonNanaPickListScreen = { navController.popBackStack(ROUTE_NANAPICK_LIST, false) }
            )
        }
    }
}