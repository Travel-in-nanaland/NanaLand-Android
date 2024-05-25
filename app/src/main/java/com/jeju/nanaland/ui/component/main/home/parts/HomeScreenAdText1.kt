package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun HomeScreenAdText1(idx: Int) {
    Text(
        text = when (idx % 4) {
            0 -> "제주도에서 예쁜 바다 보고 싶다고?"
            1 -> "보기 귀한 별 보러 가지 않을래?"
            2 -> "한국의 정감을 느끼고 싶다면, 시장이지!"
            else -> "제주도에서만 여는 7월 축제\uD83C\uDF88"
        },
        color = when (idx % 4) {
            1 -> getColor().white
            else -> getColor().black
        },
        style = bodyBold
    )
}