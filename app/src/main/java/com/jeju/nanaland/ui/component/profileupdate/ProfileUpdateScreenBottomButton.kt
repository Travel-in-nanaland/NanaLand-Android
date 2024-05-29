package com.jeju.nanaland.ui.component.profileupdate

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun ProfileUpdateScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit
) {
    BottomOkButton(
        text = "수정 완료",
        isActivated = isActivated,
        onClick = onClick
    )
}