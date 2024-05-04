package com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.largeTitle01
import com.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun NanaPickContentTopBannerTitle(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().white,
        style = largeTitle01
    )
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerTitlePreview() {
    NanaLandTheme {
        NanaPickContentTopBannerTitle(text = "Title")
    }
}