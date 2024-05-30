package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold

@Composable
fun WithdrawalScreenGuideLineHeading() {
    Text(
        text = "서비스 탈퇴 안내 사항",
        color = getColor().black,
        style = title02Bold
    )
}