package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButtonOutlined

@Composable
fun TypeTestResultScreenBottomButton1(onClick: () -> Unit) {
    BottomOkButtonOutlined(
        text = "감귤 아이스크림 여행지",
        onClick = onClick
    )
}