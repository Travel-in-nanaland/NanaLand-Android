package com.jeju.nanaland.ui.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun LocationPolicyDetailsScreen() {
    Text(
        text = "위치기반 서비스 약관 동의",
        color = getColor().black
    )
}