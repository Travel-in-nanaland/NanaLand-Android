package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButton

@Composable
fun TypeTestResultScreenBottomButton2(onClick: () -> Unit) {
    BottomOkButton(
        text = "메인 화면 바로가기",
        isActivated = true,
        onClick = onClick
    )
}