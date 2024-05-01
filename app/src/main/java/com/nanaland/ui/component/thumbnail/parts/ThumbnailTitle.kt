package com.nanaland.ui.component.thumbnail.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body02Bold
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview

@Composable
fun ThumbnailText(
    text: String?
) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body02Bold
    )
}

@ComponentPreview
@Composable
private fun ThumbnailTextPreview() {
    NanaLandTheme {
        ThumbnailText(
            text = "제주도 축제"
        )
    }
}