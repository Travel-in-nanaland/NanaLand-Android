package com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body01
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview

@Composable
fun NanaPickContentSubContentDescription(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body01
    )
}

@ComponentPreview
@Composable
private fun NanaPickContentSubContentDescriptionPreview() {
    NanaLandTheme {
        NanaPickContentSubContentDescription("Description")
    }
}