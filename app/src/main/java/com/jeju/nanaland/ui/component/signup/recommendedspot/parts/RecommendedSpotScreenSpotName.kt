package com.jeju.nanaland.ui.component.signup.recommendedspot.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold

@Composable
fun RecommendedSpotScreenSpotName(text: String) {
    Text(
        text = text,
        color = getColor().white,
        style = title01Bold
    )
}