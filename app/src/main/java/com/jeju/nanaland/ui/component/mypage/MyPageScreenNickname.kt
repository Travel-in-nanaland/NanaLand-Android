package com.jeju.nanaland.ui.component.mypage

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02

@Composable
fun ColumnScope.MyPageScreenNickname(nickname: String) {
    Text(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        text = nickname,
        color = getColor().black,
        style = largeTitle02
    )
}