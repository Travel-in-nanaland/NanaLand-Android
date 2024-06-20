package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.caption01
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun NanaPickContentSubContentSubTitle(text: String?) {
    Text(
        modifier = Modifier.padding(start = 38.dp),
        text = text ?: "",
        color = getColor().main,
        style = caption01
    )
}

@ComponentPreview
@Composable
private fun NanaPickContentSubContentSubTitlePreview() {
    NanaLandTheme {
        NanaPickContentSubContentSubTitle(text = "SubTitle")
    }
}