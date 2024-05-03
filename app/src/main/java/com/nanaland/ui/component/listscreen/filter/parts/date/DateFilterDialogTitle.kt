package com.nanaland.ui.component.listscreen.filter.parts.date

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.getColor
import com.nanaland.ui.theme.title02Bold
import com.nanaland.util.ui.ComponentPreview

@Composable
fun DateFilterDialogTitle() {
    Text(
        text = "날짜 선택",
        color = getColor().black,
        style = title02Bold
    )
}

@ComponentPreview
@Composable
private fun DateFilterDialogTitlePreview() {
    NanaLandTheme {
        DateFilterDialogTitle()
    }
}