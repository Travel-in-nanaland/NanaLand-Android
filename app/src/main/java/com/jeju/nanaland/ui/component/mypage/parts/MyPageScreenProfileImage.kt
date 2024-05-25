package com.jeju.nanaland.ui.component.mypage.parts

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun BoxScope.MyPageScreenProfileImage(imageUri: String?) {
    GlideImage (
        modifier = Modifier
            .align(Alignment.TopCenter)
            .size(100.dp)
            .clip(CircleShape),
        imageModel = { imageUri },
        imageOptions = ImageOptions(contentScale = ContentScale.Crop)
    )
}