package com.nanaland.ui.component.detailscreen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nanaland.ui.component.common.Tag
import com.nanaland.ui.component.detailscreen.parts.description.DetailScreenDescriptionContent
import com.nanaland.ui.component.detailscreen.parts.description.DetailScreenDescriptionTitle
import com.nanaland.ui.component.detailscreen.parts.description.DetailScreenLikeButton
import com.nanaland.ui.component.detailscreen.parts.description.DetailScreenMoreButton
import com.nanaland.ui.component.detailscreen.parts.description.DetailScreenShareButton
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview
import com.nanaland.util.ui.ScreenPreview

@Composable
fun DetailScreenDescription(
    isLiked: Boolean,
    tag: String?,
    title: String?,
    content: String?,
    onLikeButtonClicked: () -> Unit,
    onShareButtonClicked: () -> Unit,
) {
    val isMoreOpen = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(12.dp)
            )
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 28.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Tag(text = tag)

            Spacer(Modifier.height(12.dp))

            DetailScreenDescriptionTitle(text = title)

            Spacer(Modifier.height(4.dp))

            DetailScreenDescriptionContent(
                isMoreOpen = isMoreOpen.value,
                text = content
            )

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier.align(Alignment.End)
            ) {
                DetailScreenMoreButton {
                    isMoreOpen.value = !isMoreOpen.value
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 16.dp)
        ) {
            DetailScreenLikeButton(isLiked = isLiked) { onLikeButtonClicked() }

            Spacer(Modifier.width(8.dp))

            DetailScreenShareButton { onShareButtonClicked() }
        }
    }
}

@ScreenPreview
@Composable
private fun DetailScreenDescriptionPreview() {
    NanaLandTheme {
        DetailScreenDescription(
            isLiked = false,
            tag = "tag",
            title = "Title",
            content = "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription",
            onLikeButtonClicked = {},
            onShareButtonClicked = {}
        )
    }
}