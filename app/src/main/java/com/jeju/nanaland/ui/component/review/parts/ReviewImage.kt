package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ReviewImage(imageUrl: String) {
    GlideImage(
        modifier = Modifier
            .size(70.dp)
            .clip(RoundedCornerShape(8.dp)),
        imageModel = { imageUrl }
    )
}