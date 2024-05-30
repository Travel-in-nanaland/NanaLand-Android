package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun WithdrawalScreenAgreeText() {
    Text(
        text = "안내사항을 확인하였으며 동의합니다.",
        color = getColor().black,
        style = body02Bold
    )
}