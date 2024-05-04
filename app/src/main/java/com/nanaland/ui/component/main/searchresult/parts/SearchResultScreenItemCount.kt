package com.nanaland.ui.component.main.searchresult.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview
import dagger.Component

@Composable
fun SearchResultScreenItemCount(
    modifier: Modifier = Modifier,
    count: Long
) {
    Text(
        modifier = modifier,
        text = "${count}건",
        color = getColor().gray01,
        style = body02
    )
}

@ComponentPreview
@Composable
private fun SearchResultScreenItemCountPreview() {
    NanaLandTheme {
        SearchResultScreenItemCount(count = 9)
    }
}