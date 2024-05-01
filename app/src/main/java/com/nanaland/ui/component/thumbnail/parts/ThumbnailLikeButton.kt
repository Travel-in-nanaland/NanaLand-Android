package com.nanaland.ui.component.thumbnail.parts

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
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun ThumbnailLikeButton(
    isLiked: Boolean,
    onClick: () -> Unit
) {
    Image(
        modifier = Modifier
            .size(28.dp)
            .clickableNoEffect { onClick() },
        painter = painterResource(if (isLiked) R.drawable.ic_heart_filled else R.drawable.ic_heart_outlined_white),
        contentDescription = null,
        colorFilter = if (isLiked) ColorFilter.tint(getColor().main) else null
    )
}

@ComponentPreview
@Composable
private fun ThumbnailLikeButtonPreview_isLiked_True() {
    NanaLandTheme {
        ThumbnailLikeButton(
            isLiked = true
        ) {}
    }
}

@ComponentPreview
@Composable
private fun ThumbnailLikeButtonPreview_isLiked_False() {
    NanaLandTheme {
        ThumbnailLikeButton(
            isLiked = false
        ) {}
    }
}