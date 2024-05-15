package com.jeju.nanaland.ui.component.listscreen.filter.parts.location

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.largeTitle02
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun LocationFilterDialogTitle() {
    Text(
        text = "지역",
        color = getColor().black,
        style = largeTitle02
    )
}

@ComponentPreview
@Composable
private fun LocationFilterDialogTitlePreview() {
    NanaLandTheme {
        LocationFilterDialogTitle()
    }
}