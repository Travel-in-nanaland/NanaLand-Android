package com.jeju.nanaland.ui.profile.component.parts

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.DialogCommon
import com.jeju.nanaland.util.resource.getString

@Composable
fun RemoveDialog(
    onDismissRequest: () -> Unit,
    onDelete: ()->Unit
) {
    DialogCommon(
        onDismissRequest = onDismissRequest,
        title = getString(R.string.mypage_screen_review_remove_dialog),
        onPositive = onDelete,
        onNegative = onDismissRequest,
    )
}