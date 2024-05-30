package com.jeju.nanaland.ui.component.thumbnail

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
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailImage
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailFavoriteButton
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailSubtitle
import com.jeju.nanaland.ui.component.common.TagChip1
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailTitle
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun FestivalThumbnail(
    imageUri: String?,
    isFavorite: Boolean,
    title: String?,
    subTitle: String?,
    tag: String?,
    onFavoriteButtonClick: () -> Unit,
    onClick: () -> Unit,
    moveToSignInScreen: () -> Unit,
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
                ThumbnailFavoriteButton(
                    isFavorite = isFavorite,
                    onClick = onFavoriteButtonClick,
                    moveToSignInScreen = moveToSignInScreen
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        ThumbnailTitle(text = title)

        Spacer(Modifier.height(4.dp))

        ThumbnailSubtitle(text = subTitle)

        Spacer(Modifier.height(8.dp))

        TagChip1(text = tag)
    }
}

@ComponentPreview
@Composable
private fun FestivalThumbnailPreview1() {
    NanaLandTheme {
        FestivalThumbnail(
            imageUri = "",
            isFavorite = false,
            title = "title",
            subTitle = "subTitle",
            tag = "tag",
            onFavoriteButtonClick = {},
            onClick = {},
            moveToSignInScreen = {}
        )
    }
}

@ComponentPreview
@Composable
private fun FestivalThumbnailPreview2() {
    NanaLandTheme {
        FestivalThumbnail(
            imageUri = "",
            isFavorite = true,
            title = "title title title title title title title",
            subTitle = "subTitle",
            tag = "tag",
            onFavoriteButtonClick = {},
            onClick = {},
            moveToSignInScreen = {}
        )
    }
}