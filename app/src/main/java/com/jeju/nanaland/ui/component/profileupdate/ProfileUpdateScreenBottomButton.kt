package com.jeju.nanaland.ui.component.profileupdate

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.BottomOkButton
import com.jeju.nanaland.util.resource.getString

@Composable
fun ProfileUpdateScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit
) {
    BottomOkButton(
        text = getString(R.string.profile_update_screen_수정_완료),
        isActivated = isActivated,
        onClick = onClick
    )
}