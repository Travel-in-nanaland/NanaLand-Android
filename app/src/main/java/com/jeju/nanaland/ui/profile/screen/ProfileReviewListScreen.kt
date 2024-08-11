package com.jeju.nanaland.ui.profile.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.DialogCommon
import com.jeju.nanaland.ui.profile.ProfileViewModel
import com.jeju.nanaland.ui.profile.component.ProfileListFrame
import com.jeju.nanaland.ui.profile.component.parts.ProfileListReviewRow
import com.jeju.nanaland.ui.profile.component.parts.ReportSheet
import com.jeju.nanaland.util.resource.getString

@Composable
fun ProfileReviewListScreen(
    moveToBackScreen: () -> Unit,
    moveToReviewReportScreen: (Int) -> Unit,
    initialScrollToItemId: Int = -1,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val reviews = viewModel.reviews.collectAsLazyPagingItems()
    val isMine = viewModel.userId == null

    var removeReviewId by remember { mutableIntStateOf(-1) }
    var reportReviewId by remember { mutableIntStateOf(-1) }

    ProfileListFrame(
        title = getString(R.string.common_후기),
        initialScrollToItem = run {
            if(initialScrollToItemId == -1) -1
            else reviews.itemSnapshotList.indexOfFirst { it?.id == initialScrollToItemId }
        },
        moveToBackScreen = moveToBackScreen,
        data = reviews
    ) {
        if(isMine)
            ProfileListReviewRow(
                data = it,
                onEdit = { data ->

                },
                onRemove = { data ->
                    removeReviewId = data.id
                }
            )
        else
            ProfileListReviewRow(
                data = it,
                onLike = { id, setLike ->
                    viewModel.setLike(id)
                },
                onReport = { id ->
                    reportReviewId = id
                }
            )
    }
    if(removeReviewId != -1)
        RemoveDialog(
            onDismissRequest = { removeReviewId = -1 },
            onDelete = { viewModel.setRemove(removeReviewId)}
        )

    if(reportReviewId != -1)
        ReportSheet(
            onDismissRequest = { reportReviewId = -1 },
            onReport = { moveToReviewReportScreen(reportReviewId) }
        )
}

@Composable
private fun RemoveDialog(
    onDismissRequest: () -> Unit,
    onDelete: ()->Unit
) {
    DialogCommon(
        onDismissRequest = onDismissRequest,
        title = getString(R.string.mypage_screen_review_remove_dialog),
//        modifier = {},
//        subTitle = {},
        onPositive = { onDelete() },
        onNegative = { onDismissRequest() },
    )
}