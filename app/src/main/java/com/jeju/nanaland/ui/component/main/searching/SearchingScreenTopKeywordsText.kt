package com.jeju.nanaland.ui.component.main.searching

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title02Bold
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun SearchingScreenTopKeywordsText() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = title02Bold.toSpanStyle().copy(
                    color = getColor().main
                )
            ) {
                append(getString(R.string.searching_screen_가장_많이))
            }
            withStyle(
                style = title02Bold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append(getString(R.string.searching_screen_검색하고_있어요))
            }
        }
    )
}

@ComponentPreview
@Composable
private fun SearchingScreenHotKeywordsTextPreview() {
    NanaLandTheme {
        SearchingScreenTopKeywordsText()
    }
}