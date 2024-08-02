package com.jeju.nanaland.ui.profile.screen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.profile.ProfileViewModel
import com.jeju.nanaland.ui.profile.component.ProfileListFrame
import com.jeju.nanaland.ui.profile.component.parts.ProfileNoticeRow
import com.jeju.nanaland.util.resource.getString

@Composable
fun ProfileNoticeListScreen(
    moveToBackScreen: () -> Unit,
    initialScrollToItemId: Int = -1,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val notices = viewModel.notices.collectAsLazyPagingItems()

    ProfileListFrame(
        title = getString(R.string.common_공지사항),
        initialScrollToItem = run {
            if(initialScrollToItemId == -1) -1
            else notices.itemSnapshotList.indexOfFirst { it?.id == initialScrollToItemId }
        },
        moveToBackScreen = moveToBackScreen,
        data = notices
    ) {
        ProfileNoticeRow(
            data = it,
            onClick = {}
        )
    }
}