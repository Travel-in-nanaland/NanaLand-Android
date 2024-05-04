package com.nanaland.ui.component.detailscreen.other.parts.description

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title01Bold
import com.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenDescriptionTitle(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = title01Bold
    )
}

@ComponentPreview
@Composable
private fun DetailScreenDescriptionTitlePreview() {
    NanaLandTheme {
        DetailScreenDescriptionTitle(text = "Title")
    }
}