package com.nanaland.ui.component.detailscreen.parts.information

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.nanaland.ui.theme.NanaLandTheme
import com.nanaland.ui.theme.body02
import com.nanaland.ui.theme.getColor
import com.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenInformationContent(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body02
    )
}

@ComponentPreview
@Composable
private fun DetailScreenInformationContentPreview() {
    NanaLandTheme {
        DetailScreenInformationContent(text = "Content")
    }
}