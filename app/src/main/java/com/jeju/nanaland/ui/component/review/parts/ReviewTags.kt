package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun ReviewTags(tags: List<String>) {
    Text(
        text = "${tags.joinToString(separator = " ") { "#$it" }}",
        color = getColor().main,
        style = caption01
    )
}