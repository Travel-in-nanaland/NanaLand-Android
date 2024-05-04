package com.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun NanaPickContentSubContentImage(imageUri: String?) {
    GlideImage(
        modifier = Modifier.fillMaxWidth(),
        imageModel = { imageUri },
        imageOptions = ImageOptions(contentScale = ContentScale.FillWidth)
    )
}