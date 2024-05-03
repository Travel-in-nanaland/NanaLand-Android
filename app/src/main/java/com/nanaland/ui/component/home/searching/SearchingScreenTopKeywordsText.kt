package com.nanaland.ui.component.home.searching

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title02Bold
import com.nanaland.util.ui.ComponentPreview

@Composable
fun SearchingScreenTopKeywordsText() {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = title02Bold.toSpanStyle().copy(
                    color = getColor().main
                ) ,
            ) {
                append("가장 많이")
            }
            withStyle(
                style = title02Bold.toSpanStyle().copy(
                    color = getColor().black
                )
            ) {
                append(" 검색하고 있어요!")
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