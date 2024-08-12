package com.jeju.nanaland.ui.component.detailscreen.other

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.common.TagChip1
import com.jeju.nanaland.ui.component.detailscreen.other.parts.description.DetailScreenDescriptionContent
import com.jeju.nanaland.ui.component.detailscreen.other.parts.description.DetailScreenDescriptionTitle
import com.jeju.nanaland.ui.component.detailscreen.other.parts.description.DetailScreenMoreButton
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ScreenPreview
import com.jeju.nanaland.util.ui.drawColoredShadow

@Composable
fun ExperienceDetailScreenDescription(
    tag: String?,
    keywords: List<String>,
    title: String?,
    content: String?,
) {
    val isMoreOpen = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
//            .padding(16.dp)
            .fillMaxWidth()
            .drawColoredShadow(
                color = Color.Black,
                alpha = 0.1f,
                shadowRadius = 12.dp
            )
            .clip(RoundedCornerShape(12.dp))
            .background(
                color = getColor().white,
                shape = RoundedCornerShape(12.dp)
            )
            .animateContentSize()
    ) {
        Column(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            LazyRow(
                modifier = Modifier.height(20.dp)
            ) {
                item {
                    Row {
                        TagChip1(text = tag)

                        Spacer(Modifier.width(12.dp))
                    }
                }

                itemsIndexed(keywords) { idx, item ->
                    Row {
                        TagChip1(text = item)

                        Spacer(Modifier.width(12.dp))
                    }
                }
            }

            Spacer(Modifier.height(12.dp))

            DetailScreenDescriptionTitle(text = title)

            Spacer(Modifier.height(8.dp))

            DetailScreenDescriptionContent(
                isMoreOpen = isMoreOpen.value,
                text = content
            )

            Spacer(Modifier.height(16.dp))

            Box(
                modifier = Modifier.align(Alignment.End)
            ) {
                DetailScreenMoreButton(
                    isMoreOpen = isMoreOpen.value,
                    onClick = { isMoreOpen.value = !isMoreOpen.value }
                )
            }
        }
    }
}

@ScreenPreview
@Composable
private fun DetailScreenDescriptionPreview() {
    NanaLandTheme {
        DetailScreenDescription(
            isFavorite = false,
            tag = "tag",
            title = "Title",
            content = "DescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescriptionDescription",
            onFavoriteButtonClicked = {},
            onShareButtonClicked = {},
            moveToSignInScreen = {}
        )
    }
}