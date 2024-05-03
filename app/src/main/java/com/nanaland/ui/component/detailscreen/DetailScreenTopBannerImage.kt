package com.nanaland.ui.component.detailscreen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.zIndex
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreenTopBannerImage(imageUri: String?) {
    GlideImage(
        modifier = Modifier.fillMaxWidth(),
        imageModel = { imageUri },
        imageOptions = ImageOptions(contentScale = ContentScale.FillWidth)
    )
}