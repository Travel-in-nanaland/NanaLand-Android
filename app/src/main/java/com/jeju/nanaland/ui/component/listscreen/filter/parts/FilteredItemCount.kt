package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun FilteredItemCount(
    count: Int
) {
    Box { }
    // TODO 삭제
//    Text(
//        text = "$count " + getString(R.string.common_건),
//        color = getColor().gray01,
//        style = body02
//    )
}

@ComponentPreview
@Composable
private fun FilteredItemCountPreview() {
    NanaLandTheme {
        FilteredItemCount(count = 3)
    }
}