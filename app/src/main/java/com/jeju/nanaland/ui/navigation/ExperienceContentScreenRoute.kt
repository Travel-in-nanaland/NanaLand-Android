package com.jeju.nanaland.ui.navigation

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.core.os.bundleOf
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_CONTENT
import com.jeju.nanaland.globalvalue.constant.ROUTE_EXPERIENCE_LIST
import com.jeju.nanaland.globalvalue.constant.ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY
import com.jeju.nanaland.globalvalue.constant.ROUTE_MAIN
import com.jeju.nanaland.globalvalue.type.MainScreenViewType
import com.jeju.nanaland.ui.experience.ExperienceContentScreen
import com.jeju.nanaland.ui.experience.ExperienceListViewModel
import com.jeju.nanaland.ui.main.MainViewModel
import com.jeju.nanaland.ui.main.favorite.FavoriteViewModel
import com.jeju.nanaland.ui.main.home.search.SearchViewModel
import com.jeju.nanaland.util.navigation.navigate

fun NavGraphBuilder.experienceContentScreen(navController: NavController) = composable(route = ROUTE_EXPERIENCE_CONTENT) {
    val parentEntry = remember(it) { navController.previousBackStackEntry!! }
    val isSearch = it.arguments?.getBoolean("isSearch") ?: false
    val updatePrevScreenListFavorite: (Int, Boolean) -> Unit = when (parentEntry.destination.route) {
        ROUTE_EXPERIENCE_LIST -> {
            val viewModel: ExperienceListViewModel = hiltViewModel(parentEntry)
            viewModel::toggleFavoriteWithNoApi
        }
        ROUTE_MAIN -> {
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
                MainScreenViewType.JejuStory -> {
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

    ExperienceContentScreen(
        contentId = it.arguments?.getInt("contentId"),
        isSearch = isSearch,
        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
        moveToBackScreen = { navController.popBackStack() },
        moveToInfoModificationProposalScreen = {
            val bundle = bundleOf(
                "postId" to it.arguments?.getInt("contentId"),
                "category" to "EXPERIENCE"
            )
            navController.navigate(ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY, bundle)
        },
        moveToSignInScreen = {}
    )
}