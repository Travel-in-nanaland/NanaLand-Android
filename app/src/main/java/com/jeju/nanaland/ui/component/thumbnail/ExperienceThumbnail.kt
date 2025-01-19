package com.jeju.nanaland.ui.component.thumbnail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailFavoriteButton
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailImage
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailPlaceText
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailRating
import com.jeju.nanaland.ui.component.thumbnail.parts.ThumbnailTitle
import com.jeju.nanaland.util.ui.clickableNoEffect

@Composable
fun ExperienceThumbnail(
    imageUri: String?,
    isFavorite: Boolean,
    title: String?,
    place: String,
    rating: String,
    onClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
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
                    moveToSignInScreen = moveToSignInScreen
                )
            }
        }

        Spacer(Modifier.height(8.dp))

        ThumbnailTitle(text = title)

        Spacer(Modifier.height(4.dp))

        Row {
            ThumbnailPlaceText(place = place)

            Spacer(Modifier.weight(1f))

            ThumbnailRating(rating = rating)
        }

        Spacer(Modifier.height(8.dp))
    }
}