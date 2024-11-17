package com.jeju.nanaland.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.ui.main.MainViewModel
import com.jeju.nanaland.ui.main.favorite.FavoriteViewModel
import com.jeju.nanaland.ui.main.home.search.SearchViewModel
import com.jeju.nanaland.ui.market.MarketContentScreen
import com.jeju.nanaland.ui.market.MarketListViewModel

fun NavGraphBuilder.marketContentScreen(
    navViewModel: NavViewModel,
    getBackStackEntry: () -> NavBackStackEntry
) = composable<ROUTE.Content.Market.Detail> {
    val data: ROUTE.Content.Market.Detail = it.toRoute()

    val parentEntry = remember(it) { getBackStackEntry() }
    val isSearch = it.arguments?.getBoolean("isSearch") ?: false
    val updatePrevScreenListFavorite: (Int, Boolean) -> Unit = when {
        parentEntry.destination.route?.contains(ROUTE.Content.Market::class.qualifiedName.toString()) == true -> {
            val viewModel: MarketListViewModel = hiltViewModel(parentEntry)
            viewModel::toggleFavoriteWithNoApi
        }
        parentEntry.destination.route?.contains(ROUTE.Main::class.qualifiedName.toString()) == true -> {
            val mainViewModel: MainViewModel = hiltViewModel(parentEntry)
            val func = when (mainViewModel.viewType.collectAsState().value) {
                MainScreenViewType.Home -> {
                    val viewModel: SearchViewModel = hiltViewModel(parentEntry)
                    val tmp = { contentId: Int, isFavorite: Boolean ->
                        viewModel.toggleSearchResultFavoriteWithNoApi(contentId, isFavorite)
                        viewModel.toggleAllSearchResultFavoriteWithNoApi(contentId, isFavorite, "NATURE")
                    }
                    tmp
                }
                MainScreenViewType.Favorite -> {
                    val viewModel: FavoriteViewModel = hiltViewModel(parentEntry)
                    val tmp = { contentId: Int, _: Boolean ->
                        viewModel.toggleFavoriteWithNoApi(contentId)
                    }
                    tmp
                }
                MainScreenViewType.NanaPick -> {
                    val tmp = { _: Int, _: Boolean ->
                    }
                    tmp
                }
                MainScreenViewType.MyPage -> {
                    val tmp = { _: Int, _: Boolean ->
                    }
                    tmp
                }
            }
            func
        }
        else -> { _, _ -> }
    }
    MarketContentScreen(
        contentId = data.contentId,
        isSearch = data.isSearch,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToInfoModificationProposalScreen = {
            navViewModel.navigate(ROUTE.InformationModification(data.contentId, "MARKET"))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main) }
    )
}