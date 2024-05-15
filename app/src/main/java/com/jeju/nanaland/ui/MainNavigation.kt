package com.jeju.nanaland.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_FESTIVAL_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_FESTIVAL_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL
import com.jeju.nanaland.globalvalue.constant.ROUTE_LANGUAGE_SELECTION
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_NATURE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_NATURE_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_SIGN_IN
import com.jeju.nanaland.globalvalue.constant.ROUTE_SPLASH
import com.jeju.nanaland.globalvalue.type.CategoryType
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.ui.experience.ExperienceContentScreen
import com.jeju.nanaland.ui.experience.ExperienceListScreen
import com.jeju.nanaland.ui.languageselection.LanguageSelectionScreen
import com.jeju.nanaland.ui.main.MainScreen
import com.jeju.nanaland.ui.festival.FestivalContentScreen
import com.jeju.nanaland.ui.festival.FestivalListScreen
import com.jeju.nanaland.ui.festival.FestivalListViewModel
import com.jeju.nanaland.ui.infomodification.InformationModificationProposalScreen
import com.jeju.nanaland.ui.main.MainViewModel
import com.jeju.nanaland.ui.main.favorite.FavoriteViewModel
import com.jeju.nanaland.ui.main.home.search.SearchViewModel
import com.jeju.nanaland.ui.market.MarketContentScreen
import com.jeju.nanaland.ui.market.MarketListScreen
import com.jeju.nanaland.ui.market.MarketListViewModel
import com.jeju.nanaland.ui.nanapick.NanaPickContentScreen
import com.jeju.nanaland.ui.nanapick.NanaPickListScreen
import com.jeju.nanaland.ui.nature.NatureContentScreen
import com.jeju.nanaland.ui.nature.NatureListScreen
import com.jeju.nanaland.ui.nature.NatureListViewModel
import com.jeju.nanaland.ui.signin.SignInScreen
import com.jeju.nanaland.ui.splash.SplashScreen
import com.jeju.nanaland.util.navigation.navigate
import com.jeju.nanaland.util.type.getCategoryType

@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        modifier = Modifier.fillMaxSize(),
        navController = navController,
        startDestination = ROUTE_LANGUAGE_SELECTION,
//        startDestination = ROUTE_SPLASH
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
            SignInScreen()
        }

        // 메인 화면
        composable(route = ROUTE_MAIN) {
            MainScreen(
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
                    }
                },
                moveToNanaPickListScreen = { navController.navigate(ROUTE_NANAPICK_LIST) { launchSingleTop = true } },
                moveToNatureListScreen = { navController.navigate(ROUTE_NATURE_LIST) { launchSingleTop = true } },
                moveToFestivalListScreen = { navController.navigate(ROUTE_FESTIVAL_LIST) { launchSingleTop = true } },
                moveToMarketListScreen = { navController.navigate(ROUTE_MARKET_LIST) { launchSingleTop = true } },
                moveToExperienceListScreen = { /* MVP2 구현 예정 */ }
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
            val parentEntry = remember(it) { navController.previousBackStackEntry!! }
            val isSearch = it.arguments?.getBoolean("isSearch") ?: false
            val updatePrevScreenListFavorite: (Long, Boolean) -> Unit = when (parentEntry.destination.route) {
                ROUTE_NATURE_LIST -> {
                    val viewModel: NatureListViewModel = hiltViewModel(parentEntry)
                    viewModel::toggleFavoriteWithNoApi
                }
                ROUTE_MAIN -> {
                    val mainViewModel: MainViewModel = hiltViewModel(parentEntry)
                    val func = when (mainViewModel.viewType.collectAsState().value) {
                        MainScreenViewType.Home -> {
                            val viewModel: SearchViewModel = hiltViewModel(parentEntry)
                            val tmp = { contentId: Long, isFavorite: Boolean ->
                                viewModel.toggleSearchResultFavoriteWithNoApi(contentId, isFavorite)
                                viewModel.toggleAllSearchResultFavoriteWithNoApi(contentId, isFavorite, "NATURE")
                            }
                            tmp
                        }
                        MainScreenViewType.Favorite -> {
                            val viewModel: FavoriteViewModel = hiltViewModel(parentEntry)
                            val tmp = { contentId: Long, _: Boolean ->
                                viewModel.toggleFavoriteWithNoApi(contentId)
                            }
                            tmp
                        }
                        MainScreenViewType.JejuStory -> {
                            val tmp = { _: Long, _: Boolean ->
                            }
                            tmp
                        }
                        MainScreenViewType.MyNana -> {
                            val tmp = { _: Long, _: Boolean ->
                            }
                            tmp
                        }
                    }
                    func
                }
                else -> { _, _ -> }
            }
            NatureContentScreen(
                contentId = it.arguments?.getLong("contentId"),
                isSearch = isSearch,
                updatePrevScreenListFavorite = updatePrevScreenListFavorite,
                moveToBackScreen = { navController.popBackStack() },
                moveToInfoModificationProposalScreen = { navController.navigate(ROUTE_INFORMATION_MODIFICATION_PROPOSAL) }
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
            val parentEntry = remember(it) { navController.previousBackStackEntry!! }
            val isSearch = it.arguments?.getBoolean("isSearch") ?: false
            val updatePrevScreenListFavorite: (Long, Boolean) -> Unit = when (parentEntry.destination.route) {
                ROUTE_FESTIVAL_LIST -> {
                    val viewModel: FestivalListViewModel = hiltViewModel(parentEntry)
                    viewModel::toggleFavoriteWithNoApi
                }
                ROUTE_MAIN -> {
                    val mainViewModel: MainViewModel = hiltViewModel(parentEntry)
                    val func = when (mainViewModel.viewType.collectAsState().value) {
                        MainScreenViewType.Home -> {
                            val viewModel: SearchViewModel = hiltViewModel(parentEntry)
                            val tmp = { contentId: Long, isFavorite: Boolean ->
                                viewModel.toggleSearchResultFavoriteWithNoApi(contentId, isFavorite)
                                viewModel.toggleAllSearchResultFavoriteWithNoApi(contentId, isFavorite, "NATURE")
                            }
                            tmp
                        }
                        MainScreenViewType.Favorite -> {
                            val viewModel: FavoriteViewModel = hiltViewModel(parentEntry)
                            val tmp = { contentId: Long, _: Boolean ->
                                viewModel.toggleFavoriteWithNoApi(contentId)
                            }
                            tmp
                        }
                        MainScreenViewType.JejuStory -> {
                            val tmp = { _: Long, _: Boolean ->
                            }
                            tmp
                        }
                        MainScreenViewType.MyNana -> {
                            val tmp = { _: Long, _: Boolean ->
                            }
                            tmp
                        }
                    }
                    func
                }
                else -> { _, _ -> }
            }
            FestivalContentScreen(
                contentId = it.arguments?.getLong("contentId"),
                isSearch = isSearch,
                updatePrevScreenListFavorite = updatePrevScreenListFavorite,
                moveToBackScreen = { navController.popBackStack() },
                moveToInfoModificationProposalScreen = { navController.navigate(ROUTE_INFORMATION_MODIFICATION_PROPOSAL) }
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
            val parentEntry = remember(it) { navController.previousBackStackEntry!! }
            val isSearch = it.arguments?.getBoolean("isSearch") ?: false
            val updatePrevScreenListFavorite: (Long, Boolean) -> Unit = when (parentEntry.destination.route) {
                ROUTE_MARKET_LIST -> {
                    val viewModel: MarketListViewModel = hiltViewModel(parentEntry)
                    viewModel::toggleFavoriteWithNoApi
                }
                ROUTE_MAIN -> {
                    val mainViewModel: MainViewModel = hiltViewModel(parentEntry)
                    val func = when (mainViewModel.viewType.collectAsState().value) {
                        MainScreenViewType.Home -> {
                            val viewModel: SearchViewModel = hiltViewModel(parentEntry)
                            val tmp = { contentId: Long, isFavorite: Boolean ->
                                viewModel.toggleSearchResultFavoriteWithNoApi(contentId, isFavorite)
                                viewModel.toggleAllSearchResultFavoriteWithNoApi(contentId, isFavorite, "NATURE")
                            }
                            tmp
                        }
                        MainScreenViewType.Favorite -> {
                            val viewModel: FavoriteViewModel = hiltViewModel(parentEntry)
                            val tmp = { contentId: Long, _: Boolean ->
                                viewModel.toggleFavoriteWithNoApi(contentId)
                            }
                            tmp
                        }
                        MainScreenViewType.JejuStory -> {
                            val tmp = { _: Long, _: Boolean ->
                            }
                            tmp
                        }
                        MainScreenViewType.MyNana -> {
                            val tmp = { _: Long, _: Boolean ->
                            }
                            tmp
                        }
                    }
                    func
                }
                else -> { _, _ -> }
            }
            MarketContentScreen(
                contentId = it.arguments?.getLong("contentId"),
                isSearch = isSearch,
                updatePrevScreenListFavorite = updatePrevScreenListFavorite,
                moveToBackScreen = { navController.popBackStack() },
                moveToInfoModificationProposalScreen = { navController.navigate(ROUTE_INFORMATION_MODIFICATION_PROPOSAL) }
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

        // 정보 수정 제안 화면
        composable(route = ROUTE_INFORMATION_MODIFICATION_PROPOSAL) {
            InformationModificationProposalScreen(
                moveToBackScreen = { navController.popBackStack() }
            )
        }
    }
}