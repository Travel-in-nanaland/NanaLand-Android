package com.jeju.nanaland.ui.component.review.parts

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ReviewProfileImage(imageUrl: String) {
    GlideImage(
        modifier = Modifier
            .size(42.dp)
            .clip(CircleShape),
        imageModel = { imageUrl }
    )
}