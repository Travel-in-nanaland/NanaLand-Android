package com.jeju.nanaland.ui.component.thumbnail.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ThumbnailImage(
    imageUri: String?
) {
    GlideImage(
        modifier = Modifier
            .width(160.dp)
            .height(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(getColor().skeleton),
        imageModel = { imageUri }
    )
}

@ComponentPreview
@Composable
private fun Preview() {
    NanaLandTheme {
        ThumbnailImage(
            imageUri = "https://file.notion.so/f/f/2bf22a5c-a41d-4bbb-9b75-5fe557a5bad9/9afe3dac-6ab9-4149-b2aa-622d49cc258c/nanapick_list_preview_background_1.png?id=1b13ce8f-66f1-4c24-ae34-dbe69cd348e9&table=block&spaceId=2bf22a5c-a41d-4bbb-9b75-5fe557a5bad9&expirationTimestamp=1714615200000&signature=kgKQjMAQzxSijaQYGxd7P1y3Sp9iNYVNmvjy2t4adMU&downloadName=nanapick_list_preview_background_1.png"
        )
    }
}