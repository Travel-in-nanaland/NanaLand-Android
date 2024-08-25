package com.jeju.nanaland.ui.notification

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.CustomSurface
import com.jeju.nanaland.ui.component.common.topbar.CustomTopBar
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
    moveToNanaPickScreen: (Int) -> Unit,
    moveToNoticeScreen: (Int) -> Unit,
    moveToFavoriteScreen: (Int) -> Unit,
    viewModel: NotificationViewModel = hiltViewModel()
) {
    CustomSurface {
        Column(Modifier.fillMaxSize()) {
            CustomTopBar(
                title = getString(R.string.common_알림),
                onBackButtonClicked = moveToBackScreen
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height((24 - 8).dp))
                }

                items(viewModel.data) {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .clickableNoEffect {
                                when(it.type) {
                                    TEMP_NotificationType.ISSUE -> moveToNoticeScreen(1/*TODO*/)
                                    TEMP_NotificationType.NANA_PICK -> moveToNanaPickScreen(1249 /*TODO*/)
                                    TEMP_NotificationType.LIKE -> moveToFavoriteScreen(it.id)
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
                                text = it.title,
                                color = getColor().main,
                                style = body02Bold
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = it.subTitle,
                                color = getColor().black,
                                style = caption01
                            )
                            Text(
                                text = it.date.toFormatString(),
                                color = getColor().gray01,
                                style = caption02
                            )
                        }
                    }
                }
            }
        }
    }
}
