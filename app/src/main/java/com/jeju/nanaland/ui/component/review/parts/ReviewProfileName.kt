package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun ReviewProfileName(name: String) {
    Text(
        text = name,
        color = getColor().black,
        style = body02Bold
    )
}