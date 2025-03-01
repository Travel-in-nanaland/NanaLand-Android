package com.jeju.nanaland.ui.component.thumbnail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailFavoriteButton
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailImage
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02SemiBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun SearchThumbnail(
    imageUri: String?,
    isFavorite: Boolean,
    title: String?,
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
                    .align(Alignment.BottomEnd)
                    .padding(6.dp)
            ) {
                ThumbnailFavoriteButton(
                    isFavorite = isFavorite,
                    onClick = onFavoriteButtonClick,
                    moveToSignInScreen = moveToSignInScreen,
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        Text(
            text = title ?: "",
            color = getColor().black,
            style = body02SemiBold,
            maxLines = when (getLanguage()) {
                LanguageType.Korean, LanguageType.Chinese -> 1
                LanguageType.English, LanguageType.Malaysia, LanguageType.Vietnam -> 2
            },
            overflow = TextOverflow.Ellipsis
        )
    }
}

@ComponentPreview
@Composable
private fun SearchThumbnailPreview() {
    NanaLandTheme {
        SearchThumbnail(
            imageUri = "",
            isFavorite = false,
            title = "title",
            onFavoriteButtonClick = {},
            onClick = {},
            moveToSignInScreen = {}
        )
    }
}