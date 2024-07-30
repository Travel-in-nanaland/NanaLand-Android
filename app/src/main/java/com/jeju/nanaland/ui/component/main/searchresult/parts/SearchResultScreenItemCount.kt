package com.jeju.nanaland.ui.component.main.searchresult.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.R
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.resource.getString
import com.jeju.nanaland.util.ui.ComponentPreview
import dagger.Component

@Composable
fun SearchResultScreenItemCount(
    modifier: Modifier = Modifier,
    count: Int
) {
    Text(
        modifier = modifier,
        text = "$count " + getString(R.string.common_건),
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