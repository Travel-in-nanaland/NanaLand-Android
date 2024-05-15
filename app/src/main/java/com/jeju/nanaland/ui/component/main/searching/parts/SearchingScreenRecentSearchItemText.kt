package com.jeju.nanaland.ui.component.main.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun SearchingScreenRecentSearchItemText(text: String) {
    Text(
        text = text,
        color = getColor().main,
        style = body02
    )
}

@ComponentPreview
@Composable
private fun SearchingScreenRecentSearchItemTextPreview() {
    NanaLandTheme {
        SearchingScreenRecentSearchItemText(text = "Text")
    }
}