package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.topbanner

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreviewBlack
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun NanaPickContentTopBannerFavoriteButton(
    modifier: Modifier = Modifier,
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    Image(
        modifier = modifier
            .size(32.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined_thick),
        contentDescription = null,
        colorFilter = if (isFavorite) ColorFilter.tint(getColor().main) else ColorFilter.tint(getColor().white)
    )
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerFavoriteButtonPreview1() {
    NanaLandTheme {
        NanaPickContentTopBannerFavoriteButton(
            isFavorite = false,
            onClick = {}
        )
    }
}

@ComponentPreviewBlack
@Composable
private fun NanaPickContentTopBannerFavoriteButtonPreview2() {
    NanaLandTheme {
        NanaPickContentTopBannerFavoriteButton(
            isFavorite = true,
            onClick = {}
        )
    }
}