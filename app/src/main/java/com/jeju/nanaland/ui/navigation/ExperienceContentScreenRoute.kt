package com.jeju.nanaland.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE

fun NavGraphBuilder.experienceContentScreen(navViewModel: NavViewModel) = composable<ROUTE.Content.Experience.Art> {
//    TODO
//    val parentEntry = remember(it) { navController.previousBackStackEntry!! }
//    val isSearch = it.arguments?.getBoolean("isSearch") ?: false
//    val updatePrevScreenListFavorite: (Int, Boolean) -> Unit = when (parentEntry.destination.route) {
//        ROUTE_EXPERIENCE_LIST -> {
//            val viewModel: ExperienceListViewModel = hiltViewModel(parentEntry)
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

//    ExperienceContentScreen(
//        experienceCategory = it.arguments?.getString("experienceCategoryType") ?: "",
//        contentId = it.arguments?.getInt("contentId"),
//        isSearch = isSearch,
//        updatePrevScreenListFavorite = updatePrevScreenListFavorite,
//        moveToBackScreen = { navController.popBackStack() },
//        moveToInfoModificationProposalScreen = {
//            val bundle = bundleOf(
//                "postId" to it.arguments?.getInt("contentId"),
//                "category" to "EXPERIENCE"
//            )
//            navController.navigate(ROUTE_INFORMATION_MODIFICATION_PROPOSAL_CATEGORY, bundle)
//        },
//        moveToReviewWritingScreen = { id, image, title, address ->
//            LogUtil.e("moveToReviewWritingScreen", "moveToReviewWritingScreen")
//            val bundle = bundleOf(
//                "id" to id,
//                "category" to ReviewCategoryType.EXPERIENCE.toString()
//            )
//            navController.navigate(ROUTE_REVIEW_WRITE_ROUTE, bundle)
//        },
//        moveToSignInScreen = { navController.navigate(ROUTE_SIGN_IN) {
//            popUpTo(ROUTE_MAIN) { inclusive = true }
//            launchSingleTop = true
//        } },
//        moveToReviewListScreen = { isFavorite, image, title, address ->
//            val bundle = bundleOf(
//                "isFavorite" to isFavorite,
//                "contentId" to it.arguments?.getInt("contentId"),
//                "category" to ReviewCategoryType.EXPERIENCE.toString(),
//                "image" to image,
//                "title" to title,
//                "address" to address
//            )
//            navController.navigate(ROUTE_REVIEW_LIST, bundle)
//        },
//        moveToReportScreen = { navController.navigate(ROUTE.Report(it, true)) },
//        moveToProfileScreen = { navController.navigate(ROUTE.Profile.StartDest(it)) }
//    )
}