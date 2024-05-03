package com.nanaland.ui.component.thumbnail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.thumbnail.parts.ThumbnailImage
import com.nanaland.ui.component.thumbnail.parts.ThumbnailLikeButton
import com.nanaland.ui.component.common.Tag
import com.nanaland.ui.component.thumbnail.parts.ThumbnailTitle
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.clickableNoEffect

@Composable
fun NatureThumbnail(
    imageUri: String?,
    isLiked: Boolean,
    title: String?,
    tag: String?,
    onLikeButtonClick: () -> Unit,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .width(160.dp)
            .wrapContentHeight()
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

        Spacer(Modifier.height(8.dp))

        Tag(text = tag)
    }
}

@ComponentPreview
@Composable
private fun NatureThumbnailPreview1() {
    NanaLandTheme {
        NatureThumbnail(
            imageUri = "",
            isLiked = false,
            title = "title",
            tag = "tag",
            onLikeButtonClick = {},
            onClick = {}
        )
    }
}

@ComponentPreview
@Composable
private fun NatureThumbnailPreview2() {
    NanaLandTheme {
        NatureThumbnail(
            imageUri = "",
            isLiked = true,
            title = "title title title title title title title",
            tag = "tag",
            onLikeButtonClick = {},
            onClick = {}
        )
    }
}