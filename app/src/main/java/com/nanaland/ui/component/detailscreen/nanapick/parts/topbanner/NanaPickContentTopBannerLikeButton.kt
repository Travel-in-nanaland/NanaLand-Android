package com.nanaland.ui.component.detailscreen.nanapick.parts.topbanner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nanaland.R
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreviewBlack
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun NanaPickContentTopBannerLikeButton(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    onClick: () -> Unit
) {
    Image(
        modifier = modifier
            .size(32.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(if (isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined_thick),
        contentDescription = null,
        colorFilter = if (isLiked) ColorFilter.tint(getColor().main) else ColorFilter.tint(getColor().white)
    )
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerLikeButtonPreview1() {
    NanaLandTheme {
        NanaPickContentTopBannerLikeButton(
            isLiked = false,
            onClick = {}
        )
    }
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerLikeButtonPreview2() {
    NanaLandTheme {
        NanaPickContentTopBannerLikeButton(
            isLiked = true,
            onClick = {}
        )
    }
}