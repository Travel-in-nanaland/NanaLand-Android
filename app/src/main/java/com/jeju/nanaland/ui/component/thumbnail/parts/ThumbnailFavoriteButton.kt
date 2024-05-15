package com.jeju.nanaland.ui.component.thumbnail.parts

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
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun ThumbnailFavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .size(32.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined_white_filled_translucent),
        contentDescription = null,
        colorFilter = if (isFavorite) ColorFilter.tint(getColor().main) else null
    )
}

@ComponentPreview
@Composable
private fun ThumbnailFavoriteButtonPreview1() {
    NanaLandTheme {
        ThumbnailFavoriteButton(
            isFavorite = true
        ) {}
    }
}

@ComponentPreview
@Composable
private fun ThumbnailFavoriteButtonPreview2() {
    NanaLandTheme {
        ThumbnailFavoriteButton(isFavorite = false) {}
    }
}