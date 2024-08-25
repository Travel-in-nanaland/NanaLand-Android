package com.jeju.nanaland.ui.profile.profileNoticeList

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.profile.component.ProfileListFrame
import com.jeju.nanaland.ui.profile.root.ProfileViewModel
import com.jeju.nanaland.ui.profile.component.parts.ProfileNoticeRow
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
        data = notices
    ) {
        ProfileNoticeRow(
            data = it,
            onClick = moveToProfileNoticeListScreen
        )
    }
}