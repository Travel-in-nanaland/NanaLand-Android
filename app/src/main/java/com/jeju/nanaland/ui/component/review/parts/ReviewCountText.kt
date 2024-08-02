package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun ReviewCountText(count: Int) {
    Text(
        text = "리뷰 ${count}",
        color = getColor().black,
        style = caption01
    )
}