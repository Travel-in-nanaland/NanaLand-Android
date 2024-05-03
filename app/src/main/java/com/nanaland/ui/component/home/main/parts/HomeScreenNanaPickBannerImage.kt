package com.nanaland.ui.component.home.main.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun HomeScreenNanaPickBannerImage(imageUri: String?) {
    GlideImage(
        modifier = Modifier.fillMaxSize(),
        imageModel = { imageUri }
    )
}