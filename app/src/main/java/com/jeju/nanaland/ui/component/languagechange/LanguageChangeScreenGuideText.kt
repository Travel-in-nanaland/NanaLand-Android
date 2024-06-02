package com.jeju.nanaland.ui.component.languagechange

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02

@Composable
fun LanguageChangeScreenGuideText() {
    Text(
        text = "맨 위에 있는 언어로\n기본 설정 되어 있습니다.",
        color = getColor().black,
        style = title02
    )
}