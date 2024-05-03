package com.nanaland.ui.component.main.searching

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title02Bold
import com.nanaland.util.ui.ComponentPreview

@Composable
fun SearchingScreenHotPostsText() {
    Text(
        text = "검색량 UP! JEJU Place \uD83C\uDF4A",
        color = getColor().black,
        style = title02Bold
    )
}

@ComponentPreview
@Composable
private fun SearchingScreenHotPlacesTextPreview() {
    NanaLandTheme {
        SearchingScreenHotPostsText()
    }
}