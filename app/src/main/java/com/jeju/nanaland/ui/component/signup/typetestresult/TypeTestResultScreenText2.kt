package com.jeju.nanaland.ui.component.signup.typetestresult

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun TypeTestResultScreenText2() {
    Text(
        text = "감귤 아이스크림",
        color = getColor().main,
        style = largeTitle01
    )
}