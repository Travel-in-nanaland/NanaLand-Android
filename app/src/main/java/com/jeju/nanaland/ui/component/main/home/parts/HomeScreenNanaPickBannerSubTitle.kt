package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun HomeScreenNanaPickBannerSubTitle(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().white,
        style = body02Bold
    )
}

@ComponentPreviewBlack
@Composable
private fun HomeScreenNanaPickBannerSubTitlePreview() {
    NanaLandTheme {
        HomeScreenNanaPickBannerSubTitle(text = "SubTitle")
    }
}