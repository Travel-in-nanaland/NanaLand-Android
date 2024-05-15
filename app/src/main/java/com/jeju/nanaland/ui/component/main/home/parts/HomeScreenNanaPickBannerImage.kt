package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreenNanaPickBannerImage(imageUri: String?) {
    GlideImage(
        modifier = Modifier.fillMaxSize(),
        imageModel = { imageUri }
    )
}