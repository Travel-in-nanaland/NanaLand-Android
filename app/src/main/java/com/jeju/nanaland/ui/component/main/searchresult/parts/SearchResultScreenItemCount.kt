package com.jeju.nanaland.ui.component.main.searchresult.parts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun SearchResultScreenItemCount(
    modifier: Modifier = Modifier,
    count: Int
) {
//    if(count > 0)
//        Text(
//            modifier = modifier,
//            text = "$count " + getString(R.string.common_건),
//            color = getColor().gray01,
//            style = body02
//        )
}

@ComponentPreview
@Composable
private fun SearchResultScreenItemCountPreview() {
    NanaLandTheme {
        SearchResultScreenItemCount(count = 9)
    }
}