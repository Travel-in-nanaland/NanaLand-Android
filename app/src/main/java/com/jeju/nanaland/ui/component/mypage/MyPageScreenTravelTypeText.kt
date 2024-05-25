package com.jeju.nanaland.ui.component.mypage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun MyPageScreenTravelTypeText() {
    Text(
        text = "여행 유형",
        color = getColor().black,
        style = bodyBold
    )
}