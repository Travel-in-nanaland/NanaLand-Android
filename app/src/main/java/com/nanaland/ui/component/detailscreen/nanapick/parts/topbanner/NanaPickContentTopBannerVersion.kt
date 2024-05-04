package com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body02Bold
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreviewBlack

@Composable
fun NanaPickContentTopBannerVersion(
    modifier: Modifier = Modifier,
    text: String?
) {
    Text(
        modifier = modifier,
        text = text ?: "",
        color= getColor().white,
        style = body02Bold
    )
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerVersionPreview() {
    NanaLandTheme {
        NanaPickContentTopBannerVersion(text = "Version")
    }
}