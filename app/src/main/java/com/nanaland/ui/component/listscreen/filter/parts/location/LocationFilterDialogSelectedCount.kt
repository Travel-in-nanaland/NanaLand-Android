package com.nanaland.ui.component.listscreen.filter.parts.location

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body02
import com.nanaland.util.ui.ComponentPreview

@Composable
fun LocationFilterDialogSelectedCount(count: Int) {
    Text(
        text = "($count / 15)",
        color = Color(0xFF717171),
        style = body02
    )
}

@ComponentPreview
@Composable
private fun LocationFilterDialogSelectedCountPreview() {
    NanaLandTheme {
        LocationFilterDialogSelectedCount(count = 4)
    }
}