package com.jeju.nanaland.ui.component.detailscreen.other.parts.description

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
fun DetailScreenFavoriteButton(
    isFavorite: Boolean,
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .size(32.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(if (isFavorite) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined_thick),
        contentDescription = null,
        colorFilter = if (isFavorite) ColorFilter.tint(getColor().main) else null
    )
}

@ComponentPreview
@Composable
private fun DetailScreenFavoriteButtonPreview1() {
    NanaLandTheme {
        DetailScreenFavoriteButton(isFavorite = false) {}
    }
}

@ComponentPreview
@Composable
private fun DetailScreenFavoriteButtonPreview2() {
    NanaLandTheme {
        DetailScreenFavoriteButton(isFavorite = true) {}
    }
}