package com.jeju.nanaland.ui.component.main.searchresult.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun SearchResultScreenPreviewByCategoryTitle(text: String) {
    Text(
        text = text,
        color = getColor().black,
        style = title02Bold
    )
}

@ComponentPreview
@Composable
private fun SearchResultScreenPreviewByCategoryTitlePreview() {
    NanaLandTheme {
        SearchResultScreenPreviewByCategoryTitle(text = "7대 자연")
    }
}