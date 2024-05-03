package com.nanaland.ui.component.home.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title02Bold
import com.nanaland.util.ui.ComponentPreview

@Composable
fun SearchingScreenRecentSearchText() {
    Text(
        text = "최근 검색어",
        color = getColor().black,
        style = title02Bold
    )
}

@ComponentPreview
@Composable
private fun SearchingScreenRecentSearchTextPreview() {
    NanaLandTheme {
        SearchingScreenRecentSearchText()
    }
}