package com.jeju.nanaland.ui.notification

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.jeju.nanaland.domain.entity.notification.NotificationType
import com.jeju.nanaland.domain.navigation.NavViewModel
import com.jeju.nanaland.domain.navigation.ROUTE

fun NavGraphBuilder.notificationScreen(navViewModel: NavViewModel) = composable<ROUTE.Main.Home.Notification> {
    NotificationScreen(
        moveToBackScreen = { navViewModel.popBackStack() },
        moveToContentScreen = { id, category ->
            when(category) {
                NotificationType.NATURE -> navViewModel.navigate(ROUTE.Content.Nature.Detail(id))
                NotificationType.FESTIVAL -> navViewModel.navigate(ROUTE.Content.Festival.Detail(id))
                NotificationType.MARKET -> navViewModel.navigate(ROUTE.Content.Market.Detail(id))
                NotificationType.EXPERIENCE -> navViewModel.navigate(ROUTE.Content.Experience.Detail(false,id))
                NotificationType.NANA -> navViewModel.navigate(ROUTE.Main.NanaPick.Detail(id))
                NotificationType.RESTAURANT -> navViewModel.navigate(ROUTE.Content.Restaurant.Detail(id))
                else -> {}
            }

        },
        moveToNoticeScreen = {
            navViewModel.navigate(ROUTE.Main.Profile.NoticeList.NoticeDetail(it))
        },
    )
}
