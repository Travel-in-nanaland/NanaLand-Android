package com.jeju.nanaland.ui.component.thumbnail.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.jeju.nanaland.globalvalue.type.LanguageType
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02Bold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.language.getLanguage
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun ThumbnailTitle(
    text: String?
) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body02Bold,
        maxLines = when (getLanguage()) {
            LanguageType.Korean, LanguageType.Chinese -> 1
            LanguageType.English, LanguageType.Malaysia, LanguageType.Vietnam -> 2
        },
        overflow = TextOverflow.Ellipsis
    )
}

@ComponentPreview
@Composable
private fun ThumbnailTextPreview() {
    NanaLandTheme {
        ThumbnailTitle(
            text = "제주도 축제"
        )
    }
}