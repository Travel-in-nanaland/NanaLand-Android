package com.jeju.nanaland.ui.component.signup.recommendedspot

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle01

@Composable
fun RecommendedSpotScreenText1(text: String) {
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = text,
        color = getColor().black,
        style = largeTitle01,
        textAlign = TextAlign.Center
    )
}