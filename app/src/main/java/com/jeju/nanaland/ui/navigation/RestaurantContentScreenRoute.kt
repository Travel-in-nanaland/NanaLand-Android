package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE
import com.jeju.nanaland.globalvalue.type.ReviewCategoryType
import com.jeju.nanaland.ui.restaurant.RestaurantContentScreen

fun NavGraphBuilder.restaurantContentScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Restaurant.Detail> {
    val data: ROUTE.Content.Restaurant.Detail = it.toRoute()
//    TODO
//    val parentEntry = remember(it) { navController.previousBackStackEntry!! }
//    val isSearch = it.arguments?.getBoolean("isSearch") ?: false
//    val updatePrevScreenListFavorite: (Int, Boolean) -> Unit = when (parentEntry.destination.route) {
//        ROUTE_RESTAURANT_LIST -> {
//            val viewModel: RestaurantListViewModel = hiltViewModel(parentEntry)
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

    RestaurantContentScreen(
        contentId = data.contentId,
        isSearch = data.isSearch,
        updatePrevScreenListFavorite = {_,_ -> /*updatePrevScreenListFavorite*/ },
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToInfoModificationProposalScreen = {
            navViewModel.navigate(ROUTE.InformationModification(data.contentId, "RESTAURANT"))
        },
        moveToReviewWritingScreen = { id, image, title, address ->
            navViewModel.navigate(ROUTE.Content.ReviewWrite(id, ReviewCategoryType.RESTAURANT.toString()))
        },
        moveToSignInScreen = { navViewModel.navigatePopUpTo(ROUTE.Splash.SignIn, ROUTE.Main) },
        moveToReviewListScreen = { isFavorite, image, title, address ->
            navViewModel.navigate(ROUTE.Content.ReviewList(
                isFavorite,
                data.contentId,
                ReviewCategoryType.RESTAURANT.toString(),
                image,
                title,
                address
            ))
        },
        moveToReportScreen = { navViewModel.navigate(ROUTE.Report(it, true)) },
        moveToProfileScreen = { navViewModel.navigate(ROUTE.Main.Profile.StartDest(it)) }
    )
}