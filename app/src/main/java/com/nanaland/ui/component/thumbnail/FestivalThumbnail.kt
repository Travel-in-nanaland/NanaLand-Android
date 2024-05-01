package com.nanaland.ui.component.thumbnail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.thumbnail.parts.ThumbnailImage
import com.nanaland.ui.component.thumbnail.parts.ThumbnailLikeButton
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ComponentPreview

@Composable
fun FestivalThumbnail(
    imageUri: String?,
    isLiked: Boolean,
    onIsLikedButtonClicked: () -> Unit,
) {
    Column(
        modifier = Modifier.wrapContentSize()
    ) {
        Box {
            ThumbnailImage(imageUri = imageUri)
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 8.dp, end = 8.dp)
            ) {
                ThumbnailLikeButton(
                    isLiked = isLiked,
                    onClick = onIsLikedButtonClicked
                )
            }
        }

        Spacer(Modifier.height(10.dp))
    }
}

@ComponentPreview
@Composable
private fun FestivalThumbnailPreview() {
    NanaLandTheme {
        FestivalThumbnail(
            imageUri = "",
            isLiked = false,
            onIsLikedButtonClicked = {}
        )
    }
}