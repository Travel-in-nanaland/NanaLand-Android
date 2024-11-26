package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun NanaPickContentTopBannerTitle(text: String?, isEllipsis: Boolean) {
    Text(
        text = text ?: "",
        color = getColor().white,
        style = largeTitle02,
        maxLines = if (isEllipsis) 2 else 3,
        overflow = TextOverflow.Ellipsis
    )
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerTitlePreview() {
    NanaLandTheme {
        NanaPickContentTopBannerTitle(text = "Title",false)
    }
}