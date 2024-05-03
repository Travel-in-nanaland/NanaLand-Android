package com.nanaland.ui.component.home.main.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.largeTitle02
import com.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun HomeScreenNanaPickBannerTitle(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().white,
        style = largeTitle02
    )
}

@ComponentPreviewBlack
@Composable
private fun HomeScreenNanaPickBannerTitlePreview() {
    NanaLandTheme {
        HomeScreenNanaPickBannerTitle(text = "Title")
    }
}