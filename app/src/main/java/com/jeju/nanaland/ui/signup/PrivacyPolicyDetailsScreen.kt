package com.jeju.nanaland.ui.signup

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun PrivacyPolicyDetailsScreen() {
    Text(
        text = "이용약관 동의 및 개인정보 처리방침",
        color = getColor().black
    )
}