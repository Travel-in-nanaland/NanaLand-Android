package com.jeju.nanaland.ui.component.detailscreen.other.parts.information

import android.webkit.URLUtil
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextDecoration
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun DetailScreenInformationContent(text: String?) {
    Text(
        text = text ?: "",
        color = getColor().black,
        style = body02,
        textDecoration = if(URLUtil.isValidUrl(text)) TextDecoration.Underline else TextDecoration.None
    )
}

@ComponentPreview
@Composable
private fun DetailScreenInformationContentPreview() {
    NanaLandTheme {
        DetailScreenInformationContent(text = "Content")
    }
}