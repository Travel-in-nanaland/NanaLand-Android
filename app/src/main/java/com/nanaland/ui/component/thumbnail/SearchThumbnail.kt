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
import com.nanaland.ui.component.thumbnail.parts.ThumbnailTitle
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchThumbnail(
    imageUri: String?,
    isLiked: Boolean,
    title: String?,
    onLikeButtonClick: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .clickableNoEffect { onClick() }
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
                    onClick = onLikeButtonClick
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        ThumbnailTitle(text = title)
    }
}

@ComponentPreview
@Composable
private fun SearchThumbnailPreview() {
    NanaLandTheme {
        SearchThumbnail(
            imageUri = "",
            isLiked = false,
            title = "title",
            onLikeButtonClick = {},
            onClick = {}
        )
    }
}