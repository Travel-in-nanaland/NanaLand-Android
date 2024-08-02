package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ExpandableText

@Composable
fun ReviewContent(text: String) {
    ExpandableText(
        text = text,
        minimizedMaxLines = 2
    )
}