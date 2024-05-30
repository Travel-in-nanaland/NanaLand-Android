package com.jeju.nanaland.ui.component.withdrawal.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold

@Composable
fun WithdrawalScreenDialogHeading() {
    Text(
        text = "정말 제주도 여행 정보와 헤택을\n 받지 않으시겠습니까? \uD83D\uDE22",
        color = getColor().black,
        style = title01Bold,
        textAlign = TextAlign.Center
    )
}