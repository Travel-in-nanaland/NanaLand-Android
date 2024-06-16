package com.jeju.nanaland.ui.component.signup.recommendedspot.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.type.getCategoryType

@Composable
fun RecommendedSpotScreenSpotName(text: String) {
    Text(
        text = text,
        color = getColor().white,
        style = largeTitle02
    )
}