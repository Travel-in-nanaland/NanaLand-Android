package com.jeju.nanaland.ui.component.languageinitialization

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun LanguageInitializationScreenText2() {
    Text(
        text = "언어를 선택해주세요",
        color = getColor().black,
        style = largeTitle01
    )
}