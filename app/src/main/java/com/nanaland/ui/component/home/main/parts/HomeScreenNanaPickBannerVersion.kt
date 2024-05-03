package com.nanaland.ui.component.home.main.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.caption01SemiBold
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun BoxScope.HomeScreenNanaPickBannerVersion(text: String?) {
    Text(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .padding(16.dp),
        text = text ?: "",
        color = getColor().white,
        style = caption01SemiBold
    )
}

@ComponentPreviewBlack
@Composable
private fun HomeScreenNanaPickBannerVersionPreview() {
    NanaLandTheme {
        Box {
            HomeScreenNanaPickBannerVersion(text = "version")
        }
    }
}