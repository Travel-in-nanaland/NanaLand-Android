package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun AdPageIndicatorDot(
    color: Color
) {
    Box(
        modifier = Modifier
            .size(8.dp)
            .background(
                color = color,
                shape = CircleShape
            )
    )
}