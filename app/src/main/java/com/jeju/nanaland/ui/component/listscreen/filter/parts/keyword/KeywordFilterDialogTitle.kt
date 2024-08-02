package com.jeju.nanaland.ui.component.listscreen.filter.parts.keyword

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold

@Composable
fun KeywordFilterDialogTitle() {
    Text(
        text = "키워드",
        color = getColor().black,
        style = title02Bold
    )
}