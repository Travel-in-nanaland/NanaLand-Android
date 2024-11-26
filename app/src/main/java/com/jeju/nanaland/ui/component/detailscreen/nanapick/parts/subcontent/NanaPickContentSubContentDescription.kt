package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun NanaPickContentSubContentDescription(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body02
    )
}

@ComponentPreview
@Composable
private fun NanaPickContentSubContentDescriptionPreview() {
    NanaLandTheme {
        NanaPickContentSubContentDescription("Description")
    }
}