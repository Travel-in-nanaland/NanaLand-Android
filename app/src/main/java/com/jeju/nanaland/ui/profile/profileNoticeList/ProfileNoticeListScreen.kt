package com.jeju.nanaland.ui.profile.profileNoticeList

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.profile.component.ProfileListFrame
import com.jeju.nanaland.ui.profile.component.parts.ProfileNoticeRow
import com.jeju.nanaland.ui.profile.root.ProfileViewModel
import com.jeju.nanaland.ui.theme.body01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString

@Composable
fun ProfileNoticeListScreen(
    moveToProfileNoticeListScreen: (Int) -> Unit,
    moveToBackScreen: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val notices = viewModel.notices.collectAsLazyPagingItems()

    ProfileListFrame(
        title = getString(R.string.common_공지사항),
        initialScrollToItem = -1,
        moveToBackScreen = moveToBackScreen,
        data = notices,
        emptyView = { EmptyView() }
    ) {
        ProfileNoticeRow(
            data = it,
            onClick = moveToProfileNoticeListScreen
        )
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
            modifier = Modifier.size(113.dp),
            painter = painterResource(id = R.drawable.img_empty_notice),
            contentDescription = null
        )
        Text(
            text = getString(R.string.mypage_screen_notice_empty),
            style = body01,
            color = getColor().gray01,
            textAlign = TextAlign.Center
        )
    }
}