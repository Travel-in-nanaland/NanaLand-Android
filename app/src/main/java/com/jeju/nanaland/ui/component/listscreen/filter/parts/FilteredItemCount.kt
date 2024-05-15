package com.jeju.nanaland.ui.component.listscreen.filter.parts

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun FilteredItemCount(
    count: Long
) {
    Text(
        text = "${count}건",
        color = getColor().gray01,
        style = body02
    )
}

@ComponentPreview
@Composable
private fun FilteredItemCountPreview() {
    NanaLandTheme {
        FilteredItemCount(count = 3)
    }
}