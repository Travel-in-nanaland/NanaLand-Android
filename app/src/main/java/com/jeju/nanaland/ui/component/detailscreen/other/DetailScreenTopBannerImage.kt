package com.jeju.nanaland.ui.component.detailscreen.other

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreenTopBannerImage(imageUri: String?) {
    GlideImage(
        modifier = Modifier.fillMaxWidth().height(240.dp),
        imageModel = { imageUri },
    )
}