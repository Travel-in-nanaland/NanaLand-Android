package com.jeju.nanaland.ui.component.mypage

import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.component.common.BottomOkButtonOutlined

@Composable
fun MyPageScreenBottomButton(onClick: () -> Unit) {
    BottomOkButtonOutlined(
        text = "프로필 수정",
        onClick = onClick
    )
}