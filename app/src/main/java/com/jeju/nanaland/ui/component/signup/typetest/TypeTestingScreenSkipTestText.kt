package com.jeju.nanaland.ui.component.signup.typetest

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun TypeTestingScreenSkipTestText(onClick: () -> Unit) {
    Text(
        modifier = Modifier.clickableNoEffect { onClick() },
        text = "테스트 건너뛰기",
        color = getColor().gray01,
        style = body02
    )
}