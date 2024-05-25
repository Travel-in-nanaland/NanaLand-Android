package com.jeju.nanaland.ui.component.signup.profilesetting

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.R
import com.jeju.nanaland.util.ui.clickableNoEffect
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun SignUpScreenPhotoPreview(
    imageUri: String?,
    onClick: () -> Unit,
) {
    GlideImage(
        modifier = Modifier
            .size(100.dp)
            .clip(RoundedCornerShape(50))
            .clickableNoEffect { onClick() },
        imageModel = {
            imageUri ?: R.drawable.photo_preview
        }
    )
}