package com.jeju.nanaland.ui.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun MarketingPolicyDetailsScreen() {
    Text(
        text = "마케팅 활용 동의",
        color = getColor().black
    )
}