//package com.jeju.nanaland.ui.navigation
//
//import androidx.core.os.bundleOf
//import androidx.navigation.NavController
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.compose.composable
//import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_CONTENT
//import com.jeju.nanaland.globalvalue.constant.ROUTE_NANAPICK_LIST
//import com.jeju.nanaland.ui.nanapick.NanaPickListScreen
//import com.jeju.nanaland.util.navigation.navigate
//
//fun NavGraphBuilder.nanaPickListScreen(navViewModel: NavViewModel) = composable(route = ROUTE_NANAPICK_LIST) {
////    NanaPickListScreen(
////        moveToNanaPickContentScreen = { contentId ->
////            val bundle = bundleOf(
////                "contentId" to contentId
////            )
////            navController.navigate(ROUTE_NANAPICK_CONTENT, bundle)
////        },
////        moveToMainScreen = { navController.popBackStack() }
////    )
//}