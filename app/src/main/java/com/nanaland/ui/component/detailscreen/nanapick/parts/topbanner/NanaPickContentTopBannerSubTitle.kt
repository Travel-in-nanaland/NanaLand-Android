package com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title01Bold
import com.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun NanaPickContentTopBannerSubTitle(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().white,
        style = title01Bold
    )
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerSubTitlePreview() {
    NanaLandTheme {
        NanaPickContentTopBannerSubTitle(text = "SubTitle")
    }
}