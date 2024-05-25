package com.jeju.nanaland.ui.component.policyagree

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun PolicyAgreeScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit
) {
    BottomOkButton(
        text = "확인",
        isActivated = isActivated,
        onClick = onClick
    )
}