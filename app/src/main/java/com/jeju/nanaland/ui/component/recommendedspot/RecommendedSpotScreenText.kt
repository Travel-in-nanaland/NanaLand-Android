package com.jeju.nanaland.ui.component.recommendedspot

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun RecommendedSpotScreenText1(text: String) {
    Text(
        text = text,
        color = getColor().black,
        style = largeTitle01
    )
}