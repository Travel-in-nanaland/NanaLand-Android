package com.jeju.nanaland.ui.component.detailscreen.nanapick.parts.subcontent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.NanaLandTheme
import com.jeju.nanaland.ui.theme.getColor
import com.jeju.nanaland.ui.theme.title01Bold
import com.jeju.nanaland.util.ui.ComponentPreview
import com.jeju.nanaland.util.ui.drawCircleColoredShadow

@Composable
fun NanaPickContentSubContentNumber(index: Int) {
    Box(
        modifier = Modifier
            .size(30.dp)
            .drawCircleColoredShadow(
                color = Color.Black,
                alpha = 0.2f,
                shadowRadius = 6.dp
            )
            .clip(CircleShape)
            .background(getColor().white),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = index.toString(),
            color = Color(0xFF583FF5),
            style = title01Bold
        )
    }
}

@ComponentPreview
@Composable
private fun NanaPickContentSubContentNumberPreview() {
    NanaLandTheme {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            NanaPickContentSubContentNumber(3)
        }
    }
}