package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun NanaPickContentTopBannerSubTitle(text: String?, isEllipsis: Boolean) {
    if(!isEllipsis)
        Text(
            text = text ?: "",
            color = getColor().white,
            style = title02Bold
        )
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerSubTitlePreview() {
    NanaLandTheme {
        NanaPickContentTopBannerSubTitle(text = "SubTitle",false)
    }
}