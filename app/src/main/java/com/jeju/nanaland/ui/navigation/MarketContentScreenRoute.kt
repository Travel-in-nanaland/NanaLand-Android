package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.ui.market.MarketContentScreen

fun NavGraphBuilder.marketContentScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Market.Detail> {
    val data: ROUTE.Content.Market.Detail = it.toRoute()

//    TODO
//    val parentEntry = remember(it) { navController.previousBackStackEntry!! }
//    val isSearch = it.arguments?.getBoolean("isSearch") ?: false
//    val updatePrevScreenListFavorite: (Int, Boolean) -> Unit = when (parentEntry.destination.route) {
//        ROUTE_MARKET_LIST -> {
//            val viewModel: MarketListViewModel = hiltViewModel(parentEntry)
//            viewModel::toggleFavoriteWithNoApi
//        }
//        ROUTE_MAIN -> {
//            val mainViewModel: MainViewModel = hiltViewModel(parentEntry)
//            val func = when (mainViewModel.viewType.collectAsState().value) {
//                MainScreenViewType.Home -> {
//                    val viewModel: SearchViewModel = hiltViewModel(parentEntry)
//                    val tmp = { contentId: Int, isFavorite: Boolean ->
//                        viewModel.toggleSearchResultFavoriteWithNoApi(contentId, isFavorite)
//                        viewModel.toggleAllSearchResultFavoriteWithNoApi(contentId, isFavorite, "NATURE")
//                    }
//                    tmp
//                }
//                MainScreenViewType.Favorite -> {
//                    val viewModel: FavoriteViewModel = hiltViewModel(parentEntry)
//                    val tmp = { contentId: Int, _: Boolean ->
//                        viewModel.toggleFavoriteWithNoApi(contentId)
//                    }
//                    tmp
//                }
//                MainScreenViewType.NanaPick -> {
//                    val tmp = { _: Int, _: Boolean ->
//                    }
//                    tmp
//                }
//                MainScreenViewType.MyPage -> {
//                    val tmp = { _: Int, _: Boolean ->
//                    }
//                    tmp
//                }
//            }
//            func
//        }
//        else -> { _, _ -> }
//    }
    MarketContentScreen(
        contentId = data.contentId,
        isSearch = data.isSearch,
        updatePrevScreenListFavorite = {_,_ -> /*updatePrevScreenListFavorite*/ },
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToInfoModificationProposalScreen = {
            navViewModel.navigate(ROUTE.InformationModification(data.contentId, "MARKET"))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main) }
    )
}