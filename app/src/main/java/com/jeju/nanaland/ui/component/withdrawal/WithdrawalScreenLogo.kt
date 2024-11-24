package com.jeju.nanaland.ui.component.withdrawal

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02

@Composable
fun WithdrawalScreenLogo() {
    Text(
        text = "nanaland in Jeju",
        color = getColor().main,
        style = largeTitle02
    )
}