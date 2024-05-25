package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun HomeScreenAdText2(idx: Int) {
    Text(
        text = when (idx % 4) {
            0 -> "서귀포시 성산의 자연으로 투어해보자!!"
            1 -> "애월의 감성 가득 오름들 확인\uD83D\uDE2F"
            2 -> "아직도 남은 전통 시장들은 뭐가 있을까?"
            else -> "세계의 보물, 제주도가 가득 느껴지는 축제 보러가자"
        },
        color = when (idx % 4) {
            1 -> getColor().white
            else -> getColor().black
        },
        style = caption01
    )
}