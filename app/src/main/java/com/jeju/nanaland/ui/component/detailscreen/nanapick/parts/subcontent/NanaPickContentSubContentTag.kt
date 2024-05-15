package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.body02
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.util.ui.ComponentPreview

@Composable
fun NanaPickContentSubContentTag(text: String?) {
    Box(
        modifier = Modifier
            .height(32.dp)
            .background(
                color = getColor().main10,
                shape = RoundedCornerShape(50)
            )
            .padding(start = 10.dp, end = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text ?: "",
            color = getColor().main,
            style = body02
        )
    }
}

@ComponentPreview
@Composable
private fun NanaPickContentSubContentTagPreview() {
    NanaLandTheme {
        NanaPickContentSubContentTag(text = "Tag")
    }
}