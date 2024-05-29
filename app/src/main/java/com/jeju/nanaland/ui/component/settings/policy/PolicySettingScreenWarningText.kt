package com.jeju.nanaland.ui.component.settings.policy

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun PolicySettingScreenWarningText() {
    Text(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        text = "* 비동의시, 받지 못하는 서비스 또는 혜택이 있을 수 있으니 주의하시길 바랍니다.",
        color = getColor().gray01,
        style = caption01
    )
}