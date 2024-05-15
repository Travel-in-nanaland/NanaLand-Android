package com.jeju.nanaland.ui.component.main.home.parts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jeju.nanaland.ui.theme.getColor

@Composable
fun HomeScreenAdContent(
    idx: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(
                color = getColor().skyblue,
                shape = RoundedCornerShape(8.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$idx Ad"
        )
    }
}