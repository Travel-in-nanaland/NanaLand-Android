package com.nanaland.ui.component.home.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview

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