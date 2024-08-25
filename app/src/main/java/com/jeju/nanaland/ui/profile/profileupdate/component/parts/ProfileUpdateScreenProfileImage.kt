package com.jeju.nanaland.ui.profile.profileupdate.component.parts

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileUpdateScreenProfileImage(imageUri: String?) {
    GlideImage (
        modifier = Modifier
            .size(100.dp)
            .clip(CircleShape),
        imageModel = { imageUri },
        imageOptions = ImageOptions(contentScale = ContentScale.Crop)
    )
}