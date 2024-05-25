package com.jeju.nanaland.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_MARKET_LIST
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.ui.main.MainViewModel
import com.jeju.nanaland.ui.main.favorite.FavoriteViewModel
import com.jeju.nanaland.ui.main.home.search.SearchViewModel
import com.jeju.nanaland.ui.market.MarketContentScreen
import com.jeju.nanaland.ui.market.MarketListViewModel

fun NavGraphBuilder.marketContentScreen(navController: NavController) = composable(route = ROUTE_MARKET_CONTENT) {
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
                MainScreenViewType.MyPage -> {
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
        moveToInfoModificationProposalScreen = { navController.navigate(
            ROUTE_INFORMATION_MODIFICATION_PROPOSAL
        ) }
    )
}