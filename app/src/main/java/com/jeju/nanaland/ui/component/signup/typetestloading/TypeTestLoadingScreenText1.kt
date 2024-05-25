package com.jeju.nanaland.ui.component.signup.typetestloading

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02

@Composable
fun TypeTestLoadingScreenText1() {
    Text(
        text = "감귤이 착즙 되는 중...",
        color = getColor().main,
        style = largeTitle02
    )
}