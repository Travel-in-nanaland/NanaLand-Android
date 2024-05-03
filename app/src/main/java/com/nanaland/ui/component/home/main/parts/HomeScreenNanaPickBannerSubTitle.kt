package com.nanaland.ui.component.home.main.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.bodyBold
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun HomeScreenNanaPickBannerSubTitle(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().white,
        style = bodyBold
    )
}

@ComponentPreviewBlack
@Composable
private fun HomeScreenNanaPickBannerSubTitlePreview() {
    NanaLandTheme {
        HomeScreenNanaPickBannerSubTitle(text = "SubTitle")
    }
}