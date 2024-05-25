package com.jeju.nanaland.ui.component.signup.profilesetting

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun SignUpScreenGuideLine() {
    Text(
        text = "사용할 이름을 작성 및\n프로필을 선택해 주세요.",
        color = getColor().black,
        textAlign = TextAlign.Center,
        style = largeTitle01
    )
}