package com.jeju.nanaland.ui.component.profileupdate

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun ProfileUpdateScreenNicknameText() {
    Text(
        text = "닉네임",
        color = getColor().black,
        style = bodyBold
    )
}