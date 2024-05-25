package com.jeju.nanaland.ui.component.mypage

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold

@Composable
fun MyPageScreenTravelType(text: String) {
    Text(
        text = text,
        color = getColor().main,
        style = title02Bold
    )
}