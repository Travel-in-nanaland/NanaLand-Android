package com.jeju.nanaland.ui.component.policyagree.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.bodySemiBold
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun PolicyAgreeScreenAgreeAllContentText() {
    Text(
        text = "전체 동의",
        color = getColor().black,
        style = bodySemiBold
    )
}