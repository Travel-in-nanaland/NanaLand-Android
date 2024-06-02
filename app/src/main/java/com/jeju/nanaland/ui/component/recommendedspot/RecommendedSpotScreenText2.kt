package com.jeju.nanaland.ui.component.recommendedspot

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun RecommendedSpotScreenText2() {
    Text(
        text = "추천 여행지",
        color = getColor().main,
        style = largeTitle01
    )
}