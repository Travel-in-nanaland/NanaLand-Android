package com.jeju.nanaland.ui.component.main.searching

import androidx.compose.runtime.Composable
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.component.common.text.TextWithPointColor
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.bodyBold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun SearchingScreenTopKeywordsText() {
    TextWithPointColor(
        text = getString(R.string.searching_screen_most_search),
        style = bodyBold
    )
}

@ComponentPreview
@Composable
private fun SearchingScreenHotKeywordsTextPreview() {
    NanaLandTheme {
        SearchingScreenTopKeywordsText()
    }
}