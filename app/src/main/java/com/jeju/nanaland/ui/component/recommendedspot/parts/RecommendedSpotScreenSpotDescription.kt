package com.jeju.nanaland.ui.component.recommendedspot.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun RecommendedSpotScreenSpotDescription(text: String) {
    Text(
        text = text,
        color = getColor().white,
        style = caption01
    )
}