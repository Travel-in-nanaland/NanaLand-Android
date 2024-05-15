package com.jeju.nanaland.ui.component.languageselection

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jeju.nanaland.ui.theme.appleSdGothicNeo
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun LanguageSelectionScreenText1() {
    Text(
        text = "안녕하세요!",
        color = getColor().black,
        fontFamily = appleSdGothicNeo,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
    )
}