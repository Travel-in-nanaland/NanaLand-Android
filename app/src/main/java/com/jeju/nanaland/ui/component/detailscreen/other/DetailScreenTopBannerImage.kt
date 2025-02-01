package com.jeju.nanaland.ui.component.detailscreen.other

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.common.dialog.FullImageDialog
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreenTopBannerImage(imageUri: String?) {
    val fullImageUrl = remember { mutableStateOf<String?>(null) }

    fullImageUrl.value?.let {
        FullImageDialog(it) { fullImageUrl.value = null }
    }

    GlideImage(
        modifier = Modifier.fillMaxWidth().height(240.dp).clickableNoEffect {
            fullImageUrl.value = imageUri
        },
        imageModel = { imageUri },
    )
}