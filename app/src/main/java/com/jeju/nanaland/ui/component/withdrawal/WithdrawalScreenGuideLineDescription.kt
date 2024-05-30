package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun WithdrawalScreenGuideLineDescription(text: String) {
    Text(
        text = text,
        color = getColor().gray01,
        style = caption01
    )
}