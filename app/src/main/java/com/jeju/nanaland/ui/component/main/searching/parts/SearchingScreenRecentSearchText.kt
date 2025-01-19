package com.jeju.nanaland.ui.component.main.searching.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun SearchingScreenRecentSearchText() {
    Text(
        text = getString(R.string.searching_screen_최근_검색어),
        color = getColor().black,
        style = bodyBold
    )
}

@ComponentPreview
@Composable
private fun SearchingScreenRecentSearchTextPreview() {
    NanaLandTheme {
        SearchingScreenRecentSearchText()
    }
}