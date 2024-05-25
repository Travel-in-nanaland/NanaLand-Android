package com.jeju.nanaland.ui.component.signup.profilesetting

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun SignUpScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit
) {
    BottomOkButton(
        text = "확인",
        isActivated = isActivated,
        onClick = onClick
    )
}