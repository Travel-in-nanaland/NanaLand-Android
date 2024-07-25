package com.jeju.nanaland.ui.component.thumbnail.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun ThumbnailPlaceText(place: String) {
    Text(
        text = place,
        color = getColor().gray01,
        style = caption01
    )
}