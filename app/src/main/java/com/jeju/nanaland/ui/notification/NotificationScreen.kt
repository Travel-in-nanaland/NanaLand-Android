package com.jeju.nanaland.ui.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.domain.entity.notification.Notification
import com.jeju.nanaland.domain.entity.notification.NotificationLinkType
import com.jeju.nanaland.domain.entity.notification.NotificationType
import com.jeju.nanaland.ui.profile.component.ProfileListFrame
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.caption02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.string.toFormatString
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun NotificationScreen(
    moveToBackScreen: () -> Unit,
    moveToContentScreen: (Int, NotificationType) -> Unit,
    moveToNoticeScreen: (Int) -> Unit,
    viewModel: NotificationViewModel = hiltViewModel()
) {
    val data = viewModel.data.collectAsLazyPagingItems()
    ProfileListFrame(
        title = getString(R.string.common_알림),
        initialScrollToItem = -1,
        moveToBackScreen = moveToBackScreen,
        verticalArrangementSpace = 8.dp,
        data = data,
        emptyView = { EmptyView() }
    ) {
        NotiRow(
            data = it,
            moveToContentScreen = moveToContentScreen,
            moveToNoticeScreen = moveToNoticeScreen
        )
    }
}

@Composable
private fun NotiRow(
    data: Notification,
    moveToContentScreen: (Int, NotificationType) -> Unit,
    moveToNoticeScreen: (Int) -> Unit
){
    Row(
        modifier = Modifier
            .clickableNoEffect {
                when(data.clickEvent) {
                    NotificationLinkType.NOTICE -> {
                        moveToNoticeScreen(data.contentId)
                    }
                    NotificationLinkType.POST -> {
                        moveToContentScreen(data.contentId, data.category)
                    }
                    else -> {}
                }
            }
    ) {
        Icon(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(getColor().main)
                .padding(3.dp),
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = null,
            tint = getColor().white
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = data.title,
                color = getColor().main,
                style = body02Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            if(data.content.isNotBlank()) {
                Text(
                    text = data.content,
                    color = getColor().black,
                    style = caption01
                )
            }
            Text(
                text = data.createdAt.toFormatString(),
                color = getColor().gray01,
                style = caption02
            )
        }
    }
}



@Composable
private fun EmptyView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(140.dp),
            painter = painterResource(id = R.drawable.img_empty_notification),
            contentDescription = null
        )
        Spacer(Modifier.height(15.dp))
        Text(
            text = getString(R.string.notification_screen_empty),
            style = body01,
            color = getColor().gray01,
            textAlign = TextAlign.Center
        )
    }
}