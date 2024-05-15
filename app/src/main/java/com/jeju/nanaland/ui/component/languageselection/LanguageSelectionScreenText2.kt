package com.jeju.nanaland.ui.component.languageselection

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun LanguageSelectionScreenText2() {
    Text(
        text = "언어를 선택해주세요",
        color = getColor().black,
        style = largeTitle01
    )
}