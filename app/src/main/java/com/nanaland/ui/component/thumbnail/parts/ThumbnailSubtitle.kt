package com.nanaland.ui.component.thumbnail.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.caption01
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview

@Composable
fun ThumbnailSubtitle(
    text: String?
) {
    Text(
        text = text ?: "",
        color = getColor().gray01,
        style = caption01
    )
}

@ComponentPreview
@Composable
private fun ThumbnailSubTitlePreview() {
    NanaLandTheme {
        ThumbnailSubtitle(
            text = "휴애리 유채꽃 축제"
        )
    }
}