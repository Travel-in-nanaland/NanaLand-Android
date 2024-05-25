package com.jeju.nanaland.ui.component.policyagree

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun PolicyAgreeScreenWelcomeText() {
    Text(
        text = "nanaland in Jeju에\n오신 것을 환영합니다.",
        color = getColor().black,
        style = largeTitle01
    )
}