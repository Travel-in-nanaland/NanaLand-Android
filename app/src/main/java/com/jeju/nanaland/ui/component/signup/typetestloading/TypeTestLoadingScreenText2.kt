package com.jeju.nanaland.ui.component.signup.typetestloading

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02

@Composable
fun TypeTestLoadingScreenText2() {
    Text(
        text = "맛있는 여행 주스가 곧 나옵니다 !",
        color = getColor().black,
        style = title02
    )
}