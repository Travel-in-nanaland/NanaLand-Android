package com.jeju.nanaland.ui.component.signup.profilesetting.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun SignUpScreenTextFieldHint() {
    Text(
        text = "이름을 입력해 주세요",
        color = getColor().gray01,
        style = body02
    )
}