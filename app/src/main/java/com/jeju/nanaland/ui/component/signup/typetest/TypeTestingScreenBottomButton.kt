package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun TypeTestingScreenBottomButton(
    isActivated: Boolean,
    onClick: () -> Unit
) {
    BottomOkButton(
        text = "다음",
        isActivated = isActivated,
        onClick = onClick
    )
}